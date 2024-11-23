package com.example.easyExcel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author niuluda
 * @description 模板自定义列根据业务类型
 * @createDate:2024-07-20 14:43:22
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelTemplateStyleColumnHandler extends AbstractColumnWidthStyleStrategy {
    //业务类型
    private Integer bizType;

    /**
     * 根据业务类型判断模板类型
     * 业务类型为空时按照工作表名称判断模板类型
     * @param writeSheetHolder
     * @param cellDataList
     * @param cell
     * @param head
     * @param relativeRowIndex
     * @param isHead
     */
    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        //11、12列:规模 列宽底层代码需要:设置数/256
        //@ColumnWidth优先级高于类样式
        if (isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            int columnIndex = cell.getColumnIndex();
            if (columnIndex == 0 || columnIndex == 8 ||columnIndex == 16) {
                //隐藏id
                sheet.setColumnWidth(columnIndex, 0);
            }else if(columnIndex == 11||columnIndex == 12){
                //由于数据类型不同导致模板名称所需宽度不同统一设置流量和规模列和套餐列
                sheet.setColumnWidth(columnIndex, 5120);
            }else if(columnIndex == 10){
                //套餐
                sheet.setColumnWidth(columnIndex, 7120);
            }
            if (bizType == null) {
                String sheetName = sheet.getSheetName();
                if (sheetName.equals("无线业务")) {
                    if (columnIndex == 12) {
                        cell.setCellValue("卡数量(张)");
                    }
                } else if (sheetName.equals("有线业务")) {
                    if (columnIndex == 11) {
                        cell.setCellValue("带宽(M)或芯数(芯)");
                    }
                    if (columnIndex == 12) {
                        cell.setCellValue("条数(条)或公里(km)");
                    }
                } else if (sheetName.equals("语音业务")) {
                    if (columnIndex == 11) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                    if (columnIndex == 12) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                } else if (sheetName.equals("短信业务")) {
                    if (columnIndex == 11) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                    if (columnIndex == 12) {
                        cell.setCellValue("发送量(条)");
                    }
                } else if (sheetName.equals("5G业务")) {
                    if (columnIndex == 12) {
                        cell.setCellValue("规模(张或条)");
                    }
                }
            } else {
                if (bizType == 1) {
                    if (columnIndex == 12) {
                        cell.setCellValue("卡数量(张)");
                    }
                } else if (bizType == 2) {
                    if (columnIndex == 11) {
                        cell.setCellValue("带宽(M)或芯数(芯)");
                    }
                    if (columnIndex == 12) {
                        cell.setCellValue("条数(条)或公里(km)");
                    }
                } else if (bizType == 3) {
                    if (columnIndex == 11) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                    if (columnIndex == 12) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                } else if (bizType == 4) {
                    if (columnIndex == 11) {
                        sheet.setColumnWidth(columnIndex, 0);
                    }
                    if (columnIndex == 12) {
                        cell.setCellValue("发送量(条)");
                    }
                } else if (bizType == 5) {
                    if (columnIndex == 12) {
                        cell.setCellValue("规模(张或条)");
                    }
                }
            }
        }
    }

}
