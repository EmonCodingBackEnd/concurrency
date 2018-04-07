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
package com.coding.concurrency.example.syncContainer;

import com.coding.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

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
@NotThreadSafe
public class VectorExample2 {

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            Thread thread1 =
                    new Thread() {
                        @Override
                        public void run() {
                            for (int i = 0; i < list.size(); i++) {
                                list.remove(i);
                            }
                        }
                    };
            Thread thread2 =
                    new Thread() {
                        @Override
                        public void run() {
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i);
                            }
                        }
                    };

            thread1.start();
            thread2.start();
        }
    }
}
