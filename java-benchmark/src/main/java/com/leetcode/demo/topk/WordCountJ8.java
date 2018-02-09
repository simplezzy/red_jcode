package com.leetcode.demo.topk;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhiyu.zhou on 2018/2/9
 */

/**
 * 思想：利用java fork&&join 机制，通过parallel Stream实现
 * 条件：Java8环境
 */
public class WordCountJ8 {

    private static final Integer ThreadNum = 5;

    private static final Integer TOP_K = 10;

    public static final Logger log = Logger.getLogger(WordCountJ8.class.getSimpleName());

    public static void main(String[] args) {

        //5 stream threads
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(ThreadNum));
        long start = System.currentTimeMillis();
        // get file array
        Optional<File[]> targetFileArr = Optional.ofNullable(FileSystemView.getFileSystemView().getDefaultDirectory()
                .listFiles(pathname -> pathname.getName().endsWith(".txt")));

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
                .collect(Collectors.groupingByConcurrent(word -> word, Collectors.counting()))
                .entrySet().stream()
                .parallel()
                //sort
                .sorted(Map.Entry.comparingByValue((v1, v2) -> (int) (v2 - v1)))
                //top 100
                .limit(TOP_K)
                //output
                .map(entry -> entry.getKey() + "\t:\t" + entry.getValue())
                .forEachOrdered(System.out::println);
        //runtime
        System.out.println("runtime:" + (System.currentTimeMillis() - start));
    }
}
