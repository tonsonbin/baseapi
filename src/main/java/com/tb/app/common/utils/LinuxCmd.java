package com.tb.app.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Linux命令执行
 * @Author Benjamin
 * @CreateDate 2019-05-23 13:17
 **/
public class LinuxCmd {

    /**
     * 执行Linx命令
     *
     * @param cmd int tp = 1 返回执行结果  非1 返回命令执行后的输出
     * @return
     */
    public static String runCommand(String cmd, int tp) {
        StringBuffer buf = new StringBuffer(1000);
        String rt = "-1";
        try {
            Process pos = Runtime.getRuntime().exec(cmd);
            pos.waitFor();
            if (tp == 1) {
                if (pos.exitValue() == 0) {
                    rt = "1";
                }
            } else {
                InputStreamReader ir = new InputStreamReader(pos.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String ln = "";
                while ((ln = input.readLine()) != null) {
                    buf.append(ln + "<br>");
                }
                rt = buf.toString();
                input.close();
                ir.close();
            }
        } catch (java.io.IOException e) {
            rt = e.toString();
        } catch (Exception e) {
            rt = e.toString();
        }
        return rt;
    }
}
