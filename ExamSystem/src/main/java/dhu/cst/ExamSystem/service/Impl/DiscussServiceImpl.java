package dhu.cst.ExamSystem.service.Impl;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.utils.BaiDuAiCheck;
import dhu.cst.ExamSystem.dao.DiscussRepository;
import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.Discuss;
import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.IDiscussService;
import dhu.cst.security.entity.CurrentUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DiscussService")
@Transactional
public class DiscussServiceImpl implements IDiscussService {
    @Autowired
    DiscussRepository discussRepository;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map getdiscuss(long questionId){
        Map m = new HashMap();
        List<Discuss> ld = discussRepository.findByQuestionId(questionId);
        long studentId = currentUser.getCurrentUser().getId();
        User user = userRepository.findById(studentId);
        List<Long> replynum = new ArrayList();
        for(int i=0;i<ld.size();i++) {
            replynum.add(discussRepository.countByDiscussId(ld.get(i).getDiscussId()));
        }
        m.put("replynum",replynum);
        m.put("discuss",ld);
        m.put("studentName",user.getName());
        m.put("studentId",studentId);
        return m;
    }

    @Override
    public Map admingetdiscussnum(){
        Map m = new HashMap();
        List<Discuss> ld = discussRepository.findAll();
        m.put("discussnum",ld.size());
        return m;
    }

    @Override
    public Map admingetdiscuss(Integer page, Integer size){
        Map m = new HashMap();
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Discuss> dp =  discussRepository.findAll(pageable);
        m.put("discuss",dp.getContent());
        return m;
    }

    @Override
    public Map getdiscussbytime(long questionId, Integer page){
        Map m = new HashMap();
        List<Discuss> ld = discussRepository.findByQuestionIdOrderByDtimeDesc(questionId);
        List<Long> replynum = new ArrayList();
        for(int i=0;i<ld.size();i++) {
            replynum.add(discussRepository.countByDiscussIdOrderByDtimeDesc(ld.get(i).getDiscussId()));
        }
        long studentId = currentUser.getCurrentUser().getId();
        User user = userRepository.findById(studentId);
        int size = 5;   //每页留言数量
        int toIndex = page * size + size;
        if(toIndex>ld.size()){
            toIndex = ld.size();
        }
        m.put("discussnum", ld.size());
        m.put("replynum",replynum.subList(page*size, toIndex));
        m.put("discuss",ld.subList(page*size, toIndex));
        m.put("studentName",user.getName());
        m.put("studentId",studentId);
        return m;
    }

    @Override
    public Map getdiscussbythumb(long questionId, Integer page){
        Map m = new HashMap();
        List<Discuss> ld = discussRepository.findByQuestionIdOrderByThumbDesc(questionId);
        List<Long> replynum = new ArrayList();
        for(int i=0;i<ld.size();i++) {
            replynum.add(discussRepository.countByDiscussIdOrderByThumbDesc(ld.get(i).getDiscussId()));
        }
        int size = 5;   //每页留言数量
        int toIndex = page * size + size;
        if(toIndex>ld.size()){
            toIndex = ld.size();
        }
        m.put("replynum",replynum.subList(page*size, toIndex));
        m.put("discuss",ld.subList(page*size, toIndex));
        return m;
    }

    @Override
    public Map getreplybydiscuss(long discussId){
        Map m = new HashMap();
        List<Discuss> ld = discussRepository.findReplyByDiscussId(discussId);
        m.put("discuss",ld);
        return m;
    }

    @Override
    public boolean deldiscuss(long discussId){
        discussRepository.deleteByDiscussId(discussId);
        return true;
    }

    @Override
    public Map adddiscuss(String str){
        Map m = new HashMap();
        JSONObject jsonObject=new JSONObject();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<Discuss> ldiscuss = JSON.parseArray(JSON.parseObject(str).getString("discuss"), Discuss.class);
        if(ldiscuss.size()!=0){
            Discuss discuss = ldiscuss.get(0);
            org.json.JSONObject result= BaiDuAiCheck.checkText(discuss.getContent());
            System.out.println(result);
            if(result.get("conclusion")==null){
                jsonObject.put("code",2);
                m.put("msg","操作异常！");
            }
            else if(result.get("conclusion").equals("合规")) {
                discuss.setDtime(time);
                discussRepository.save(discuss);
                jsonObject.put("code",1);
                m.put("msg","留言成功！");
            }
            else{
                jsonObject.put("code",3);
                JSONArray jsonData = result.getJSONArray("data");
                JSONObject jsonDailyFirst = jsonData.getJSONObject(0);
                String msg = jsonDailyFirst.getString("msg");
                m.put("msg",msg);
            }
        }
        return m;
    }

    @Override
    public boolean thumbup(long discussId){
        Discuss discuss = discussRepository.findByDiscussId(discussId);
        discuss.setThumb(discuss.getThumb()+1);
        discussRepository.save(discuss);
        return true;
    }

    @Override
    public boolean unthumb(long discussId){
        Discuss discuss = discussRepository.findByDiscussId(discussId);
        discuss.setThumb(discuss.getThumb()-1);
        discussRepository.save(discuss);
        return true;
    }
}
