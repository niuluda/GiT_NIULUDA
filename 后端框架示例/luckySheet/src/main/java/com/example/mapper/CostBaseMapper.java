package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.CostBase;
import com.example.model.CostBaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author niuluda
 * @description cost_base费用结算Mapper
 * @createDate:2024-07-10 15:43:22
 */
@Mapper
public interface CostBaseMapper extends BaseMapper<CostBase> {


    /**
     * 费用结算查询主表及子表数据
     *
     * @param ids
     * @return
     */
    List<CostBaseDto> selectAllSubByIds(@Param("ids") String[] ids);



}




