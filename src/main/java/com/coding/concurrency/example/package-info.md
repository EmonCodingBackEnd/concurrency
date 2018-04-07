# Java并发

## 一、线程安全性

### 1.1 原子性

​	同一时刻，只能有一个线程访问。

- Atomic包
- CAS算法
- synchronized
- Lock



### 1.2 可见性

​	一个线程对主内存的修改，可以及时的被另一个线程观察到。

- synchronized
- volatile



### 1.3 有序性 - happens-before原则

​	如果两个操作的执行次序，无法从happens-before原则推导出来，那么就无法保证有序性，虚拟机可以随意重排序。

- 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
- 锁定规则：一个unLock操作先行发生于后面对同一个锁的lock操作
- volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
- 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
- 线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作
- 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
- 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
- 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始



## 二、安全发布对象

### 2.1 发布与逸出

- 发布对象：使一个对象能够被当前范围之外的代码所使用
- 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见



### 2.2 安全发布的四种方法

- 在静态初始化函数中初始化一个对象引用
- 将对象的引用保存到volatile类型域或者AtomicReference对象中
- 将对象的引用保存到某个正确构造对象的final类型域中
- 将对象的引用保存到一个由锁保护的域中



## 三、线程安全策略

### 3.1 不可变对象

- 不可变对象需要满足的条件
  - 对象创建以后其状态就不能修改
  - 对象所有域都是final类型
  - 对象是正确创建的（在对象创建其间，this引用没有逸出）
- final关键字：类、方法、变量
  - 修饰类：不能被继承
    - String、Integer、Long
    - final类中的成员变量可以根据需要设置为final
    - final类中的成员方法，都会被隐式的设置为final
  - 修饰方法：
    - 锁定方法不被继承类修改
    - 效率（早期版本的final方法，会内嵌调用提升效率，但如果方法过于复杂，会大打折扣）
    - private方法会隐式的被指定为final的方法
  - 修饰变量：
    - final修饰的基本类型数据变量，初始化后不能修改
    - final修饰的引用类型的数据变量，初始化后不能指向另外的对象
- Collections.unmodifiableXXX : Collection、List、Set、Map...
- Guava : Collection、List、Set、Map...



### 3.2 线程封闭

- Ad-hoc 线程封闭：程序控制实现，最糟糕，忽略
- 堆栈封闭：局部变量，无并发问题
- ThreadLocal线程封闭：特别好的封闭方法



### 3.3 线程不安全类与写法

- StringBuilder非线程安全，StringBuffer线程安全
- SimpleDateFormat非线程安全，JodaTime线程安全
- ArrayList，HashSet，HashMap等Collections
- 先检查在执行：if(condition(a)) {handle(a);}



### 3.4 同步容器

- ArrayList -> Vector,Stack
- HashMap -> HashTable(key,value不能为null)
- Collections.synchronizedXXX(List,Set,Map) 



### 3.5 并发容器J.U.C

- ArrayList -> CopyOnWriteArrayList
  - 读在原数组，无需加锁
  - 写在复制出的副本，需要加锁，避免复制出多个副本
- HashSet,TreeSet -> CopyOnWriteArraySet,ConcurrentSkipListSet
  - ConcurrentSkipListSet不允许null
- HashMap,TreeMap -> ConcurrentHashMap,ConcurrentSkipListMap
  - ConcurrentHashMap不允许null
  - ConcurrentSkipListMap的key有序，适合高并发

安全共享对象的策略：

- 线程限制：一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改
- 共享只读：一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它
- 线程安全对象：一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问
- 被守护对象：一个被守护对象只能通过获取特定的锁来访问



## 四、J.U.C之AQS(AbstractQueuedSynchronizer)介绍

- 使用Node实现FIFO队列（双向链表），可以用于构建锁或者其他同步装置的基础框架
- 利用了一个int类型表示状态
- 使用的方法是继承
- 子类通过继承并通过实现它的方法管理器状态（acquire和release）的方法操纵状态
- 可以同时实现排它锁和共享锁的模式（独占、共享）



### 4.1 AQS同步组件

- CountDownLatch
- Semaphore
- CyclicBarrier
- ReentrantLock
- Condition
- FutureTask



### 4.2 ReentrantLock与锁

- ReentrantLock(可重入锁)和synchronized区别

  - 可重入性

    - 对于一个线程两者都可以重入

  - 锁的实现

    - ReentrantLock是JDK实现的
    - synchronized是JVMM实现的

  - 性能的区别

    - ReentrantLock
    - synchronized之前很慢，后来就好了

  - 功能区别

    - ReentrantLock使用灵活
    - synchronized使用简便

  - ReentrantLock独有的功能

    - 可指定是公平锁还是非公平锁（默认非公平锁），而synchronized只有非公平的锁
    - 提供了一个Condition类，可以分组唤醒需要唤醒的线程
    - 提供了能够中断等待锁的线程的机制，lock.lockInterruptibly()


### 4.3 J.U.C之FutureTask

- Callable与Runnable接口对比
- Future接口
- FutureTask类



### 4.4 Fork/Join框架



### 4.5 BlockingQueue

| -       | Thros Excepion | Special Value | Blocks | Times Out                   |
| ------- | -------------- | ------------- | ------ | --------------------------- |
| Insert  | add(o)         | offer(o)      | put(o) | offer(o, timeout, timeuint) |
| Remove  | remove(o)      | poll()        | take() | poll(timeout, timeunit)     |
| Examine | element()      | peek()        |        |                             |

- ArrayBlockingQueue
  - FIFO
- DelayQueue
- LinkedBlockingQueue
  - FIFO
- PriorityBlockingQueue
  - 带有优先级的队列
- SynchronousQueue
  - 无界非缓存队列



## 五、线程池

- new Thread弊端
  - 每次new Thread新建对象，性能差
  - 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多系统资源导致死机或OOM
  - 缺少更多功能，如更多执行、定期执行、线程中断
- 线程池的好处
  - 重用存在的线程，减少对象创建、消亡的开销，性能佳
  - 可有效控制最大并发线程的数量，提高系统资源利用率，同时可避免过多资源竞争，避免阻塞
  - 提供定时执行、定期执行、单线程、并发数控制等功能



### 5.1 ThreadPoolExecutor

- corePoolSize:核心线程数量
- maximumPoolSize:线程最大线程数
- workQueue:阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
- keepAliveTime:线程没有任务执行时最多保持多久时间终止
- unit:keepAliveTime的时间单位
- threadFactory:线程工厂，用来创建线程
- rejectHandler:当拒绝处理任务时的策略
  - 抛异常（默认）
  - 用调用者所在的线程来执行任务
  - 丢弃队列中最靠前的任务，来执行当前任务
  - 直接丢弃当前的任务



### 5.2 ThreadPoolExecutor状态

- RUNNING
- SHUTDOWN
- STOP
- TIDYING
- TERMINATED

ThreadPoolExecutor方法

### 5.3 ThreadPoolExecutor

- execute():提交任务，交给线程池执行
- submit():提交任务，能够返回执行结果  execute + Future
- shutdown():关闭线程池，等待任务都执行完
- shutdownNow():关闭线程池，不等待任务执行完
- getTaskCount():线程池已经执行和未执行的任务总数
- getCompletedTaskCount():已完成的任务数量
- getPoolSize():线程池当前的线程数量
- getActiveCount():当前线程池中正在执行任务的线程数量



### 5.4 Executor框架接口

- Executors.newCachedThreadPool
- Executors.newFixedThreadPool
- Executors.newScheduledThreadPool
- Executors.newSingleThreadExecutor



### 5.5 合理配置

- CPU密集型任务，就需要尽量压榨CPU，参考值可以设为NCPU+1
- IO密集型任务，参考值可以设为2*NCPU



## 六、多线程并发拓展

### 6.1 死锁

- 死锁发生的必要条件
  - 互斥条件
  - 请求和保持条件
  - 不剥夺条件
  - 环路等待条件



### 6.2 多线程并发最佳实践

- 使用本地变量
- 使用不可变类
- 最小化锁的作用域范围：S=1/(1-a+a/n)
- 使用线程池的Executor，而不是直接new Thread执行
- 宁可使用同步也不要使用线程的wait和notify方法
- 使用BlockingQueue实现生产-消费模式
- 使用并发集合而不是加了锁的同步集合
- 使用Semaphore创建有界的访问
- 宁可使用同步代码块，也不使用同步的方法
- 避免使用静态变量



### 6.3 Spring与线程安全

- Spring bean：Singleton、Prototype
- 无状态对象



### 6.4 HashMap与Concurrent HashMap



### 6.5 多线程并发与线程安全总结

- 线程安全性
  - 原子性
  - 可见性
  - 有序性
  - atomic包
  - CAS算法
  - synchronized与Lock
  - volatile
  - happens-before
- 安全发布对象
  - 安全发布方法
  - 不可变对象
  - final关键字使用
  - 不可变方法
  - 线程不安全类与写法
- 线程封闭同步容器并发容器
  - 堆栈封闭
  - ThreadLocal线程封闭
  - JDBC的线程封闭
  - 同步容器
  - 并发容器
  - J.U.C
- AQS等J.U.C组件
  - CauntDownLatch
  - Semaphore
  - CyclicBarries
  - ReentrantLock与锁
  - Condition
  - FutureTask
  - Fork/Join框架
  - BlockingQueue
- 线程调度
  - new Thread弊端
  - 线程池的好处
  - ThreadPoolExecutor
  - Executor框架接口
- 线程安全补充内容
  - 死锁的产生于预防
  - 多线程并发最佳实践
  - Spring的线程安全
  - HashMap与ConcurrentHashMap深入讲解



