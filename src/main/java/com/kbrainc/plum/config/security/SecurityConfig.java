package com.kbrainc.plum.config.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.channel.ChannelDecisionManagerImpl;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.channel.ChannelProcessor;
import org.springframework.security.web.access.channel.InsecureChannelProcessor;
import org.springframework.security.web.access.channel.RetryWithHttpsEntryPoint;
import org.springframework.security.web.access.channel.SecureChannelProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.kbrainc.plum.rte.filter.SiteChangeFilter;
import com.kbrainc.plum.rte.security.AjaxSessionTimeoutFilter;
import com.kbrainc.plum.rte.security.CustomAuthFailureHandler;
import com.kbrainc.plum.rte.security.CustomAuthenticationProvider;
import com.kbrainc.plum.rte.security.CustomLogoutSuccessHandler;
import com.kbrainc.plum.rte.security.CustomWebInvocationPrivilegeEvaluator;
import com.kbrainc.plum.rte.security.HttpsLoginSuccessHandler;
import com.kbrainc.plum.rte.security.ReloadableChannelProcessingFilterMetadataSource;
import com.kbrainc.plum.rte.security.ReloadableFilterSecurityInterceptorMetadataSource;
import com.kbrainc.plum.rte.service.ResSiteService;

/**
 * 
 * 스프링 시큐리티 설정 파일.
 *
 * <pre>
 * com.kbrainc.plum.config.security
 * - SecurityConfig.java
 * </pre> 
 *
 * @ClassName : SecurityConfig
 * @Description : 스프링 시큐리티 설정 파일
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.http.port}")
    private String serverHttpPort;

    @Value("${server.port}")
    private String serverHttpsPort;

    @Value("${app.multilogin.isuse:false}")
    private boolean multiloginIsUse;
    
    @Value("${app.ssl.isuse}")
    private boolean sslIsUse;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ReloadableChannelProcessingFilterMetadataSource channelProcessingFilterMetadataSource;

    @Autowired
    private ReloadableFilterSecurityInterceptorMetadataSource filterSecurityInterceptorMetadataSource;

    @Autowired
    private SecureChannelProcessor secureChannelProcessor;

    @Autowired
    private InsecureChannelProcessor insecureChannelProcessor;

    private FilterSecurityInterceptor filterSecurityInterceptor;

    private ChannelProcessingFilter channelProcessingFilter;
    
    @Autowired
    ResSiteService resSiteService;

    /*@Autowired
    @Lazy
    private RedisIndexedSessionRepository sessionRepository;*/ // REDIS_SESSION
        
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/mng/monitor/reloadMenuInfo.do",
                "/mng/monitor/reloadSecurityMetadataSource.do",
                "/mng/monitor/reloadCodeInfo.do",
                "/downloadLogo.do",
                "/onepass/rcv.do",
        		"/**/*.js", 
        		"/**/*.css", 
        		"/css/**/*", 
        		"/fonts/**/*", 
        		"/images/**/*", 
        		"/favicon.ico",
        		"/test/*",
        		"/sample/*",
        		"/files/**/*",
                "/down/**/*",
        		"/ckE/**/*",
        		"/ckEimg/**/*",
                "/pdf_view_file/**/*",
                "/cmmFile/**/*",
                "/js/pdfjs/**/*",
        		"/example/**/*",
        		"/js/ckeditor/**/*",
        		"/old/**/*",
                "/infodata3/**/*", // 마이그레이션 데이터 pdf 허용
                "/devnas/keep/portal/uploads/infodata4/**/*",
        		"/search/*" // 통합검색
        ); // 시큐리티를 적용하지 않을 자원들
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 
         * 로그인후 새로운 세션을 생성하고 기존의 세션 값들을 새 세션에 복사해준다. 
         * 세션고정공격 방지 및 IE에서 로그인후 JSESSIONID가 변경될때 로그인이 되지않는 현상 해결
         * 
         */
        http.sessionManagement().sessionFixation().migrateSession(); 

        if (multiloginIsUse) {
            // 1개의 세션만 로그인이 가능하며, 중복로그인시 먼저로그인한 사람의 세션이 끊기며 action시 expiredUrl로 이동하게 된다. 이중화
            // 이중화 구성시 각서버에서만 적용됨. 세션서버사용시 중복로그인 사용가능.
            http.sessionManagement().maximumSessions(1).expiredUrl("/dupLogout");
            //http.sessionManagement().maximumSessions(1).expiredUrl("/dupLogout").sessionRegistry(sessionRegistry()); // REDIS_SESSION
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여(로그인전)
        http.anonymous().authorities(authorities);

        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/?timeout=true")); // 로그인폼URL

        /*
         * 로그인아이디/비밀번호
         * 파라미터명 설정
         */
        http.formLogin().usernameParameter("p_userid").passwordParameter("p_pswd") 
                .successHandler(httpsLoginSuccessHandler()).failureHandler(customAuthFailureHandler());

        http.logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutSuccessHandler());

        http.addFilterBefore(channelProcessingFilter, ChannelProcessingFilter.class); // http/https 필터적용
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class); // 인가 필터 적용
        http.addFilterAfter(new SiteChangeFilter(resSiteService), ExceptionTranslationFilter.class); // 사이트전환시 역할변경 필터 적용
        
        /* ajax로 호출시 sessionTimeout등 응답코드 대응 필터*/
        http.addFilterAfter(new AjaxSessionTimeoutFilter(), ExceptionTranslationFilter.class); 

        // http.csrf().disable(); // 주석풀지마세요!!! csrf공격대응 기능을 기본적으로 사용함(보안강화)
        //http.csrf().csrfTokenRepository(csrfTokenRepository()).ignoringAntMatchers("/onepass/acs.html");
        http.csrf().ignoringAntMatchers("/onepass/acs.html");

        http.headers().frameOptions().disable(); // iframe사용가능
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SecureChannelProcessor secureChannelProcessor() {
        Map<String, String> portMap = new HashMap<String, String>();

        /** Test시 -1이 들어와 원인파악과 해결전까지 임시로 사용 **/
        serverHttpsPort = "-1".equals(serverHttpsPort) ? "443" : serverHttpsPort;

        portMap.put(serverHttpPort, serverHttpsPort);
        PortMapperImpl portMapper = new PortMapperImpl();
        portMapper.setPortMappings(portMap);

        SecureChannelProcessor secureChannelProcessor = new SecureChannelProcessor();
        RetryWithHttpsEntryPoint entryPoint = new RetryWithHttpsEntryPoint();

        entryPoint.setPortMapper(portMapper);
        secureChannelProcessor.setEntryPoint(entryPoint);
        return secureChannelProcessor;
    }

    @Bean
    public InsecureChannelProcessor insecureChannelProcessor() {

        return new InsecureChannelProcessor();
    }

    @Bean
    public AffirmativeBased affirmativeBased() {
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        accessDecisionVoters.add(roleVoter);
        AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
        return affirmativeBased;
    }

    @PostConstruct
    public void channelProcessingFilter() {
        ChannelProcessingFilter channelProcessingFilter = new ChannelProcessingFilter();
        channelProcessingFilter.setChannelDecisionManager(channelDecisionManagerImpl());
        channelProcessingFilter.setSecurityMetadataSource(channelProcessingFilterMetadataSource);
        this.channelProcessingFilter = channelProcessingFilter;

    }

    @PostConstruct
    public void filterSecurityInterceptor() {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        filterSecurityInterceptor.setRejectPublicInvocations(true);
        filterSecurityInterceptor.setSecurityMetadataSource(filterSecurityInterceptorMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        this.filterSecurityInterceptor = filterSecurityInterceptor;
    }

    @Bean
    public WebInvocationPrivilegeEvaluator defaultWebInvocationPrivilegeEvaluator() {
        //return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
        return new CustomWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
    }

    @Bean
    public ChannelDecisionManagerImpl channelDecisionManagerImpl() {
        ChannelDecisionManagerImpl channelDecisionManagerImpl = new ChannelDecisionManagerImpl();
        List<ChannelProcessor> list = new ArrayList<ChannelProcessor>();
        if (sslIsUse) {
            list.add(secureChannelProcessor);
        }
        list.add(insecureChannelProcessor);
        channelDecisionManagerImpl.setChannelProcessors(list);
        return channelDecisionManagerImpl;
    }

    @Bean
    public HttpsLoginSuccessHandler httpsLoginSuccessHandler() throws Exception {
        HttpsLoginSuccessHandler httpsLoginSuccessHandler = new HttpsLoginSuccessHandler();
        return httpsLoginSuccessHandler;
    }
    
    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler() throws Exception {
        CustomLogoutSuccessHandler customLogoutSuccessHandler = new CustomLogoutSuccessHandler();
        return customLogoutSuccessHandler;
    }
    
    @Bean
    public CustomAuthFailureHandler customAuthFailureHandler() throws Exception {
        CustomAuthFailureHandler customAuthFailureHandler = new CustomAuthFailureHandler();
        return customAuthFailureHandler;
    }
    
    @Bean
    public CsrfTokenRepository csrfTokenRepository() throws Exception {
        CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
        return cookieCsrfTokenRepository;
    }
    
    /*
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }*/ // REDIS_SESSION
}
