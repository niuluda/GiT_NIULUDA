package com.example.easyExcel;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import org.apache.poi.ss.usermodel.*;

/**
 * @author niuluda
 * @description 在线excel基本样式
 * @createDate:2024-07-16 15:43:22
 */
public class ExcelStyleHandler {
    /**
     * 头部样式
     *
     * @return
     */
    public static WriteCellStyle getHeadStyle() {
        WriteCellStyle headStyle = createBaseStyle();
        //设置标题字体
        WriteFont headFont = new WriteFont();
        headFont.setFontName("黑体");
        headFont.setFontHeightInPoints((short) 11);
        headFont.setColor(IndexedColors.WHITE.getIndex());
        headFont.setBold(true);
        //设置背景色
        headStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        headStyle.setWriteFont(headFont);
        return headStyle;
    }

    /**
     * 内容样式
     *
     * @return
     */
    public static WriteCellStyle getContentStyle() {
        WriteCellStyle contentStyle = createBaseStyle();
        //设置标题
        WriteFont contentFont = new WriteFont();
        contentFont.setFontName("黑体");
        contentStyle.setWriteFont(contentFont);
        return contentStyle;
    }

    /**
     * 基础样式
     *
     * @return
     */
    private static WriteCellStyle createBaseStyle() {
        WriteCellStyle writeCellStyle = new WriteCellStyle();
        writeCellStyle.setBorderBottom(BorderStyle.THIN);
        writeCellStyle.setBottomBorderColor((short) 0);
        writeCellStyle.setBorderTop(BorderStyle.THIN);
        writeCellStyle.setTopBorderColor((short) 0);
        writeCellStyle.setBorderLeft(BorderStyle.THIN);
        writeCellStyle.setLeftBorderColor((short) 0);
        writeCellStyle.setBorderRight(BorderStyle.THIN);
        writeCellStyle.setRightBorderColor((short) 0);
        //对齐方式
        writeCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        writeCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //其他
        writeCellStyle.setWrapped(true);
        writeCellStyle.setShrinkToFit(true);
        return writeCellStyle;
    }
}
