package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 运营商套餐详情表
 * @TableName cost_package_details
 */
@TableName(value ="cost_package_details")
@Data
@Accessors(chain = true)
public class CostPackageDetails  implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 结算id
     */
    @TableField(value = "cost_id")
    private String costId;

    /**
     * 运营商类型
     */
    @TableField(value = "operator_type")
    private String operatorType;

    /**
     * 运营商名称
     */
    @TableField(value = "operator_name")
    private String operatorName;


    /**
     * 流量
     */
    @TableField(value = "flow")
    private String flow;

    /**
     * 规模
     */
    @TableField(value = "scale")
    private Integer scale;

    /**
     * 序号
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 税额
     */
    @TableField(value = "amount_tax")
    private BigDecimal amountTax;

    /**
     * 税率
     */
    @TableField(value = "rate_tax")
    private BigDecimal rateTax;

    /**
     * 发票金额
     */
    @TableField(value = "invoice_amount")
    private BigDecimal invoiceAmount;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}