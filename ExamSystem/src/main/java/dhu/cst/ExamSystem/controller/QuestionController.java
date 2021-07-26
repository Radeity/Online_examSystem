package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Pdetail;
import dhu.cst.ExamSystem.entity.Question;
import dhu.cst.ExamSystem.excel.SubjectExcelModel;
import dhu.cst.ExamSystem.excel.SubjectImportListener;
import dhu.cst.ExamSystem.service.IPointService;
import dhu.cst.ExamSystem.service.IQuestionService;
import dhu.cst.ExamSystem.utils.ExcelToolUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(tags = "老师与试题相关接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class QuestionController {
    @Autowired
    IQuestionService questionService;
    @Autowired
    IPointService pointService;

    @ApiOperation("根据学科获取题库名")
    @GetMapping(value = "/getbank")
    public List<String> getbank(@RequestParam("subjectId") long subjectId){
        return questionService.getbank(subjectId);
    }

    @ApiOperation("根据学科获取试题")
    @GetMapping(value = "/getquestion")
    public List<Question> getquestion(@RequestParam("subjectId") long subjectId,
                                      @RequestParam("pageNum") Integer page,
                                      @RequestParam("pageSize") Integer size) {
        return questionService.getquestion(subjectId,page,size);
    }

    @ApiOperation("综合查询试题")
    @GetMapping(value = "/questionintegratedquery")
    public Map questionintegratedquery(@RequestParam(value="bankName",required = false,defaultValue = "") String bankName,
                                            @RequestParam(value="pointId",required = false,defaultValue = "0")  long pointId,
                                            @RequestParam(value="type",required = false,defaultValue = "-1")  long type,
                                            @RequestParam("pageNum") Integer page,
                                            @RequestParam("pageSize") Integer size) {
        return questionService.integratedquery(bankName,pointId,type,page,size);
    }

    @ApiOperation("根据题库获取试题")
    @GetMapping(value = "/getquestionbybank")
    public List<Question> getquestionbybank(@RequestParam("bankName") String bankName,
                                        @RequestParam("pageNum") Integer page,
                                        @RequestParam("pageSize") Integer size) {
        return questionService.getquestionbybank(bankName,page,size);
    }

    @ApiOperation("根据知识点获取试题")
    @GetMapping(value = "/getquestionbypoint")
    public List<Question> getquestionbypoint(@RequestParam("subjectId") long subjectId,@RequestParam("pointId")  long pointId,
                                            @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size ) {
        return questionService.getquestionbypoint(subjectId,pointId,page,size);
    }

    @ApiOperation("根据题型获取试题")
    @GetMapping(value = "/getquestionbytype")
    public List<Question> getquestionbytype(@RequestParam("subjectId") long subjectId,@RequestParam("type")  long type,
                                             @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size ) {
        return questionService.getquestionbytype(subjectId,type,page,size);
    }

    @ApiOperation("获取试卷中的题目信息")
    @GetMapping(value = "/getquefrompaper")
    public Map getquefromexam(@RequestParam("paperId") long paperId,
                                        @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size ){
        return questionService.getquefromexam(paperId,page,size);
    }

    @ApiOperation("获取试卷中的题目信息通过知识点查找")
    @GetMapping(value = "/getquefrompaperbypoint")
    public List<Pdetail> getquefromexambypoint(@RequestParam("paperId") long paperId,@RequestParam("pointId") long pointId,
                                        @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size ){
        return questionService.getquefromexambypoint(paperId,pointId,page,size);
    }

    @ApiOperation("获取试卷中的题目信息通过题目类型查找")
    @GetMapping(value = "/getquefrompaperbytype")
    public List<Pdetail> getquefromexambytype(@RequestParam("paperId") long paperId,@RequestParam("type") long type,
                                               @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size ){
        return questionService.getquefromexambytype(paperId,type,page,size);
    }


    @ApiOperation("增加试题到试卷")
    @PostMapping(value = "/addquetopaper")
    public Map addquestiontoexam(@RequestParam("questionId") long questionId, @RequestParam("paperId") long paperId) {
        return questionService.addquestiontoexam(questionId, paperId);
    }

    @ApiOperation("获取当前试卷各种类题目数量")
    @GetMapping(value = "/getquestionnum")
    public Map getquestionnum(@RequestParam("paperId") long paperId) {
        return questionService.getquestionnum(paperId);
    }

    @ApiOperation("从试卷中删除题目")
    @DeleteMapping(value = "/delequefrompaper")
    public Map deletequefromexam(@RequestParam("questionId") long questionId, @RequestParam("paperId") long paperId){
        return questionService.delquefromexam(questionId,paperId);
    }

    @ApiOperation("插入更新题目信息")
    @PostMapping(value = "/saveorupdatequestion")
    public Map addquestion(@RequestBody String strque) {
        return questionService.addquestion(strque);
    }

    @ApiOperation("导入题目")
    @PostMapping(value = "/import")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "学科ID", dataType = "Long"),
            @ApiImplicitParam(name = "bankName", value = "题库名", dataType = "String")})
    public Boolean importque(long subjectId,String bankName,
                             @ApiParam(value = "要上传的文件", required = true) MultipartFile file) throws IOException {
        return ExcelToolUtil.readExcel(file.getInputStream(), SubjectExcelModel.class, new SubjectImportListener(pointService,questionService,subjectId, bankName));

    }

    @ApiOperation("删除题目")
    @DeleteMapping(value = "/delequestion")
    public Map deletequestion(@RequestParam("questionId") long questionId){
        return questionService.delquestion(questionId);
    }

    @ApiOperation("新增题库")
    @PostMapping(value = "/addbank")
    public boolean importque(@RequestParam("bankName") String bankName,
                           @RequestParam("subjectId") long subjectId) {
        return questionService.addbank(subjectId,bankName);
    }

    @ApiOperation("修改题库名")
    @PostMapping(value = "/updatebank")
    public boolean addbank(@RequestParam("oldbankName") String oldbankName,
                           @RequestParam("newbankName") String newbankName) {
        return questionService.changebankname(oldbankName,newbankName);
    }

    @ApiOperation("删除题库")
    @DeleteMapping(value = "/delebank")
    public boolean deletebank(@RequestParam("bankName") String bankName){
        return questionService.delbank(bankName);
    }


    @ApiOperation("卷面分析")
    @GetMapping(value = "/analyzebyexam")
    public Map analyse(@RequestParam("examId") long examId){
        return questionService.analyse(examId);
    }

}
