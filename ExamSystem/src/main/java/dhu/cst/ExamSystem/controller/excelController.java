package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.dao.ExamRepository;
import dhu.cst.ExamSystem.dao.SubjectRepository;
import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Scoreview;
import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.IExamsetService;
import dhu.cst.ExamSystem.service.IGradeService;
import dhu.cst.ExamSystem.utils.ExportEncodeUtil;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER;

@Api(tags = "导出表格接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class excelController {
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IExamsetService examsetservice;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/excel")
    public String export(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value = "subjectName",required = false,defaultValue = "") String subjectName,
                         @RequestParam(value = "examName",required = false,defaultValue = "") String examName,
                         @RequestParam(value = "teacherName",required = false,defaultValue = "") String teacherName) throws Exception {
        List<Classinfo> classList = new ArrayList<>();
        long teacherId=0,examId=0;
        long subjectId = subjectRepository.findBySubjectName(subjectName).getSubjectId();
        if(!teacherName.equals("")){
            User tea = userRepository.findByName(teacherName).get();
            teacherId = tea.getId();
            classList = examsetservice.classQuerybyteacher(subjectId,teacherId);
        }
        else{
            classList = examsetservice.classQuery(subjectId);
        }
        if(!examName.equals("")){
            examId = examRepository.findAllByExamNameContaining(examName).get(0).getExamId();
        }
        HSSFWorkbook wb = new HSSFWorkbook();//创建Excel文件
        int j=0;
        for(Classinfo classinfo : classList){
            HSSFSheet sheet = wb.createSheet(classinfo.getClassName());
            System.out.println(classinfo.getClassName());
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, 7);
            sheet.addMergedRegion(region);
            HSSFRow rowTitle = sheet.createRow(0);
            Cell oneCell = rowTitle.createCell(0);
            oneCell.setCellValue("成绩信息");// 设置标题内容
            HSSFCellStyle boderStyle = wb.createCellStyle();
            //boderStyle.setVerticalAlignment(CENTER);
            boderStyle.setAlignment(CENTER); // 创建一个居中格式
            oneCell.setCellStyle(boderStyle);

            String sheetName = sheet.getSheetName();
            wb.setSheetName(j, sheetName);
            sheet.setDefaultRowHeight((short) (20 * 20));
            CellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            HSSFRow row = sheet.createRow(1);
            row.createCell(0).setCellValue("考试名称");
            row.createCell(1).setCellValue("课程");
            row.createCell(2).setCellValue("姓名");
            row.createCell(3).setCellValue("授课老师");
            row.createCell(4).setCellValue("学院");
            row.createCell(5).setCellValue("班级");
            row.createCell(6).setCellValue("成绩");
            row.createCell(7).setCellValue("提交时间");


            List<Scoreview> scores = gradeService.findgradebyclassid(classinfo.getClassId(),examId);
            if ( scores != null && scores.size() > 0) {
                int i = 2;
                for (Scoreview score : scores) {
                    row = sheet.createRow(i);
                    row.createCell(0).setCellValue(score.getExamName());
                    row.createCell(1).setCellValue(subjectName);
                    row.createCell(2).setCellValue(score.getStudentName());
                    row.createCell(3).setCellValue(score.getTeacherName());
                    row.createCell(4).setCellValue(score.getDepartment());
                    row.createCell(5).setCellValue(classinfo.getClassName());

                    if(score.getSubmit()==null){
                        row.createCell(6).setCellValue(0);
                        row.createCell(7).setCellValue("未提交");
                    }
                    else{
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sf.format(score.getSubmit());
                        row.createCell(6).setCellValue(score.getScore());
                        row.createCell(7).setCellValue(str);
                        System.out.println(score.getSubmit());
                    }
                    i++;
                }
            }
            ExportEncodeUtil.setSizeColumn(sheet, scores.size());
            j++;
        }
        ExportEncodeUtil.HeaderCode(request, response, "成绩统计表", wb);

        return "导出用户信息成功";
    }
}


