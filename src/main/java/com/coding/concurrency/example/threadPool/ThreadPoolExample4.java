/*
 * 文件名称：ThreadPoolExample1.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180326 07:10
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180326-01         Rushing0711     M201803260710 新建文件
 ********************************************************************************/
package com.coding.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * [请在此输入功能简述].
 *
 * <p>创建时间: <font style="color:#00FFFF">20180326 07:10</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        //        executorService.schedule(
        //                new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        log.warn("schedule run");
        //                    }
        //                },
        //                3,
        //                TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        log.warn("schedule run");
                    }
                },
                1,
                3,
                TimeUnit.SECONDS);
        //        executorService.shutdown();

        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        log.warn("timer run");
                    }
                },
                new Date(),
                5 * 1000);
    }
}
