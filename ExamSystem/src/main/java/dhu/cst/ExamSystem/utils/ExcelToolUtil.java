package dhu.cst.ExamSystem.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.InputStream;


public class ExcelToolUtil {
    public static <T> Boolean readExcel(InputStream inputStream, Class<T> clazz, AnalysisEventListener<T> listener) {
        Boolean success = Boolean.TRUE;
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcelFactory.read(inputStream, clazz, listener).build();
            ReadSheet readSheet = EasyExcelFactory.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (Exception e) {
            success = Boolean.FALSE;
        } finally {
            //读的时候会创建临时文件，因此要写关闭
            if (excelReader != null)
                excelReader.finish();
        }
        return success;
    }
}
