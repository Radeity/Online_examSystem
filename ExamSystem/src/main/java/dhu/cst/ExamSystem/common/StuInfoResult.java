package dhu.cst.ExamSystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StuInfoResult {
    private long studentId;
    private String studentNo;
    private String studentName;
}
