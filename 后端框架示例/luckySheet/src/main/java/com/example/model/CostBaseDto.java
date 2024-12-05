package com.example.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用结算合并实体
 */
@Data
@NoArgsConstructor
public class CostBaseDto extends BaseModelDto implements Serializable {
    /**
     * 主键
     */
    @ExcelProperty("编号")
    private String id;

    /**
     * 结算单号
     */
    @ColumnWidth(30)
    @ExcelProperty(value = "结算单号")
    private String orderNo;

    /**
     * 申报单位 isc主键
     */
    @ExcelIgnore
    private String orgId;

    /**
     * 申报单位
     */
    @ColumnWidth(30)
    @ExcelProperty(value = "单位")
    private String orgName;

    /**
     * 申报部门 isc主键
     */
    @ExcelIgnore
    private String deptId;

    /**
     * 申报部门
     */
    @ExcelProperty(value = "专业")
    private String deptName;

    /**CostRegisterController.java
     * 业务用途code值
     */
    @ExcelIgnore
    @TableField(value = "biz_purpose_id")
    private String bizPurposeId;

    /**
     * 业务用途
     */
    @ColumnWidth(25)
    @ExcelProperty("业务用途")
    private String bizPurpose;

    /**
     * 业务部门
     */
    @ExcelIgnore
    private String bizDeptCode;

    /**
     * 业务部门
     */
    @ExcelProperty(value = "业务部门")
    private String bizDeptName;

    /**
     * 业务数据类型
     */
    @ExcelIgnore
    @TableField(value = "biz_type")
    private Integer bizType;

    /**
     * 业务数据类型名称
     */
    @ColumnWidth(25)
    @ExcelProperty(value = "类型")
    @TableField(value = "biz_name")
    private String bizName;

    /**
     * 结算类型
     */
    @ExcelIgnore
    private Integer type;

    /**
     * 结算状态
     */
    @ExcelIgnore
    private Integer state;

    /**
     * 月份
     */
    @ExcelProperty("月份")
    private String month;


    /**
     * 子结算id
     */
    @ExcelProperty("套餐编号")
    private String detailId;

    /**
     * 运营商名称
     */
    @ExcelProperty(value = "运营商名称")
    private String operatorName;

    /**
     * 运营商套餐
     */
    @ExcelIgnore
    private String packageId;

    /**
     * 运营商套餐
     */
    @ExcelProperty(value = "套餐")
    private String packageName;
    /**
     * 流量
     */
    @ExcelProperty(value = "流量(M)")
    private String flow;

    /**
     * 规模
     */
    @ExcelProperty({"规模"})
    private Integer scale;

    /**
     * 新增卡规模
     */
    @ExcelIgnore
    private Integer newCardScale;

    /**
     * 异常卡规模
     */
    @ExcelIgnore
    private Integer unUsualCardScale;

    /**
     * 序号
     */
    @ExcelIgnore
    private Integer sort;

    /**
     * 发票金额
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "发票金额(万元)")
    private BigDecimal invoiceAmount;

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
     * taxId
     */
    @ExcelProperty("税率编号")
    private String taxId;

    /**
     * 回报指数
     */
    @ExcelIgnore
    private BigDecimal rewardTax;

    /**
     * 节支
     */
    @ExcelIgnore
    private BigDecimal saveAccu;



    /**
     * 申报金额
     */
    @ExcelIgnore
    private BigDecimal planeAmount;

    /**
     * 结算金额
     */
    @ExcelIgnore
    private BigDecimal costAmount;

    /**
     * 剩余申报金额
     */
    @ExcelIgnore
    private BigDecimal leftPlaneAmount;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelIgnore
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
