package com.kbrainc.plum.mng.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class NoticeController {


    @RequestMapping(value="/mng/notice/noticeList.html")
    public String noticeList() throws Exception {
        return "mng/notice/noticeList";
    }

    @RequestMapping(value="/mng/notice/selectMemberList.do")
    @ResponseBody
    public Map<String,Object> selectNoticeList() {
        Map<String, Object> result = new HashMap<>();

        return result;
    }
}
