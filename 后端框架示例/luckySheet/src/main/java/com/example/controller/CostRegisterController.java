package com.example.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.model.Celldata;
import com.example.model.NoticeWebsocketResp;
import com.example.model.SheetOption;
import com.example.service.CostBaseService;
import com.example.websocket.server.NoticeWebsocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author niuluda
 * @description 费用结算
 * @createDate:2024-07-10 15:43:22
 */
@Slf4j
@RequestMapping("/cost/register")
@RestController
@Validated
public class CostRegisterController {
    @Resource
    private CostBaseService costBaseService;
    @Resource
    private NoticeWebsocket noticeWebsocket;



    /**
     * 导出excel
     *
     * @param response
     * @param ids
     */
    @PostMapping("exportExcel")
    public void exportExcel(HttpServletResponse response,  String[] ids) throws Exception {
        costBaseService.exportExcel(response, ids);
    }

    /**
     * 编辑excel
     *
     * @param response
     * @param ids
     */
    @PostMapping("editExcel")
    public void editExcel(HttpServletResponse response, String[] ids) throws Exception {
        costBaseService.editExcel(response, ids);
    }

    @PostMapping("cooperate")
    public String cooperate(){
        List<SheetOption> list = new ArrayList<>();
        Map keys = new HashMap();
        SheetOption stop = new SheetOption();
        //设置sheet页名
        stop.setName("options");
        //设置sheet页索引
        stop.setIndex("1");
        stop.setStatus(1);
        stop.setHide(0);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                //随机生成点数据
                Celldata celldata = new Celldata(i + "", j + "", i + j + "", new Random().nextInt(500) + "");
                stop.getCelldata().add(celldata);
            }
        }

        list.add(stop);
        return JSONArray.toJSONString(list);
    }

    @GetMapping("/websocket/send")
    public void sendMessage(String uid){
        NoticeWebsocketResp noticeWebsocketResp=new NoticeWebsocketResp();
        noticeWebsocketResp.setType("type");
        noticeWebsocketResp.setNoticeInfo(3);
        NoticeWebsocket.sendMessageByUserId(uid,noticeWebsocketResp);
    }


}
