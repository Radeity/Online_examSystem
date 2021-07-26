package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

//设置表头和数据字段
@Data
public class SubjectExcelModel {

    @ExcelProperty(value = "题目名称")
    private String questionName;

    @ExcelProperty(value = "题目类型", converter = SubjectTypeConverter.class)
    private Integer questionType;

    @ExcelProperty(value = "选项", converter = OptcontextConverter.class)
    private String opt;

    @ExcelProperty(value = "答案")
    private String questionAnswer;

    @ExcelProperty(value = "知识点")
    private String pointName;

}