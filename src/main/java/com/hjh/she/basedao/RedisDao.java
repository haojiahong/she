package com.hjh.she.basedao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis Dao 接口，只是简单实现了set get 方法
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-27 下午4:42:23
 * 
 * 
 */
public class RedisDao {
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public void set(String key, String value) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		jedis.set(key, value);
	}

	public String get(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		return jedis.get(key);
	}
}
