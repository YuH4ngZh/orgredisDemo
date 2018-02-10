package com.dinge.service.impl;

import com.dinge.dao.JedisClientSingle;
import com.dinge.service.AddService;
import com.dinge.util.RedisCache;

import javax.annotation.Resource;
import java.util.Set;

public class AddServiceImpl implements AddService {
    @Resource
    private JedisClientSingle jedisClientSingle;


    @Override
    @RedisCache(type = String.class, expire = 200000000)
    public String setName( String key,  String value) {
        return jedisClientSingle.set(key, value);
    }

    @Override
    public String getName(final String x) {
        return jedisClientSingle.get(x);
    }

    @Override
    public long del(String key) {
        long l = jedisClientSingle.del(key);
        return l;
    }

    @Override
    public String incr(String key, String value) {
        long l = jedisClientSingle.incr(key);
        return String.valueOf(l);
    }

    @Override
    public Set<String> getAllKeys() {
        Set<String> keys = jedisClientSingle.getAll();
        return keys;
    }
}
