package dhu.cst.ExamSystem.utils;

import dhu.cst.ExamSystem.config.BaiDuAiConfig;
import org.json.JSONObject;

public class BaiDuAiCheck {

    public static JSONObject checkText(String text) {
        // 参数为输入文本
        JSONObject response = BaiDuAiConfig.client.textCensorUserDefined(text);
        return response;
    }
}