package dhu.cst.ExamSystem.service.Impl;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.ClassInfoRepository;
import dhu.cst.ExamSystem.dao.PaperRepository;
import dhu.cst.ExamSystem.entity.Paper;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.service.IPaperService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PaperService")
@Transactional
public class PaperServiceImpl implements IPaperService {
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ClassInfoRepository classInfoRepository;
    @Autowired
    CurrentUser currentUser;

    /*
    public List<Paper> getpaper(){

        long teacherId = currentUser.getCurrentUser().getId();
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);
        long subjectId=0;
        Set set = new HashSet();
        if(tc.size()!=0){
            for(int i=0;i<tc.size();i++){
                subjectId = tc.get(i).getSubjectId();
                set.addAll(paperRepository.findBySubjectId(subjectId));
            }
        }
        List<Paper> lpaper = new ArrayList<Paper>();
        lpaper.addAll(set);
        return lpaper;
    }*/

    /**
     * 根据科目查试卷
     */
    @Override
    public List<Paper> getpaper(long subjectid){
        return paperRepository.findBySubjectId(subjectid);
    }
    @Override
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
    @Override
    public Map delpaper(long paperid){
        Map m = new HashMap();
        paperRepository.deleteByPaperId(paperid);
        m.put("msg","Delete successfully!");
        return m;
    }

    /**
     * 老师查询试卷信息
     * 只能查自己学科的试卷
     */
    @Override
    public List<Paper> teafindPaper(String PaperName){
        long teacherid = currentUser.getCurrentUser().getId();
        List<Subject> subjects = classInfoRepository.findSubjectName(teacherid);
        List<Long> longList = new ArrayList<>();
        List<Paper> paperrs = new ArrayList<>();
        List<Paper> lpaper = paperRepository.findAllByPaperNameContaining(PaperName);

        for(int i=0;i<subjects.size();i++){
            longList.add(subjects.get(i).getSubjectId());
        }
        for(int i=0;i<lpaper.size();i++){
            if(longList.contains(lpaper.get(i).getSubjectId())){
                paperrs.add(lpaper.get(i));
            }
        }
        return paperrs;
    }

}
