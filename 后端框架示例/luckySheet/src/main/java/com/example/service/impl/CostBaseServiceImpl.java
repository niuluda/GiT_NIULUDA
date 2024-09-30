package com.example.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easyExcel.CustomColumnStyleHandler;
import com.example.easyExcel.ExcelMergeHandler;
import com.example.easyExcel.ExcelStyleHandler;
import com.example.easyExcel.ExcelTemplateStyleColumnHandler;
import com.example.mapper.CostBaseMapper;
import com.example.model.CostBase;
import com.example.model.CostBaseDto;
import com.example.service.CostBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author niuluda
 * @description cost_base费用结算主表业务实现类
 * @createDate:2024-07-10 15:43:22
 */
@Slf4j
@Service
@CrossOrigin(value = "*")
public class CostBaseServiceImpl extends ServiceImpl<CostBaseMapper, CostBase> implements CostBaseService {

    @Resource
    private CostBaseMapper costBaseMapper;




    @Override
    public void exportExcel(HttpServletResponse response, String[] ids) throws Exception {
        List<CostBaseDto> costBases = costBaseMapper.selectAllSubByIds(ids);
        if (!CollectionUtils.isEmpty(costBases)) {
            //导出结算数据按照业务类型区分
            exportExcelGroupByBusiness(response, costBases);
            return;
        }
        throw new Exception("未查询到数据!");
    }



    @Override
    public void editExcel(HttpServletResponse response, String[] ids) throws Exception {
        List<CostBaseDto> costBases = costBaseMapper.selectAllSubByIds(ids);
        if (!CollectionUtils.isEmpty(costBases)) {
            //筛选业务类型
            List<CostBaseDto> wireless = costBases.stream().filter(i -> i.getBizType() == 1).collect(Collectors.toList());
            List<CostBaseDto> wired = costBases.stream().filter(i -> i.getBizType() == 2).collect(Collectors.toList());
            List<CostBaseDto> speech = costBases.stream().filter(i -> i.getBizType() == 3).collect(Collectors.toList());
            List<CostBaseDto> message = costBases.stream().filter(i -> i.getBizType() == 4).collect(Collectors.toList());
            List<CostBaseDto> fiveG = costBases.stream().filter(i -> i.getBizType() == 5).collect(Collectors.toList());
            ExcelWriter excelWriter = null;
            try {
                excelWriter = EasyExcel.write(response.getOutputStream(), CostBaseDto.class).registerWriteHandler(new CustomColumnStyleHandler(new int[]{12, 13, 14, 15}, IndexedColors.YELLOW.getIndex())).registerWriteHandler(new ExcelMergeHandler(new int[]{0, 1, 2, 3, 4, 5, 6, 7},new int[]{9,10,13}, 1)).registerWriteHandler(new ExcelTemplateStyleColumnHandler()).build();
                for (int i = 1; i <= 5; i++) {
                    if (i == 1) {
                        //无线业务
                        if (!CollectionUtils.isEmpty(wireless)) {
                            WriteSheet writeSheet = EasyExcel.writerSheet("无线业务").build();
                            excelWriter.write(wireless, writeSheet);
                        }
                    } else if (i == 2) {
                        //有线业务
                        if (!CollectionUtils.isEmpty(wired)) {
                            WriteSheet writeSheet = EasyExcel.writerSheet("有线业务").build();
                            excelWriter.write(wired, writeSheet);
                        }
                    } else if (i == 3) {
                        //语音业务
                        if (!CollectionUtils.isEmpty(speech)) {
                            WriteSheet writeSheet = EasyExcel.writerSheet("语音业务").build();
                            excelWriter.write(speech, writeSheet);
                        }
                    } else if (i == 4) {
                        //短信业务
                        if (!CollectionUtils.isEmpty(message)) {
                            WriteSheet writeSheet = EasyExcel.writerSheet("短信业务").build();
                            excelWriter.write(message, writeSheet);
                        }
                    } else if (i == 5) {
                        //5G业务
                        if (!CollectionUtils.isEmpty(fiveG)) {
                            WriteSheet writeSheet = EasyExcel.writerSheet("5G业务").build();
                            excelWriter.write(fiveG, writeSheet);
                        }
                    }
                }
            } catch (IOException exception) {
                log.error(exception.getMessage());
            } finally {
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
            return;
        }
        throw new Exception("未查询到数据!");
    }


    /**
     * 导出各个业务数据
     *
     * @param response
     * @param dataList
     * @param <T>
     */
    public static <T> void exportExcelGroupByBusiness(HttpServletResponse response, List<CostBaseDto> dataList) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream(), CostBaseDto.class).registerWriteHandler(new ExcelTemplateStyleColumnHandler()).registerWriteHandler(new HorizontalCellStyleStrategy(ExcelStyleHandler.getHeadStyle(), ExcelStyleHandler.getContentStyle())).registerWriteHandler(new ExcelMergeHandler(new int[]{0, 1, 2, 3, 4, 5, 6, 7},new int[]{9,10,13} ,1)).build();
            if (excelWriter != null) {
                if (!CollectionUtils.isEmpty(dataList)) {
                    //筛选业务类型
                    List<CostBaseDto> wireless = dataList.stream().filter(i -> i.getBizType() == 1).collect(Collectors.toList());
                    List<CostBaseDto> wired = dataList.stream().filter(i -> i.getBizType() == 2).collect(Collectors.toList());
                    List<CostBaseDto> speech = dataList.stream().filter(i -> i.getBizType() == 3).collect(Collectors.toList());
                    List<CostBaseDto> message = dataList.stream().filter(i -> i.getBizType() == 4).collect(Collectors.toList());
                    List<CostBaseDto> fiveG = dataList.stream().filter(i -> i.getBizType() == 5).collect(Collectors.toList());
                    for (int i = 1; i <= 5; i++) {
                        if (i == 1) {
                            //无线业务
                            if (!CollectionUtils.isEmpty(wireless)) {
                                WriteSheet writeSheet = EasyExcel.writerSheet("无线业务").build();
                                excelWriter.write(wireless, writeSheet);
                            }
                        } else if (i == 2) {
                            //有线业务
                            if (!CollectionUtils.isEmpty(wired)) {
                                WriteSheet writeSheet = EasyExcel.writerSheet("有线业务").build();
                                excelWriter.write(wired, writeSheet);
                            }
                        } else if (i == 3) {
                            //语音业务
                            if (!CollectionUtils.isEmpty(speech)) {
                                WriteSheet writeSheet = EasyExcel.writerSheet("语音业务").build();
                                excelWriter.write(speech, writeSheet);
                            }
                        } else if (i == 4) {
                            //短信业务
                            if (!CollectionUtils.isEmpty(message)) {
                                WriteSheet writeSheet = EasyExcel.writerSheet("短信业务").build();
                                excelWriter.write(message, writeSheet);
                            }
                        } else if (i == 5) {
                            //5G业务
                            if (!CollectionUtils.isEmpty(fiveG)) {
                                WriteSheet writeSheet = EasyExcel.writerSheet("5G业务").build();
                                excelWriter.write(fiveG, writeSheet);
                            }
                        }
                    }
                }
            }
        } catch (IOException exception) {
            log.error(exception.getMessage());

        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    @Override
    public boolean save(CostBase entity) {
        return super.save(entity);
    }
}




