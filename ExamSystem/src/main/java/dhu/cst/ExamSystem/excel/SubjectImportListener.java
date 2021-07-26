package dhu.cst.ExamSystem.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.entity.Question;
import dhu.cst.ExamSystem.service.IPointService;
import dhu.cst.ExamSystem.service.IQuestionService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SubjectImportListener extends AnalysisEventListener<SubjectExcelModel> {

    List<SubjectExcelModel> list = new ArrayList<>();
    private static final int BATCH_COUNT = 5;
    private long subjectId;
    private String bankName;
    private IPointService pointService;
    private IQuestionService questionService;

    //构造函数
    public SubjectImportListener(IPointService pointService, IQuestionService questionService,
                                 Long subjectId, String bankName) {
        this.pointService = pointService;
        this.questionService = questionService;
        this.subjectId = subjectId;
        this.bankName = bankName;
    }

    //存入数据库
    public void saveData() {
        List<Question> lq = new ArrayList<>();
        list.forEach(subject -> {
            Question question = new Question();
            BeanUtils.copyProperties(subject, question);
            question.setSubjectId(subjectId);
            question.setBankName(bankName);
            String pointname = subject.getPointName();
            List<Point> lp = pointService.findbypointname(pointname);
            if(lp.size()==0){
                Point point = new Point();
                point.setPointName(pointname);
                point.setSubjectId(subjectId);
                pointService.addpoint(point);
                lp = pointService.findbypointname(pointname);
            }
            question.setPointId(lp.get(0).getPointId());
            lq.add(question);
        });
        questionService.saveall(lq);
    }

    //每次解析都会调用
    @Override
    public void invoke(SubjectExcelModel subjectExcelModel, AnalysisContext analysisContext) {
        list.add(subjectExcelModel);
        System.out.println(JSON.toJSONString(subjectExcelModel));
        if(list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    //解析完成调用的方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
}