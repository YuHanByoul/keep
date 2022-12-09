package com.kbrainc.plum.mng.notice.service;

import com.kbrainc.plum.mng.inqry.service.InqryService;
import com.kbrainc.plum.mng.notice.model.NoticeDao;
import com.kbrainc.plum.mng.notice.model.NoticeVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mng.notice.noticeService")
public class NoticeServiceImpl extends PlumAbstractServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<NoticeVo> selectNoticeList(NoticeVo noticeVo) throws Exception {
        return noticeDao.selectNoticeList(noticeVo);
    }
}
