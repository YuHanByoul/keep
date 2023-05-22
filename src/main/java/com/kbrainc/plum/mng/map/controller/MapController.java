package com.kbrainc.plum.mng.map.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnAnsVo;
import com.kbrainc.plum.mng.example.excel.service.ExcelService;
import com.kbrainc.plum.mng.map.model.MapVo;
import com.kbrainc.plum.mng.map.service.MapService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 지도기반데이터 관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.map.controller
* - MapController.java
* </pre>
*
* @ClassName : MapController
* @Description : 지도기반데이터 관리 컨트롤러
* @author : kbrain
* @date : 2023. 3. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
@Slf4j
public class MapController {

	@Autowired
    private MapService mapService;

	@Autowired
	ExcelService excelService;

	/**
	* 지도기반데이터관리 목록 화면이동
	*
	* @Title : mapMngList
	* @Description : 지도기반데이터관리 목록 화면이동
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/mng/map/mapMngList.html")
    public String mapMngList(Model model) throws Exception {
		model.addAttribute("sidoList", mapService.selectCtprvnList());
		model.addAttribute("grpcd", "155");
        return "mng/map/mapMngList";
    }

	/**
	* 지도기반데이터 목록 조회
	*
	* @Title : selectMapList
	* @Description : 지도기반데이터 목록 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/map/selectMapList.do")
    @ResponseBody
    public Map<String, Object> selectMapList(MapVo mapVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MapVo> result = null;

        result = mapService.selectMapList(mapVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

	/**
	* 지도기반데이터 등록 화면 이동
	*
	* @Title : mapInsertForm
	* @Description : 지도기반데이터 등록 화면 이동
	* @param mapVo
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/mng/map/mapInsertForm.html")
    public String mapInsertForm(MapVo mapVo, Model model) throws Exception {
		model.addAttribute("sidoList", mapService.selectCtprvnList());
        return "mng/map/mapInsert";
    }

	/**
	* 지도기반데이터 수정 화면 이동
	*
	* @Title : mapUpdateForm
	* @Description : 지도기반데이터 수정 화면 이동
	* @param mapVo
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/mng/map/mapUpdateForm.html")
	public String mapUpdateForm(MapVo mapVo, Model model) throws Exception {
		model.addAttribute("mapInfo", mapService.selectMap(mapVo));
		model.addAttribute("sidoList", mapService.selectCtprvnList());
		return "mng/map/mapUpdate";
	}


	/**
	* 지도기반데이터 등록
	*
	* @Title : insertInst
	* @Description : 지도기반데이터 등록
	* @param mapList
	* @param bindingResult1
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/map/insertMap.do")
    @ResponseBody
    public Map<String, Object> insertMap(MapVo mapVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        int retVal = 0;

        mapVo.setUser(user);
        retVal = mapService.insertMap(mapVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

	/**
	* 지도기반데이터 수정
	*
	* @Title : updateMap
	* @Description : 지도기반데이터 수정
	* @param mapVo
	* @param bindingResult1
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/map/updateMap.do")
	@ResponseBody
	public Map<String, Object> updateMap(MapVo mapVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int retVal = 0;

		mapVo.setUser(user);
		retVal = mapService.updateMap(mapVo);

		if (retVal > 0) {
			resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
			resultMap.put("msg", "등록에 성공하였습니다.");
		} else {
			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
			resultMap.put("msg", "등록에 실패했습니다.");
		}

		return resultMap;
	}

	/**
	* 지도기반데이터 등록 팝업
	*
	* @Title : mapInsertPopup
	* @Description : 지도기반데이터 등록 팝업
	* @param mapVo
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/mng/map/mapInsertPopup.html")
    public String mapInsertPopup(MapVo mapVo, Model model) throws Exception {
		//model.addAttribute("sidoList", mapService.selectCtprvnList());
        return "mng/map/mapInsertPopup";
    }


	/**
	* 지도기반데이터 엑셀 정합성 체크
	*
	* @Title : mapExcelDataCheck
	* @Description : 지도기반데이터 엑셀 정합성 체크
	* @param multiRequest
	* @param response
	* @param model
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping("/mng/map/mapExcelDataCheck.do")
    @ResponseBody
	public Map<String,Object> mapExcelDataCheck (MultipartHttpServletRequest multiRequest, HttpServletResponse response, MapVo mapVo)throws Exception{

		Map<String,Object> map = new HashMap();
		MultipartFile file = multiRequest.getFile("p_excelFile");
		ArrayList excelList = null;
		//엑셀내용을 리스트로
		String oriFilename = null;

		try {
			if (file != null) {
			    oriFilename = file.getOriginalFilename();
			}
			if (oriFilename != null) {
	    		if(oriFilename.indexOf(".xlsx") >-1){
	    			excelList = ExcelUtil.getExcelPoiArrayList(file.getInputStream());
	    		}else if(oriFilename.indexOf(".xls") >-1){
	    			excelList = ExcelUtil.getExcelJxlArrayList(file.getInputStream());
	    		}
			}

			String resrceSeCd = mapVo.getResrceSeCd();
 			Map<String,Object> result = mapService.mapExcelDatalValidationCheck(excelList,resrceSeCd);//엑셀 체크

			map.put("checkList" ,(ArrayList)result.get("checkList"));
		} catch (IndexOutOfBoundsException e) {
			map.put("msg" ,"엑셀 정합성 체크중에 에러가 발생하였습니다. 양식을 확인 하여 주십시오");
        } catch (Exception e) {
        	map.put("msg" , e.getMessage());
        }
		return map;
	}

	/**
	* 지도기반데이터 목록 엑셀 등록
	*
	* @Title : insertMapListExcel
	* @Description : 지도기반데이터 목록 엑셀 등록
	* @param mapList
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/map/insertMapListExcel.do")
    @ResponseBody
    public Map<String, Object> insertMapListExcel(@RequestParam("mapList") String mapList , @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        ObjectMapper mapper = new ObjectMapper();
        List<MapVo> mapVoList = mapper.readValue(mapList, new TypeReference<>(){});

        int retVal = 0;
        retVal = mapService.insertMapList(mapVoList, user);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

}
