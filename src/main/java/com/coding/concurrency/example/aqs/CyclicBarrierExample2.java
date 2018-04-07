/*
 * 文件名称：CyclicBarrierExample1.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180325 22:41
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180325-01         Rushing0711     M201803252241 新建文件
 ********************************************************************************/
package com.coding.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * [请在此输入功能简述].
 *
 * <p>创建时间: <font style="color:#00FFFF">20180325 22:41</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(
                    () -> {
                        try {
                            race(threadNum);
                        } catch (InterruptedException e) {
                            log.error("InterruptedException", e);
                        } catch (BrokenBarrierException e) {
                            log.error("BrokenBarrierException", e);
                        } catch (TimeoutException e) {
                            log.error("TimeoutException", e);
                        }
                    });
        }

        executorService.shutdown();
    }

    private static void race(int threadNum)
            throws InterruptedException, BrokenBarrierException, TimeoutException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            log.warn("BrokenBarrierException", e);
        }
        log.info("{} continue", threadNum);
    }
}
