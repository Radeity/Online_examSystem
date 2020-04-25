package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Scoreview;

import java.util.List;

public interface IGradeService {
    List<Scoreview> integratedquery(String subjectName, String examName, String teacherName, Integer page, Integer size);
    List<Scoreview> findgradebysubject(long subjectid,Integer page,Integer size);
    List<Scoreview> findgradebyname(String studentname);
    List<Scoreview> findgradebyteacher(Integer page,Integer size);
    List<Scoreview> findgradebyclass(long classid,Integer page,Integer size);
    List<Scoreview> findgradebyclassid(long classid,long examid);
    List<Scoreview> stufindgrade();
}
