package com.example.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportExcelTemplateDto {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private String id;


    /**
     * 业务数据类型
     */
    @ExcelIgnore
    private Integer bizType;

    /**
     * 业务数据类型名称
     */
    @ExcelProperty(value = "类型")
    private String bizName;


    /**
     * 套餐编号
     */
    @ExcelProperty("套餐编号")
    private String detailId;

    /**
     * 税率编号
     */
    @ExcelProperty("税率编号")
    private String taxId;


    /**
     * 规模
     */
    @ExcelProperty({"卡数量(张)"})
    private Integer wirelessScale;

    /**
     * 规模
     */
    @ExcelProperty({"条数(条)或公里(km)"})
    private Integer wiredScale;

    /**
     * 规模
     */
    @ExcelProperty({"发送量(条)"})
    private Integer messageScale;
    /**
     * 规模
     */
    @ExcelProperty({"规模(张或条)"})
    private Integer fiveGScale;


    /**
     * 税额
     */
    @ExcelProperty("税额(万元)")
    private BigDecimal amountTax;

    /**
     * 税率
     */
    @ExcelProperty("税率(%)")
    private BigDecimal rateTax;

    /**
     * 发票金额
     */
    @ExcelProperty(value = "发票金额(万元)")
    private BigDecimal invoiceAmount;


    private static final long serialVersionUID = 1L;

}
