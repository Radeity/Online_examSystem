package dhu.cst.ExamSystem.config;

import com.baidu.aip.contentcensor.AipContentCensor;

public class BaiDuAiConfig {
    public static final String APP_ID ="111";

    public static final String API_KEY = "222";

    public static final String SECRET_KEY = "333";

    /*初始化客户端*/
    public static final AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

}
