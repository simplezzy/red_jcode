package com.leetcode.demo.topk;

/**
 * Created by zhiyu.zhou on 2018/2/9
 */
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
/**
 * Q:100个文本文件，预计一共涉及单词数量1w左右，由5个线程并发计算，全部计算完合并结果，选出频率最高的100个单词和频数
 */

/**
 * 每个小文件中使用HashMap,以单词为key，出现次数为value,选出所有文件中top100
 * 假设：1、文件路径--假设文件路径：默认为/User/Document/xxx.txt (100个txt文件)  也可自定义
 *      2、单词的存在形式--假设在每个文件中以‘,’隔开
 *      3、服务器的形式--假设运行环境单机多核，5个线程的计算较为平常
 *
 *算法思路：
 *     1、创建5个线程,由每个线程的Hashmap统计单词和频次
 *     2、通过future获取每个线程的结果
 *     3、merge操作
 *     4、维护topK的最小堆,获取topk 的Map
 *算法优化：
 *     1、动态维护线程task而不是均分给每个线程，即线程分到的解析文件数量是最优的，保持最小的空间时间（A可能处理30个File B可能只处理10个）
 *     2、读取文件统计结果，采用java nio优化，提高效率
 *     3、采用MinHeap维护topk, 优化HashMap的排序算法效率
 */
public class Wordcount {

    private static final int ThreadNum = 5;

    private static final int TOP_K = 10;

    private static String path = null; //default:/User/Document/ (also can be referred)

    public static final Logger log = Logger.getLogger(Wordcount.class.getSimpleName());

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(ThreadNum);

        Future<Map<String, Integer>>[] futures = new Future[ThreadNum];

        long start = System.currentTimeMillis();

        try {
            for (int i = 0; i < ThreadNum; i++) {
               futures[i] = executorService.submit(new Callable<Map<String, Integer>>() {
                   @Override
                   public Map<String, Integer> call() throws Exception {
                       Map<String, Integer> map = new HashMap<>();
                       while (true) {
                           File file = FileLoader.getFile();
                           if(null == file) {
                               break;
                           }
                           map = FileToMap(file);    //read by line
                           //map = FileToWordMap(file);  //by java nio
                       }
                       return map;
                   }
               });
            }
            Map<String, Integer> map = mergeAllMap(futures);
            //getTopK
           // Map<String, Integer> topMap = getTopK(map, TOP_K);    //full sort
            Map<String, Integer> topMap = getHeapTopK(map, TOP_K);  //minheap sort
           //output
            outputResult(topMap);
            System.out.println("runtime:" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * the result output
     * @param map
     */
    private static void outputResult(Map<String, Integer> map) {
        System.out.println("the result of top100 words：");
        for(Map.Entry<String, Integer> entry : map.entrySet()){
          System.out.println(entry.getKey() + ":\t" + entry.getValue());
      }
    }

    /**
     * get the topK by full sort
     * @param map
     * @param k
     * @return
     */
    private static Map<String, Integer> getTopK(Map<String, Integer> map, int k)
    {
        Map<String, Integer> topMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue() + o2.getValue();
            }
        });
        int num = 0;
        for (Map.Entry<String, Integer> entry : list) {
            num++;
            if(num > k) {
                break;
            }
            topMap.put(entry.getKey(), entry.getValue());
        }
        return topMap;
    }

    /**
     * get topK by minHeap
     * @param map
     * @param k
     * @return
     */
    private static Map<String, Integer> getHeapTopK(Map<String, Integer> map, int k) {

        Map<String, Integer> topMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //size > k then minheap sort
        List<Map.Entry<String, Integer>> topk = list.size() <= k ? list : MinHeap.topK(list, k);
        //from low to top
        Collections.sort(topk, new Comparator<Map.Entry<String,Integer>>() {

            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(Map.Entry<String, Integer> top : topk) {
            topMap.put(top.getKey(), top.getValue());
        }
        return topMap;
    }

    /**
     * merge the map result from all threads
     * @return
     */
    private static Map<String, Integer> mergeAllMap(Future<Map<String, Integer>>[] futures) throws  Exception {
        Map<String, Integer> map = new HashMap<>();

        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> resultMap = future.get();
            //merge all map from the threads
            for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                String word = entry.getKey();
                Integer wordCount = entry.getValue();
                Integer wordAllCount = map.get(word);

                if(null != wordAllCount) {
                    map.put(word, wordAllCount + wordCount);
                } else {
                    map.put(word, wordCount);
                }
            }
        }
        return map;
    }

    /**
     * read file by line then count the word/frequency
     * @param file
     * @return
     */
    private static Map<String, Integer> FileToMap(File file) {
        Map<String, Integer> fileMap = new HashMap<>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                for(String word : words) {
                    Integer wordCont = (fileMap.get(word) == null ? 0 : fileMap.get(word));
                    fileMap.put(word, ++wordCont);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileMap;
    }

    /**
     * read file and build the word map by java.nio method
     * @param file
     * @return
     */
    private static Map<String, Integer> FileToWordMap(File file) {
        Map<String, Integer> map = new HashMap<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                buffer.clear();
                if(fileChannel.read(buffer) == -1) {
                    break;
                }
                buffer.flip();
                bufferToWordMap(buffer, map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return map;
    }

    /**
     * count the word and frequency
     * @param buffer
     * @param map
     */
    private static void bufferToWordMap(ByteBuffer buffer, Map<String, Integer> map) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < buffer.limit(); i++) {
            if(',' != (char)buffer.get()) {
                str.append((char) buffer.get());
            } else {
               Integer count = map.get(str.toString());
               if(null == count) {
                   map.put(str.toString(), 1);
               } else {
                   map.put(str.toString(), ++count);
               }
                str = new StringBuilder();
            }
        }
    }

    /**
     * File Loader Class
     */
   static class FileLoader {
        private static List<File> fileList = getFileList(path);
        private static int index = 0;

        public synchronized static File getFile() {
            //all file are be read
            if (index == fileList.size()) {
                return null;
            }
            File file = fileList.get(index);
            index++;
            return file;
        }

       /**
        * get all files in the direction path ending with ".txt"
        * @param path
        * @return
        */
        private static List<File> getFileList(String path) {
            List<File> fileList = new ArrayList<>();
            File dirFile = FileSystemView.getFileSystemView().getDefaultDirectory();
            if(null != path) {
                dirFile = new File(path);
            }
            if(!dirFile.exists() || !dirFile.isDirectory()) {
                return null;
            }
            File[] files = dirFile.listFiles();
            //filter the invalid file(not ending with xxx.txt)
            for(File file : files) {
                if(file.getName().endsWith(".txt")) {
                    fileList.add(file);
                }
            }
            log.info("the total read file nums:" + fileList.size());
            return fileList;
        }
   }

    /**
     * MinHeap Class
     */
   static class MinHeap {

        //heap Object
        private List<Map.Entry<String, Integer>> list;

        /**
         * init
         */
        public MinHeap(List<Map.Entry<String, Integer>> list) {
            this.list = list;
            buildHeap();
        }

        /**
         * build min heap
         */
        public void buildHeap() {
            for(int i = list.size() / 2 - 1; i >= 0; i --) {
                adjustHeap(i);
            }
        }

        /**
         * heap adjust
         * @param i
         */
        private void adjustHeap(int i) {
            int left = left(i);
            int right = right(i);

            int min = i;

            if (left < list.size() && list.get(left).getValue() < list.get(i).getValue())
                min = left;

            if (right < list.size() && list.get(right).getValue() < list.get(min).getValue())
                min = right;

            if (i == min)
                return;

            swap(i, min);

            adjustHeap(min);
        }

        /**
         * right node index
         * @param i
         * @return
         */
        private int right(int i) {
            return (i + 1) << 1;
        }

        /**
         * left node index
         * @param i
         * @return
         */
        private int left(int i) {
            return ((i + 1) << 1) - 1;
        }

        /**
         * swap
         * @param i
         * @param j
         */
        private void swap(int i, int j) {
            Map.Entry<String, Integer> tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

        /**
         * the min data
         * @return
         */
        public Map.Entry<String, Integer> getRoot() {
            return list.get(0);
        }

        /**
         * update the root data then adjustHeap
         * @param root
         */
        public void setRoot(Map.Entry<String, Integer> root) {
            list.set(0, root);
            adjustHeap(0);
        }

        /**
         * get topK
         * @param data
         * @param k
         * @return
         */
        private static List<Map.Entry<String, Integer>> topK(List<Map.Entry<String, Integer>> data,int k) {
            List<Map.Entry<String, Integer>> topk = new ArrayList<>(k);
            for(int i = 0; i < k; i++) {
                topk.add(i, data.get(i));
            }

            MinHeap heap = new MinHeap(topk);

            for(int i= k;i<data.size();i++)
            {
                Map.Entry<String, Integer> root = heap.getRoot();

                if(data.get(i).getValue() > root.getValue())
                {
                    heap.setRoot(data.get(i));
                }
            }
            return topk;
        }
    }
}
