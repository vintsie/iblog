package com.vint.iblog.common.cache;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheLoader;

import java.util.Map;

/**
 *
 * Created by Vin on 14-4-12.
 */
public interface CacheDataLoader extends CacheLoader {

    /**
     * 缓存数据加载接口
     * @return 数据容器
     * @throws Exception
     */
    public Map<String, Object> loadData() throws Exception;
}
