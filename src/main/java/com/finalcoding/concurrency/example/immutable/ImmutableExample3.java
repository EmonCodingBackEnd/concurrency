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

import com.finalcoding.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
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
@ThreadSafe
public class ImmutableExample3 {

    private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    private static final ImmutableMap<Integer, Integer> map2 =
            ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
        // list.add(4);
        // set.add(4);
        // map.put(1, 4);
        // map2.put(1, 4);
        log.info("{}", map2.get(5));
    }
}
