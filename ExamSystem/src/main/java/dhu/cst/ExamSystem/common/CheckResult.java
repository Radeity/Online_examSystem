package dhu.cst.ExamSystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckResult {
    private long questionType;
    private String questionAnswer;
}
