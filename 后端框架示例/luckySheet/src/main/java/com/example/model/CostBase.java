package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 费用结算主表

 * @TableName cost_base
 */
@TableName(value ="cost_base")
@Data
@Accessors(chain = true)
public class CostBase  implements Serializable  {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 结算单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 申报单位 isc主键
     */
    @TableField(value = "org_id")
    private String orgId;

    /**
     * 申报单位
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 申报部门 isc主键
     */
    @TableField(value = "dept_id")
    private String deptId;

    /**
     * 申报部门
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 业务用途code值
     */
    @TableField(value = "biz_purpose_id")
    private String bizPurposeId;

    /**
     * 业务用途
     */
    @TableField(value = "biz_purpose")
    private String bizPurpose;

    /**
     * 业务部门
     */
    @TableField(value = "biz_dept_code")
    private String bizDeptCode;

    /**
     * 业务部门
     */
    @TableField(value = "biz_dept_name")
    private String bizDeptName;

    /**
     * 业务数据类型
     */
    @TableField(value = "biz_type")
    private Integer bizType;

    /**
     * 业务数据类型名称
     */
    @TableField(value = "biz_name")
    private String bizName;

    /**
     * 月份
     */
    @TableField(value = "month")
    private String month;

    /**
     * 套餐详情
     */
    @TableField(exist = false)
    private List<CostPackageDetails> costPackageDetails;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}