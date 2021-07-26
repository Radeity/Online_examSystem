package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import dhu.cst.ExamSystem.dao.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OptcontextConverter implements Converter<String> {

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
        if(cellvalue.indexOf("A.")!=-1){
            cellvalue = cellvalue.replace("A.",", A.");
        }
        if(cellvalue.indexOf("B.")!=-1){
            cellvalue = cellvalue.replace("B.",", B.");
        }
        if(cellvalue.indexOf("C.")!=-1){
            cellvalue = cellvalue.replace("C.",", C.");
        }
        if(cellvalue.indexOf("D.")!=-1){
            cellvalue = cellvalue.replace("D.",", D.");
        }
        if(cellvalue.indexOf("A、")!=-1){
            cellvalue = cellvalue.replace("A、",", A.");
        }
        if(cellvalue.indexOf("B、")!=-1){
            cellvalue = cellvalue.replace("B、",", B.");
        }
        if(cellvalue.indexOf("C、")!=-1){
            cellvalue = cellvalue.replace("C、",", C.");
        }
        if(cellvalue.indexOf("D、")!=-1){
            cellvalue.replace("D、",", D.");
        }
        return cellvalue;
    }

    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws Exception {
        return null;
        //return new CellData<>(SubjectTypeEnum.matchByValue(value).getName());
    }
}