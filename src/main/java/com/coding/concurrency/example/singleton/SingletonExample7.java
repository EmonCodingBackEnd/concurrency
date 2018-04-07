/*
 * 文件名称：SingletonExample1.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180324 23:35
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180324-01         Rushing0711     M201803242335 新建文件
 ********************************************************************************/
package com.coding.concurrency.example.singleton;

import com.coding.concurrency.annotations.Recommend;
import com.coding.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180324 23:35</font><br>
 * 单例实例在第一次使用时进行创建
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {}

    public static SingletonExample7 getInstance() {
        return SingleTon.INSTANCE.getSingleton();
    }

    private enum SingleTon {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用了一次
        SingleTon() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
