package com.example.easyExcel;


import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author niuluda
 * @description 合并单元格
 * @createDate:2024-07-16 15:43:22
 */
@Data
@AllArgsConstructor
public class ExcelMergeHandler implements CellWriteHandler {
    //合并列数组
    private final int[] mergeColumnIndex;
    private final int[] mergeColumnIndex2;
    //合并行开始索引
    private final int mergeRowIndex;

    /**
     * 单元格处理后
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cellData
     * @param cell
     * @param head
     * @param relativeRowIndex
     * @param isHead
     */
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        int curRowIndex = cell.getRowIndex();
        int curColIndex = cell.getColumnIndex();
        //判断是否在合并列中
        if (curRowIndex > mergeRowIndex && isMergeColumn(curColIndex)) {
            //合并操作
            mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
        }
        if (curRowIndex > mergeRowIndex && isMergeColumn2(curColIndex)) {
            //合并操作
            mergeWithPrevRow2(writeSheetHolder, cell, curRowIndex, curColIndex);
        }
    }

    /**
     * 与上一行合并列
     *
     * @param writeSheetHolder
     * @param cell
     * @param curRowIndex
     * @param curColIndex
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        Object curData = getCellData(cell);
        Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
        Object preData = getCellData(preCell);
        if (curData.equals(preData) && isSamePrimaryKey(cell, curRowIndex)) {
            Sheet sheet = writeSheetHolder.getSheet();
            mergeCell(sheet, curRowIndex, curColIndex);

        }

    }

    private void mergeWithPrevRow2(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        Object curData = getCellData(cell);
        Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
        Object preData = getCellData(preCell);
        if(curData.equals(preData) && isSamePrimaryKey2(cell, curRowIndex)){
            Sheet sheet = writeSheetHolder.getSheet();
            mergeCell(sheet, curRowIndex, curColIndex);
        }

    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param curRowIndex
     * @param curColIndex
     */
    private void mergeCell(Sheet sheet, int curRowIndex, int curColIndex) {
        //获取合并区域
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        boolean isMerged = false;
        //检查上一个单元格是否合并
        for (int i = 0; i < mergedRegions.size() && !isMerged; i++) {
            CellRangeAddress cellAddresses = mergedRegions.get(i);
            if (cellAddresses.isInRange(curRowIndex - 1, curColIndex)) {
                sheet.removeMergedRegion(i);
                cellAddresses.setLastRow(curRowIndex);
                sheet.addMergedRegion(cellAddresses);
                isMerged = true;
            }
        }
        if (!isMerged) {
            CellRangeAddress cellAddresses = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex, curColIndex);
            sheet.addMergedRegion(cellAddresses);
        }
    }

    /**
     * 判断合并组
     *
     * @param cell
     * @param curRowIndex
     * @return
     */
    private boolean isSamePrimaryKey(Cell cell, int curRowIndex) {
        String currentPriKey = cell.getRow().getCell(0).getStringCellValue();
        String previousPriKey = cell.getSheet().getRow(curRowIndex - 1).getCell(0).getStringCellValue();
        return currentPriKey .equals (previousPriKey);
    }

    private boolean isSamePrimaryKey2(Cell cell, int curRowIndex) {
        String currentPriKey = cell.getRow().getCell(8).getStringCellValue();
        String previousPriKey = cell.getSheet().getRow(curRowIndex - 1).getCell(8).getStringCellValue();
        return currentPriKey .equals (previousPriKey);
    }

    /**
     * 获取单元格值
     *
     * @param cell
     * @return
     */
    private Object getCellData(Cell cell) {
        return cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
    }

    /**
     * 是否是合并列
     *
     * @param curColIndex
     * @return
     */
    private boolean isMergeColumn(int curColIndex) {
        for (int columnIndex : mergeColumnIndex) {
            if (curColIndex == columnIndex) {
                return true;
            }
        }
        return false;
    }

    private boolean isMergeColumn2(int curColIndex) {
        for (int columnIndex : mergeColumnIndex2) {
            if (curColIndex == columnIndex) {
                return true;
            }
        }
        return false;
    }
}
