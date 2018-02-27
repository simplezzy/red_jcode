
 ReentrantLock && Snytronized
并发的线程数量多，频繁创建会大大降低效率，创建和销毁均需要时间---解决办法：  线程池
ThreadPoolExecutor
1.Java中的ThreadPoolExecutor
  java.uitl.concurrent.ThreadPoolExecutor类是线程池中最核心的一个类，ThreadPoolExecutor继承了AbstractExecutorService类，并提供了四个构造器
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
              BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory);
    构造器参数：
      corePoolSize: 池大小  默认，创建池后，线程数为0，当有任务来时，创建线程执行，达到corePoolSize后，会放入缓存队列
      maximumPoolSize：线程池最大线程数
      keepAliveTime：线程没有任务执行时最多保持多久时间会终止，默认 只有大于corePoolSize才会起作用
      unit：参数keepAliveTime的时间单位
      workQueue：一个阻塞队列，用来存储等待执行的任务
           ArrayBlockingQueue;
           LinkedBlockingQueue;
           SynchronousQueue;
      threadFactory：线程工厂，主要用来创建线程；
      handler：表示当拒绝处理任务时的策略，有以下四种取值：
          ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
          ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
          ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
          ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

      AbstractExecutorService是一个抽象类，它实现了ExecutorService接口。

      execute()
      submit()  能够返回任务执行的结果，去看submit()方法的实现，会发现它实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果
      shutdown()
      shutdownNow()

2、深入剖析线程池实现原理
   .线程池状态
   .任务的执行
     execute--->
         corePoolSize || maxCorePoolSize
         ReentrantLock
         threadFactory--->worker(new Thread())---->
              run()---->getTask()
      如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务
      如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；
         若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
      如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理
      如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；
         如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。


     不过在java doc中，并不提倡我们直接使用ThreadPoolExecutor，而是使用Executors类中提供的几个静态方法来创建线程池
      Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE  corePoolSize设置为0，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程
      Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池  corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue
      Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池  corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；

 合理配置线程池大小
    如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1
    如果是IO密集型任务，参考值可以设置为2*NCPU
