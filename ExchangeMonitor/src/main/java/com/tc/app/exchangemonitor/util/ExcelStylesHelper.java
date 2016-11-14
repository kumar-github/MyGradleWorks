package com.tc.app.exchangemonitor.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelStylesHelper
{
	@SuppressWarnings("deprecation")
	public static Map<String, CellStyle> createStyles(Workbook anExcelWorkbook)
	{
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle headerCellStyle = null;

		headerCellStyle = anExcelWorkbook.createCellStyle();
		Font font = anExcelWorkbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.DARK_BLUE.getIndex());
		headerCellStyle.setFont(font);
		headerCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		//headerCellStyle.setFillPattern(CellStyle.BIG_SPOTS);
		//headerCellStyle.setFillBackgroundColor(IndexedColors.ORANGE.getIndex());
		styles.put("headerStyle", headerCellStyle);

		CellStyle dateCellStyle = anExcelWorkbook.createCellStyle();
		DataFormat dataFormat = anExcelWorkbook.createDataFormat();
		dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mmm"));
		styles.put("dateStyle", dateCellStyle);

		CellStyle cellStyle = anExcelWorkbook.createCellStyle();
		Font font1 = anExcelWorkbook.createFont();
		font1.setColor(IndexedColors.DARK_RED.getIndex());
		cellStyle.setFont(font1);
		cellStyle.setFillPattern(CellStyle.FINE_DOTS);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("rowStyle", cellStyle);

		return styles;
	}
}