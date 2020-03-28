package dhu.cst.ExamSystem.service;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.Admin;
import dhu.cst.ExamSystem.entity.Student;
import dhu.cst.ExamSystem.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService{
  //  @Autowired
   // private RedisService redisService;
    @Autowired
    private StuRepository stuRepository;
    @Autowired
    private TeaRepository teaRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private TCRepository tcRepository;
    @Autowired
    private AdminRepository adminRepository;
    public List<Student > findByStudentId(long id){
        return stuRepository.findByStudentId(id);
    }

    public Map login(String username , String password){
        Map m = new HashMap();
        List<Student> stu = stuRepository.findByStudentNoAndStudentPwd(username,password);
        List<Teacher> tea = teaRepository.findByTeacherNoAndTeacherPwd(username,password);
        List<Admin> admin = adminRepository.findByAdminNoAndAdminPwd(username,password);

        /*if(stu.size()!=0||tea.size()!=0){
            String token = UUID.randomUUID().toString();
            redisService.set(token, username);
            return "{\"user\":"+'"'+username+'"'+",\"token\":"+token+"}";
        }*/
        if(stu.size()!=0||tea.size()!=0){
            String token = UUID.randomUUID().toString();
        //    redisService.set(token, username);
            Integer permission=0;
            if(stu.size()!=0){
                permission=0;
                long studentid=stu.get(0).getStudentId();
                m.put("name",stu.get(0).getStudentName());
            }
            if(tea.size()!=0){
                permission=1;
                long teacherid=tea.get(0).getTeacherId();
                m.put("name",tea.get(0).getTeacherName());
            }
            if(admin.size()!=0){
                permission=2;
                long adminid=admin.get(0).getAdminId();
                m.put("name",admin.get(0).getAdminName());
            }
            m.put("token",token);
            m.put("permission",permission);
            return m;
        }
        else {
            m.put("error","Login failed!");
            return m;
        }
    }

    public StuInfoResult getstufortea(long studentid){
        List<StuInfoResult> lstu = stuRepository.queryforteabystuid(studentid);
        if(lstu.size()!=0){
            return lstu.get(0);
        }
        else return null;
    }

    public List<Student> findstuforadmin(long studentno){
        return stuRepository.findAllByStudentNoContaining(studentno);
    }
    public List<Student> findstuforadmin(String studentname){
        return stuRepository.findAllByStudentNameContaining(studentname);
    }

    public List<Student> getstuforadmin(){
        return stuRepository.findAll();
    }

    public Map addstu(String str){
        Map m = new HashMap();
        List<Student> lstu = JSON.parseArray(JSON.parseObject(str).getString("student"), Student.class);
        if(lstu.size()!=0){
            Student stu = lstu.get(0);
            stuRepository.save(stu);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    public Map delstu(long stuid) {
        Map m = new HashMap();
        stuRepository.deleteByStudentId(stuid);
        m.put("msg", "Delete successfully!");
        return m;
    }


    public List<Teacher> findteaforadmin(long teacherno){
        return teaRepository.findAllByTeacherNoContaining(teacherno);
    }
    public List<Teacher> findteaforadmin(String teachername){
        return teaRepository.findAllByTeacherNameContaining(teachername);
    }

    public List<Teacher> getteaforadmin(){
        return teaRepository.findAll();
    }

    public Map addtea(String str){
        Map m = new HashMap();
        List<Teacher> ltea = JSON.parseArray(JSON.parseObject(str).getString("teacher"), Teacher.class);
        if(ltea.size()!=0){
            Teacher tea = ltea.get(0);
            teaRepository.save(tea);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    public Map deltea(long teaid) {
        Map m = new HashMap();
        teaRepository.deleteByTeacherId(teaid);
        m.put("msg", "Delete successfully!");
        return m;
    }

        public String logout(HttpServletRequest request) {
    /*    String token = request.getHeader("token");
        Boolean delete = redisService.delete(token);
        if (!delete) {
            return "注销失败，请检查是否登录！";
        }
        return "注销成功！";*/
        return "unfinished!";
    }

}