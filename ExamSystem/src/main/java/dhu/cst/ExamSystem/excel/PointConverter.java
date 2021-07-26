package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PointConverter implements Converter<Integer> {

    @Autowired
    IPointService pointService;

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) throws Exception {
        String cellvalue = cellData.getStringValue();
        List<Point> lp = pointService.getpointbysub(1);
        if(lp!=null){
            return (int)lp.get(0).getPointId();
        }
        return -1;
    }

    @Override
    public CellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws Exception {
        return null;
        //return new CellData<>(SubjectTypeEnum.matchByValue(value).getName());
    }
}