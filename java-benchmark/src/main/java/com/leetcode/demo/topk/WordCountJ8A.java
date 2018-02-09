package com.leetcode.demo.topk;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by zhiyu.zhou on 2018/2/9.
 */

/**
 * reduce 优化
 */
public class WordCountJ8A {

    private static final Integer ThreadNum = 5;

    private static final Integer TOP_K = 10;

    private static final String EMPTY_STRING = "";

    public static final Logger log = Logger.getLogger(WordCountJ8.class.getSimpleName());

    public static void main(String[] args) {

        //5 stream threads
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(ThreadNum));
        long start = System.currentTimeMillis();
        // get file array
        Optional<File[]> targetFileArr = Optional.ofNullable(FileSystemView.getFileSystemView().getDefaultDirectory()
                .listFiles(pathname -> pathname.getName().endsWith(".txt")));

        ConcurrentMap<String, Long> map = new ConcurrentHashMap<>();
        Arrays.stream(targetFileArr.orElse(new File[0])) // file stream
                //parallel
                .parallel()
                //to line stream
                .flatMap(targetFile -> {
                    try {
                        return Files.lines(targetFile.toPath());
                    } catch (IOException e) {
                        log.log(Level.SEVERE, "", e);
                    }
                    return Stream.empty();
                })
                // get word by splitting ','
                .flatMap(line -> Arrays.stream(line.split(",")))
                // groupBy and the result: Map<String, Long>
                //
                .reduce(EMPTY_STRING, (pWord, word) -> {
                    map.merge(word, 1L, (ov, nv) -> ov + nv);
                    return EMPTY_STRING;
                });

         map.entrySet().stream()
                .parallel()
                //sort
                .sorted(Map.Entry.comparingByValue((v1, v2) -> (int) (v2 - v1)))
                //top 100
                .limit(TOP_K)
                //output
                .map(entry -> entry.getKey() + "：\t" + entry.getValue())
                .forEachOrdered(System.out::println);
        //runtime
        System.out.println("runtime:" + (System.currentTimeMillis() - start));
    }
}
