package com.kbrainc.plum.mng.packageindvdChck.controller;

import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckAnsVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckArtclVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdTchaidVo;
import com.kbrainc.plum.mng.packageindvdChck.service.PackageindvdChckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 꾸러미개체 점검 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.controller
 * - PackageindvdChckController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckController
 * @Description : 꾸러미개체 점검 컨트롤러 클래스
 * @date : 2023. 03. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/packageindvdChck")
public class PackageindvdChckController {
    @Autowired
    private PackageindvdChckService packageindvdChckService;


    /**
     * 꾸러미 개체 점검 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : packageindvdChckList
     * @Description : 꾸러미 개체 점검 목록 화면
     */
    @GetMapping("/packageindvdChckList.html")
    public String packageindvdChckList() throws Exception {
        return "/mng/packageindvdChck/packageindvdChckList";
    }

    @GetMapping("/packageindvdChckDetail.html")
    public String packageindvdChckDetail(PackageindvdChckVo packageindvdChckVo, Model model) throws Exception {
        PackageindvdChckVo packageindvdChck = packageindvdChckService.selectPackageindvdChck(packageindvdChckVo);
        List<PackageindvdTchaidVo> packageindvdTchaids = packageindvdChckService.selectPackageindvdTchaidList(packageindvdChck); //꾸러미객체에 해당하는 교구 리스트
        List<PackageindvdChckArtclVo> packageindvdChckArtcls = packageindvdChckService.selectPackageindvdChckArtclList(packageindvdChck); // 양식리스트

        List<PackageindvdChckAnsVo> packageindvdChckAns = packageindvdChckService.selectPackageindvdChckAnsList(packageindvdChck);

        model.addAttribute("packageindvdChck", packageindvdChck); //꾸러미 점검 정보
        model.addAttribute("packageindvdTchaids", packageindvdTchaids); //꾸러미 교구 리스트
        model.addAttribute("packageindvdChckAns", packageindvdChckAns);
        model.addAttribute("packageindvdChckArtcls", packageindvdChckArtcls);

        return "/mng/packageindvdChck/packageindvdChckDetail";
    }

    /**
     * 꾸러미개체 점검 목록 조회
     *
     * @return map
     * @throws Exception
     * @Title : selectPackageindvdChckList
     * @Description : 꾸러미개체 점검 목록 조회
     */
    @GetMapping("/selectPackageindvdChckList.do")
    @ResponseBody
    public Map<String,Object> selectPackageindvdChckList(PackageindvdChckVo packageIndvdChckVo) throws Exception {
        Map<String,Object> result = new HashMap<>();

        List<PackageindvdChckVo> list = packageindvdChckService.selectPackageindvdChckList(packageIndvdChckVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }
}
