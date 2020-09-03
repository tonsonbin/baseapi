package com.tb.app.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Description 多媒体工具--该工具类依赖Linux系统并且安装了ffmpeg
 * @Author Benjamin
 * @CreateDate 2019-05-23 9:40
 **/
public class MediaUtils {
    /**
     * 截取视频指定时间帧
     */
    private final String GET_FRAME = "ffmpeg -y -ss %s -i %s -vframes 1 -an -vcodec png -vf scale=iw/2:ih/2 %s";

    /**
     * 将VOICE转换为WAV
     */
    private final String VOICE_TO_WAV = "ffmpeg -i %s -acodec  pcm_alaw -ar 8000 -ac 1 %s";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * voice转Wav
     * WAV格式要求：CCITT A-Law，8.000 kHz, 8 位, 单声道。
     *
     * @param inFile
     * @param outFile
     */
    public void voiceToWav(File inFile, File outFile) throws IOException {
        logger.info("输入文件地址：" + inFile.getPath() + "  是否存在：" + inFile.exists());
        logger.info("输出文件地址：" + outFile.getPath() + "  是否存在：" + outFile.exists());
        //存在就不进行转码
        if (outFile.exists()) {
            logger.info("文件存在，不进行转码");
            return;
        }
        //开始转换
        String cmd = String.format(VOICE_TO_WAV, inFile.getPath(), outFile.getPath());
        logger.info("转换命令：" + cmd);
        logger.info("开始转换");
        String result = LinuxCmd.runCommand(cmd, -1);
        logger.info("转换结束:," + result);
    }

    /**
     * 获取视频指定帧
     *
     * @param seconds 指定的秒数
     * @param inFile  视频源
     * @param outFile 保持帧
     * @throws IOException
     */
    public void findVideoFrame(String seconds, File inFile, File outFile) throws IOException {
        logger.info("指定的秒数：" + seconds);
        logger.info("输入文件地址：" + inFile.getPath() + "  是否存在：" + inFile.exists());
        logger.info("输出文件地址：" + outFile.getPath() + "  是否存在：" + outFile.exists());
        //不存在就创建
        if (outFile.exists()) {
            outFile.deleteOnExit();
        }
        //生成命令
        String cmd = String.format(GET_FRAME, seconds, inFile.getPath(), outFile.getPath());
        logger.info("获取视频封面：" + cmd);
        long start = System.currentTimeMillis();
        logger.info("开始获取:");
        String result = LinuxCmd.runCommand(cmd, -1);
        long end = System.currentTimeMillis();
        logger.info("获取结束:" + result);
        logger.info("耗时:" + (end - start) / 1000);
    }

    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    private static String processFLV(String inputPath) {
        List<String> commend = new java.util.ArrayList<String>();
        commend.add("F:/ffmpeg/bin/ffmpeg.exe");
        commend.add("-i");
        commend.add(inputPath);

        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.redirectErrorStream(true);
            Process p = builder.start();

            // 1. start
            BufferedReader buf = null; // 保存ffmpeg的输出结果流
            String line = null;
            // read the standard output

            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuffer sb = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                continue;
            }
            p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
            // 1. end
            return sb.toString();
        } catch (Exception e) {
            // System.out.println(e);
            return null;
        }
    }
}
