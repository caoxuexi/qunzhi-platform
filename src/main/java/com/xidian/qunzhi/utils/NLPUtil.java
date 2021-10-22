package com.xidian.qunzhi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @Author beiqixing
 * @Description 调用NLP开源库 hanlp 对需求进行词性标注，根据词性抽取关键短语
 * @Date 2021/10/17 12:48
 */
public class NLPUtil {

        public static String ext(String demands) {
        // TODO Auto-generated method stub
        Process proc;
        try {
            // 参数列表
            String[] args = new String[]{"/usr/local/bin/python3", "/home/centos/qunzhi-platform/keyext.py", demands};
            // 执行py文件
            proc = Runtime.getRuntime().exec(args);
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "utf-8"));
            String line = null;
            String res = "";
            while ((line = in.readLine()) != null) {
                res += line;
            }
            in.close();
            proc.waitFor();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "传入为空";
    }

}

