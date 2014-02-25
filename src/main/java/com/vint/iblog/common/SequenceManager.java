package com.vint.iblog.common;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列管理器
 * Created by Vin on 14-2-17.
 */
public class SequenceManager {

    // 根据序列类型存储序列的缓冲池
    private static final Map<String, Queue<String>> sequence =
            new ConcurrentHashMap<String, Queue<String>>();


}
