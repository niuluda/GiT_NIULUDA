package com.example.easyExcel;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.util.Arrays;

/**
 * @author niuluda
 * @description 设置导出模板标黄列样式
 * @createDate:2024-07-20 14:43:22
 */
@Slf4j
@Data
@AllArgsConstructor
public class CustomColumnStyleHandler implements CellWriteHandler {
    /**
     * 填充颜色列数组
     */
    private final int[] markColumns;
    /**
     * 内容区填写处填充颜色
     */
    private final short fillColorIndex;

    /**
     * 单元格生成后调用
     * @param context
     */
    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        if (context != null) {
            Cell cell = context.getCell();
            WriteCellData<?> firstCellData = context.getFirstCellData();
            WriteSheetHolder writeSheetHolder = context.getWriteSheetHolder();
            Boolean isHead = context.getHead();
            if (writeSheetHolder != null && cell != null && isHead != null && firstCellData != null) {
                setColumnStyles(writeSheetHolder, cell, isHead);
                if (firstCellData != null) {
                    //清除自带拦截器FillStyleCellWriteHandler默认样式
                    firstCellData.setWriteCellStyle(null);
                }
            }
        }
    }


    /**
     * 获取公共样式
     *
     * @param cellStyle
     * @param font
     * @return
     */
    public CellStyle getCommonStyles(CellStyle cellStyle, Font font) {
        if (cellStyle != null && font != null) {
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBottomBorderColor((short) 0);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setTopBorderColor((short) 0);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setLeftBorderColor((short) 0);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setRightBorderColor((short) 0);
            cellStyle.setShrinkToFit(true);
            font.setFontHeightInPoints((short) 10);
            cellStyle.setFont(font);
            return cellStyle;
        }
        return null;
    }

    /**
     * 判断是否为填充列
     *
     * @param col
     * @return
     */
    public Boolean markCol(int col) {
        if (markColumns != null && markColumns.length > 0) {
            return Arrays.stream(markColumns).anyMatch(val -> val == (col));
        }
        return false;
    }


    /**
     * 设置单元格样式
     *
     * @param writeSheetHolder
     * @param cell
     * @param isHead
     */
    public void setColumnStyles(WriteSheetHolder writeSheetHolder, Cell cell, Boolean isHead) {
        if (writeSheetHolder != null && cell != null && isHead != null) {
            try {
                Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                if (workbook != null) {
                    Font font = workbook.createFont();
                    CellStyle cellStyle = getCommonStyles(workbook.createCellStyle(), font);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    font.setFontName("黑体");
                    font.setFontHeightInPoints((short) 11);
                    if (cellStyle != null && font != null) {
                        if (isHead) {
                            font.setBold(true);
                            font.setColor(IndexedColors.WHITE.getIndex());
                            cellStyle.setFont(font);
                            //自定义颜色
//                            byte[] rgb=new byte[]{0,101, (byte) 204};
//                            XSSFCellStyle xssfCellStyle=(XSSFCellStyle) cellStyle;
//                            xssfCellStyle.setFillForegroundColor(new XSSFColor(rgb,null));
                            //常规定义颜色
                            cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        } else {
                            cellStyle.setFont(font);
                            //判断是否为填写列
                            if (markCol(cell.getColumnIndex())) {
                                cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            }
                        }
                        cell.setCellStyle(cellStyle);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }
}

