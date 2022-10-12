package com.kbrainc.plum.rte.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ResCodeDao;
import com.kbrainc.plum.rte.model.ResSiteDao;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.security.ReloadableChannelProcessingFilterMetadataSource;
import com.kbrainc.plum.rte.security.ReloadableFilterSecurityInterceptorMetadataSource;
import com.kbrainc.plum.rte.service.ResMenuServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.bootstrap.BootstrapCacheLoaderFactory;
import net.sf.ehcache.event.CacheEventListener;

public class MyBootstrapCacheLoaderFactory extends BootstrapCacheLoaderFactory implements BootstrapCacheLoader {

    public MyBootstrapCacheLoaderFactory() {
    	super();
    	// TODO Auto-generated constructor stub
    }

    @Override
    public BootstrapCacheLoader createBootstrapCacheLoader(Properties properties) {
    	// TODO Auto-generated method stub
    	return new MyBootstrapCacheLoaderFactory();
    }

    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public boolean isAsynchronous() {
    	// TODO Auto-generated method stub
    	return false;
    }

    public void load(Ehcache myCache) throws CacheException {
    	try {
    	    //place calls to the method that need to be cached – myService.findPagesWithText(bkReadByAll, textSearchedByAll)
    	    //System.out.println("######### load #########");
    	    //System.out.println(myCache.getName());
    		//myCache.getCacheEventNotificationService().
    		String cacheName = myCache.getName();
    	    if ("codeMap".equals(cacheName)) {
    	    	/*boolean registerListener = myCache.getCacheEventNotificationService().registerListener(new CacheEventListener() {//RegisteredEventListeners
    
    	    		@Override
    	    	    public Object clone() throws CloneNotSupportedException {
    	    	        return super.clone();
    	    	    }
    	    		
    	            public void notifyElementRemoved(Ehcache arg0, Element arg1)
    	                    throws CacheException {
    	                // TODO Auto-generated method stub
    	                System.out.println("notifyElementRemoved cache=" + arg0 + "  element=" + arg1);
    	            }
    
    				@Override
    				public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    					
    				}
    
    				@Override
    				public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    					
    				}
    
    				@Override
    				public void notifyElementExpired(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    					
    				}
    
    				@Override
    				public void notifyElementEvicted(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    					System.out.println("notifyElementRemoved cache=" + cache + "  element=" + element);
    				}
    
    				@Override
    				public void notifyRemoveAll(Ehcache cache) {
    					// TODO Auto-generated method stub
    					
    				}
    
    				@Override
    				public void dispose() {
    					// TODO Auto-generated method stub
    					
    				}
    
    	        });*/
    	        //System.out.println("코드맵 캐시로드 작업");
    	        
    	        Ehcache codeMap = myCache;
    	        Ehcache codeListMap = null;
    	        
    	        myCache.getCacheManager().addCache("codeListMap");
    	        codeListMap = myCache.getCacheManager().getCache("codeListMap");
    	        //System.out.println(codeListMap.get("test").getObjectValue());
    	        //System.out.println("BREAK");
    	        
    	        ResCodeDao resCodeDao = (ResCodeDao) CommonUtil.getBean("resCodeDao");
    	        List<CodeInfoVo> allCodeInfoList = resCodeDao.selectAllCodeInfoList();
    	        List<CodeInfoVo> codeInfoList = null;
    	        Element element = null;
    
    	        for (CodeInfoVo codeInfo : allCodeInfoList) {
    	            codeMap.put(new Element("CODE|" + codeInfo.getCd(), codeInfo), true);
    	            element = codeListMap.get(codeInfo.getCdgrpid() + "|" + codeInfo.getUpprCd());
    	            
    	            if (element == null) {
    	                codeInfoList = new ArrayList<CodeInfoVo>();
    	                codeInfoList.add(codeInfo);
    	                codeListMap.put(new Element(codeInfo.getCdgrpid() + "|" + codeInfo.getUpprCd(), codeInfoList), true);
    	            } else {
    	                codeInfoList = (List<CodeInfoVo>) element.getObjectValue();    
    	                codeInfoList.add(codeInfo);
    	                codeListMap.put(new Element(codeInfo.getCdgrpid() + "|" + codeInfo.getUpprCd(), codeInfoList), true);
    	            }
    	        }
    	        
    	        //System.out.println("BREAK");
    	    }/* else if ("codeListMap".equals(myCache.getName())) {
                    System.out.println("코드리스트맵 캐시로드 작업");
                    myCache.put(new Element("test", "123"));
                }*/
    	    //myCache.put(new Element("key2", "value1"), true); // 초기코드는 true로 해서 다른 노드에 전파하지 않도록 한다.
    	    //myCache.put(new Element("abc", "value1")); // 전파 AND 덮어쓰기
    	    /*CodeInfo codeInfo = new CodeInfo();
    	    codeInfo.setId("0001");
    	    codeInfo.setNm("기술연구소");
    	    codeInfo.setDesc("R&D를 담당하는 기술연구소");
    	    myCache.put(new Element("jsk", codeInfo));*/
    	    else if ("menuMap".equals(cacheName)) {
    	    	ResSiteDao resSiteDao = (ResSiteDao) CommonUtil.getBean("resSiteDao");
    	    	List<SiteInfoVo> siteidList = resSiteDao.selectSiteidList();
    	    	for (SiteInfoVo siteInfo : siteidList) {
    	    		myCache.put(new Element(siteInfo.getSiteid(), true));
    	    	}
    	    	
    	    	myCache.getCacheEventNotificationService().registerListener(new CacheEventListener() {
    	    		
    	    		@Override
    	    	    public Object clone() throws CloneNotSupportedException {
    	    	        return super.clone();
    	    	    }
    	    		
    	    		@Override
    	            public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
    	    			try {
    	    				ResMenuServiceImpl resMenuService = (ResMenuServiceImpl) CommonUtil.getBean("cmm.resMenuService");
    	    				resMenuService.makeTreeMenuInfoSite(element.getObjectKey().toString());
    						cache.put(new Element(element.getObjectKey().toString(), true));
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    	            }
    
    				@Override
    				public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementExpired(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementEvicted(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyRemoveAll(Ehcache cache) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void dispose() {
    					// TODO Auto-generated method stub
    				}
    	        });
    	    } else if ("authMap".equals(cacheName)) {
    	    	myCache.put(new Element("auth", true));
    	    	
    	    	myCache.getCacheEventNotificationService().registerListener(new CacheEventListener() {
    	    		
    	    		@Override
    	    	    public Object clone() throws CloneNotSupportedException {
    	    	        return super.clone();
    	    	    }
    	    		
    	    		@Override
    	            public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
    	    			try {
    	    				ReloadableChannelProcessingFilterMetadataSource reloadableChannelProcessingFilterMetadataSource = (ReloadableChannelProcessingFilterMetadataSource) CommonUtil.getBean("reloadableChannelProcessingFilterMetadataSource");
    	    				ReloadableFilterSecurityInterceptorMetadataSource reloadableFilterSecurityInterceptorMetadataSource = (ReloadableFilterSecurityInterceptorMetadataSource) CommonUtil.getBean("reloadableFilterSecurityInterceptorMetadataSource");
    	    				reloadableChannelProcessingFilterMetadataSource.reload();
    	    		        reloadableFilterSecurityInterceptorMetadataSource.reload();
    						cache.put(new Element("auth", true));
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    	            }
    
    				@Override
    				public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementExpired(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyElementEvicted(Ehcache cache, Element element) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void notifyRemoveAll(Ehcache cache) {
    					// TODO Auto-generated method stub
    				}
    
    				@Override
    				public void dispose() {
    					// TODO Auto-generated method stub
    				}
    	        });
    	    }
    	} catch (Exception e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    }
}

/*class CodeInfo {
	private String id;
	private String nm;
	private String desc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}*/