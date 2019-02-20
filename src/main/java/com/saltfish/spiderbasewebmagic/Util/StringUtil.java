package com.saltfish.spiderbasewebmagic.Util;

import java.io.*;

/**
 * Description:
 * 和字符串相关的工具
 * spider-base-webmagic
 *
 * @Date: 2019/2/20 20:59
 * @Author: James Lin
 * @Version: 1.0
 */

public class StringUtil {
    /**
     *  将传入的数据覆盖写入filePath指向的文件
     *
     * @param filePath 文件路径
     * @param data 数据
     * @return
     * @Date: 2019/2/20-21:14
     */
    public static void OverrideStringToFile(String filePath, String data) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.append(data + "\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *  将传入的数据写入filePath指向的文件
     *
     * @param filePath 文件路径
     * @param data 数据
     * @return
     * @Date: 2019/2/20-21:14
     */
    public static void WriteStringToFileEnd(String filePath,String data) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data+"\r\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String filePath = "D:\\Spider.txt";
        //覆盖上一次写入数据
//         new StringUtil().WriteStringToFile(filePath,"天气不错!");
//         new StringUtil().WriteStringToFile2(filePath);
//        new StringUtil().WriteStringToFile5(filePath);
    }
}
