package dhu.cst.ExamSystem.common;

import lombok.Data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class ExamResult {

    private long examId;
    private String examName;
    private java.sql.Timestamp deadline;
    private long lastTime;
    public ExamResult(long examId,String examName,Object deadline,long lastTime) {   //全参构造器
        this.examId = examId;
        this.examName = examName;
        this.deadline = stringToTimestamp(deadline.toString());
        this.lastTime = lastTime;
    }
    public static Timestamp stringToTimestamp(String dateStr){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateStr);
            cal.setTime(date);
            return new Timestamp(cal.getTimeInMillis());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            cal.setTime(new Date());
            return new Timestamp(cal.getTimeInMillis());
        }
    }


}
