package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private StuRepository stuRepository;

    public List<Student > findByStudentId(long id){
        return stuRepository.findByStudentId(id);
    }

   /* public Map login(String username , String password){
        Map m = new HashMap();
        List<Student> stu = stuRepository.findByStudentNoAndStudentPwd(username,password);
        List<Teacher> tea = teaRepository.findByTeacherNoAndTeacherPwd(username,password);
        List<Admin> admin = adminRepository.findByAdminNoAndAdminPwd(username,password);

        if(stu.size()!=0||tea.size()!=0){
            String token = UUID.randomUUID().toString();
            redisService.set(token, username);
            return "{\"user\":"+'"'+username+'"'+",\"token\":"+token+"}";
        }
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
    }*/

    public StuInfoResult getstufortea(long studentid){
        List<StuInfoResult> lstu = stuRepository.queryforteabystuid(studentid);
        if(lstu.size()!=0){
            return lstu.get(0);
        }
        else return null;
    }



}