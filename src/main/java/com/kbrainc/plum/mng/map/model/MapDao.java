package com.kbrainc.plum.mng.map.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface MapDao {

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
	* @param insertMapList
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertMap(MapVo mapVo) throws Exception;

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

}
