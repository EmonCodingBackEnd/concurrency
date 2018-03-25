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
package com.finalcoding.concurrency.example.singleton;

import com.finalcoding.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180324 23:35</font><br>
 * 单例实例在第一次使用时进行创建
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {
    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
