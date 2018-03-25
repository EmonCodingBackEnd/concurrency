/*
 * 文件名称：ArrayListExample.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180325 20:04
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180325-01         Rushing0711     M201803252004 新建文件
 ********************************************************************************/
package com.finalcoding.concurrency.example.concurrent;

import com.finalcoding.concurrency.annotations.NotThreadSafe;
import com.finalcoding.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * [请在此输入功能简述].
 *
 * <p>创建时间: <font style="color:#00FFFF">20180325 20:04</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    // 请求总数
    public static final int clientTotal = 5000;

    // 同时并发执行的线程数
    public static final int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            update(count);
                            semaphore.release();
                        } catch (InterruptedException e) {
                            log.error("exception", e);
                        }
                        countDownLatch.countDown();
                    });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int count) {
        map.put(count, count);
    }
}
