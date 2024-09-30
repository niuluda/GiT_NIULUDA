package com.example.controller;


import com.example.service.CostBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


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


}
