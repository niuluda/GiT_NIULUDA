package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.CostBase;
import javax.servlet.http.HttpServletResponse;



/**
 * @author niuluda
 * @description cost_base费用结算主表业务接口
 * @createDate:2024-07-10 15:43:22
 */
public interface CostBaseService extends IService<CostBase> {
    /**
     * 导出结算excel
     *
     * @param response
     * @param ids
     */
    void exportExcel(HttpServletResponse response, String[] ids) throws Exception;



    /**
     * 在线编辑excel
     * @param response
     * @param ids
     */
    void editExcel(HttpServletResponse response, String[] ids) throws Exception;


}
