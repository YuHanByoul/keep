package com.kbrainc.plum.mng.notice.model;

import com.kbrainc.plum.mng.inqry.model.InqryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDao {
    public List<NoticeVo> selectNoticeList(NoticeVo noticeVo) throws Exception;
}
