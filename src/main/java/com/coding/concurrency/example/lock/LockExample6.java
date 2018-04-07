/*
 * 文件名称：ConcurrencyTest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180324 07:52
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180324-01         Rushing0711     M201803240752 新建文件
 ********************************************************************************/
package com.coding.concurrency.example.lock;

import com.coding.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [请在此输入功能简述].
 *
 * <p>创建时间: <font style="color:#00FFFF">20180324 07:52</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(
                        () -> {
                            try {
                                reentrantLock.lock();
                                log.info("wait signal"); // 1
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            log.info("get signal"); // 4
                            reentrantLock.unlock();
                        })
                .start();

        new Thread(
                        () -> {
                            reentrantLock.lock();
                            log.info("get lock"); // 2
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            condition.signalAll();
                            log.info("send signal ~ "); // 3
                            reentrantLock.unlock();
                        })
                .start();
    }
}
