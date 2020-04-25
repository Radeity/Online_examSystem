package dhu.cst.ExamSystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccuracyResult {
    private long count;
    private String answer;
}
