package com.mi.heap.util;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 文件操作
 * Created by WZL on 2018/3/25.
 */
public class FileUtils {
    /**
     *
     * @param bufferedWriter
     * @param params
     */
    public static void writeLine(BufferedWriter bufferedWriter, String... params) {
        try {
            for (String param : params) {
                bufferedWriter.write(param);
            }
            bufferedWriter.newLine();
        } catch (IOException e) {

        }
    }

    /**
     *
     * @param bufferedWriter
     * @param params
     */
    public static void writeSpaceLine(BufferedWriter bufferedWriter, String... params) {
        try {
            for (int i = 0; i < params.length - 1; i++) {
                bufferedWriter.write(params[i] + " ");
            }

            bufferedWriter.write(params[params.length - 1]);
            bufferedWriter.newLine();
        } catch (IOException e) {

        }
    }
}
