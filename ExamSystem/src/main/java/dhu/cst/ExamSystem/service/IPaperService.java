package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Paper;

import java.util.List;
import java.util.Map;

public interface IPaperService {
    List<Paper> getpaper(long subjectid);
    Map addpaper(String pstr);
    Map delpaper(long paperid);
    List<Paper> teafindPaper(String PaperName);

}
