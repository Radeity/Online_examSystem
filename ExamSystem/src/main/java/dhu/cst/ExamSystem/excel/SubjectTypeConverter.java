package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class SubjectTypeConverter implements Converter<Integer> {

    public static final String single = "单选";
    public static final String multi = "多选";
    public static final String blank = "填空";

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) throws Exception {
        String cellvalue = cellData.getStringValue();
        if(cellvalue.indexOf(single)!=-1){
            return 0;
        }
        else if(cellvalue.indexOf(multi)!=-1){
            return 1;
        }
        else if(cellvalue.indexOf(blank)!=-1){
            return 2;
        }
        else return -1;
    }

    @Override
    public CellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws Exception {
        return null;
        //return new CellData<>(SubjectTypeEnum.matchByValue(value).getName());
    }
}