/*
 * 文件名称：ImmutableExample1.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180325 09:01
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180325-01         Rushing0711     M201803250901 新建文件
 ********************************************************************************/
package com.finalcoding.concurrency.example.immutable;

import com.finalcoding.concurrency.annotations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * [请在此输入功能简述].
 *
 * <p>创建时间: <font style="color:#00FFFF">20180325 09:01</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private static final Integer a = 1;

    private static final String b = "2";

    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        // a = 2;
        // b = "3";
        // map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
        // a = 1;
    }
}
