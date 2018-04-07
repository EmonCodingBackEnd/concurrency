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

import com.coding.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180324 23:35</font><br>
 * 单例实例在第一次使用时进行创建<br>
 * 懒汉模式 -> 双重同步锁单例模式
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {}

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample4.class) { // 同步锁
                if (instance == null) {
                    /*
                    第一步： memory = allocate() 分配对象的内存空间
                    第二步： ctorInstance() 初始化对象
                    第三步： instance = memory 设置instance指向刚分配的内存

                    如果JVM和CPU优化，发生了指令重排

                    第一步： memory = allocate() 分配对象的内存空间
                    第三步： instance = memory 设置instance指向刚分配的内存
                    第二步： ctorInstance() 初始化对象
                     */
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
