package com.vote.system.utils;

import java.io.*;

public class IOUtil {
    private IOUtil() {}

    private static final int BUF_SIZE = 2048;

    public static String readAsString(File file) throws IOException {
        try(InputStream in = new FileInputStream(file)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            copy(in,baos);
            return new String(baos.toByteArray());
        }
    }

//    public static String readResourceAsString(String resource) {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
////        try(InputStream in = classLoader.getResourceAsStream(resource)) {
////
////        }
//    }
}
