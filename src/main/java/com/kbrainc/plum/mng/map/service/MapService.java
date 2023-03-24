package com.kbrainc.plum.mng.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kbrainc.plum.mng.map.model.MapVo;
import com.kbrainc.plum.rte.model.UserVo;

public interface MapService {

	/**
	* 지도기반데이터 목록 조회
	*
	* @Title : selectMapList
	* @Description : 지도기반데이터 목록 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return List<MapVo>
	*/
	public List<MapVo> selectMapList(MapVo mapVo) throws Exception;

	/**
	* 지도데이터 등록
	*
	* @Title : insertMap
	* @Description : 지도데이터 등록
	* @param mapList
	* @param user
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertMap(MapVo mapVo) throws Exception ;

	/**
	* 시도목록 조회
	*
	* @Title : selectCtprvnList
	* @Description : 시도목록 조회
	* @return
	* @throws Exception
	* @return List<MapVo>
	*/
	public List<MapVo> selectCtprvnList() throws Exception ;

	/**
	* 지도기반데이터 조회
	*
	* @Title : selectMap
	* @Description : 지도기반데이터 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return MapVo
	*/
	public MapVo selectMap(MapVo mapVo) throws Exception ;

	/**
	* 지도기반데이터 수정
	*
	* @Title : updateMap
	* @Description : 지도기반데이터 수정
	* @param mapVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateMap(MapVo mapVo) throws Exception ;

	/**
	* 지도기반데이터 엑셀데이터 체크
	*
	* @Title : mapExcelDatalValidationCheck
	* @Description : 지도기반데이터 엑셀데이터 체크
	* @param excelList
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	public Map<String, Object> mapExcelDatalValidationCheck(ArrayList excelList) throws Exception;

}
