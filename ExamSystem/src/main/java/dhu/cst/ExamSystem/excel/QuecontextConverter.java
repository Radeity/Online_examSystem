package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import dhu.cst.ExamSystem.dao.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class QuecontextConverter implements Converter<String> {

    @Autowired
    PointRepository pointRepository;

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) throws Exception {
        String cellvalue = cellData.getStringValue();

        return cellvalue;
    }

    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws Exception {
        return null;
        //return new CellData<>(SubjectTypeEnum.matchByValue(value).getName());
    }
}
