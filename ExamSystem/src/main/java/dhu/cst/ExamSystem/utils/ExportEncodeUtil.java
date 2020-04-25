package dhu.cst.ExamSystem.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExportEncodeUtil {
    public static void HeaderCode(HttpServletRequest request, HttpServletResponse response, String sheetName, HSSFWorkbook wb) throws IOException {
        // 针对IE或者以IE为内核的浏览器：
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        String fileName = sheetName + ".xlsx";
        if (userAgent.contains("msie") || userAgent.contains("trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //"application/vnd.ms-excel;charset=utf-8"
        response.setHeader("success", "true");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);//默认Excel名称

        wb.write(os);
        os.flush();
        os.close();
    }

    /**
     * 自适应宽度(中文支持)
     *  @param sheet
     * @param size
     */
    public static void setSizeColumn(HSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setHorizontallyCenter(true);
            sheet.setVerticallyCenter(true);
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }
}
