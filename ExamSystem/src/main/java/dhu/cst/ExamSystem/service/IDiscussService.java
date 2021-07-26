package dhu.cst.ExamSystem.service;

import java.util.Map;

public interface IDiscussService {
    Map getdiscuss(long questionId);
    Map getdiscussbytime(long questionId, Integer page);
    Map getdiscussbythumb(long questionId, Integer page);
    Map getreplybydiscuss(long discussId);
    boolean deldiscuss(long discussId);
    Map adddiscuss(String str);
    boolean unthumb(long discussId);
    boolean thumbup(long discussId);
    Map admingetdiscuss(Integer page, Integer size);
    Map admingetdiscussnum();
}
