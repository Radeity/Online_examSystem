package dhu.cst.ExamSystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaperResult {
    private long questionId;
    private long questionType;
    private String opt;
    private String questionName;
}
