package com.kbrainc.plum.front.envLrnQlfcFnshHstry.service;

import java.util.List;

import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryVo;

public interface EnvLrnQlfcFnshHstryService {

    List<EnvLrnQlfcFnshHstryVo> selectFnshInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    EnvLrnQlfcFnshHstryVo selectFnshInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    List<EnvLrnQlfcFnshHstryVo> selectQlfcInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    EnvLrnQlfcFnshHstryVo selectQlfcInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

}
