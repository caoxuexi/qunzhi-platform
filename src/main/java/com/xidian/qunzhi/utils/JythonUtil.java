package com.xidian.qunzhi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @Author beiqixing
 * @Description 调用 python 的 jieba 库中的分词、词性标注功能进行关键短语的抽取
 * @Date 2021/10/17 12:48
 */
public class JythonUtil {

    public static String ext(String demands){
        Process proc;
        try {
            // 参数列表
            String[] args = new String[] { "python", "C:\\Users\\1\\Desktop\\qunzhi\\qunzhi-api\\src\\main\\java\\com\\xidian\\qunzhi\\utils\\test2.py", demands};
            // 执行py文件
            proc = Runtime.getRuntime().exec(args);
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
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
        return "";
    }

    public static void main(String[] args) {
        String extStr = ext("我想要个农业自动化系统，能够自动浇水功能、自动施肥功能，还要有温湿度监控");
        System.out.println(extStr);

    }
}

