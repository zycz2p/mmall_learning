package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by ausu on 2018/1/17.
 */
public class TokenCache {
    private static Logger logger= LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    //LRU算法
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载，当get的时候没有值，就用这个方法进行加载
                @Override
                public String load(String s) throws Exception {

                    return "null";
                }
            });

    public static void setKey(String key,String value)
    {
        localCache.put(key,value);
    }
    public static String getKey(String key)
    {
        String value = null;
        try {
            value = localCache.get(key);
            if("null".equals(value))
            {
                return null;
            }
            return value;
        }catch (Exception e)
        {
            logger.error("localCache get error value",e);

        }
        return null;
    }
}
