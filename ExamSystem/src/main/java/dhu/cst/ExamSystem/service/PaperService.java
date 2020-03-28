package dhu.cst.ExamSystem.service;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.PaperRepository;
import dhu.cst.ExamSystem.dao.TCRepository;
import dhu.cst.ExamSystem.entity.Paper;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Tc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    TCRepository tcRepository;

    public List<Paper> getpaper(long teacherId){
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);
        long subjectId=0;
        if(tc.size()!=0){
            subjectId = tc.get(0).getSubjectId();
        }
        return paperRepository.findBySubjectId(subjectId);
    }

    public Map addpaper(String pstr){
        Map m = new HashMap();
        List<Paper> lpaper = JSON.parseArray(JSON.parseObject(pstr).getString("paper"), Paper.class);
        if(lpaper.size()!=0){
            Paper paper = lpaper.get(0);
            paperRepository.save(paper);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    public Map delpaper(long paperid){
        Map m = new HashMap();
        paperRepository.deleteByPaperId(paperid);
        m.put("msg","Delete successfully!");
        return m;
    }

    public List<Paper> teafindPaper(String PaperName, long teacherid){
        List<Subject> subjects = tcRepository.findSubjectName(teacherid);
        List<Long> longList = new ArrayList<>();
        List<Paper> paperrs = new ArrayList<>();
        List<Paper> lpaper = paperRepository.findAllByPaperNameContaining(PaperName);
        for(int i=0;i<subjects.size();i++){
            longList.add(subjects.get(i).getSubjectId());
        }
        for(int i=0;i<lpaper.size();i++){
            if(longList.contains(lpaper.get(i).getPaperId())){
                paperrs.add(lpaper.get(i));
            }
        }
        return paperrs;
    }

}
