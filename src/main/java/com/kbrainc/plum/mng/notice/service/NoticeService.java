package com.kbrainc.plum.mng.notice.service;

import com.kbrainc.plum.mng.inqry.model.InqryVo;
import com.kbrainc.plum.mng.notice.model.NoticeVo;

import java.util.List;

public interface NoticeService {
    public List<NoticeVo> selectNoticeList(NoticeVo noticeVo) throws Exception;
}
