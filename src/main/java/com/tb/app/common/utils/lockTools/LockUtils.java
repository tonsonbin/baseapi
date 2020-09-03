package com.tb.app.common.utils.lockTools;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockUtils {

	/**
	 * 用于存放读写锁对象与key
	 */
	private static Map<String, EntityLockInfo> lConcurrentHashMap = new HashMap<String, EntityLockInfo>();
	/**
	 * 用于存放所有锁信息，对弱需求锁进行清理
	 */
	private static List<EntityLockInfo> keyInfos = new ArrayList<EntityLockInfo>();
	
	/**
	 * 缓存锁的最大数量
	 */
	private static Long lockMax = 10000l;
	
	/**
	 * 当距离当前时间超过nowToDis时被认定为弱需求锁
	 */
	private static Long nowToDis = 5000L;
	/**
	 * 根据key进入锁
	 * @param key
	 */
	public synchronized static void lock(String key) {
		
		EntityLockInfo lockInfo = null;

		//对缓存的锁进行弱需求锁维护，超过了缓存锁的最大数量则进行一次清理
		if (keyInfos.size() >= lockMax) {
			
			//清理一半
			Integer indexHaf = Math.round(keyInfos.size()/2);
			EntityLockInfo hafEntityLockInfo = keyInfos.get(indexHaf);
			
			Long hafTime = hafEntityLockInfo.getTimestamp();
			//判断这半节点是否超过nowToDis
			if (new Date().getTime() - hafTime >= nowToDis) {
				
				System.out.println("进行锁记录清理开始，锁长度："+keyInfos.size());
				
				//记录的锁已经超过最大锁数量进行清理，清理nowToDisPoint前面的
				List<EntityLockInfo> dropKeyInfos = keyInfos.subList(0, indexHaf);
				
				if (dropKeyInfos != null && dropKeyInfos.size() > 0) {
					//新的锁记录
					List<EntityLockInfo> newKeyInfos = keyInfos.subList(indexHaf, keyInfos.size());
					
					List<EntityLockInfo> sNewKeyInfos = new ArrayList<EntityLockInfo>();
					//清理缓存锁
					dropKeyInfos.forEach(item->{
						
						Boolean itemLocked = item.isLocked();
						if (!itemLocked) {
							
							//表示未锁定，可清理
							String itemKey = item.getKey();
							lConcurrentHashMap.remove(itemKey);
							
						}else {
							
							//处于锁定的锁放入新的锁记录中
							sNewKeyInfos.add(item);
							
						}
						
					});
					//重置锁记录
					if (newKeyInfos == null) {
						newKeyInfos = new ArrayList<EntityLockInfo>();
					}
					newKeyInfos.addAll(sNewKeyInfos);
					keyInfos = newKeyInfos;
					
				}
				
				System.out.println("进行锁记录清理结束，锁长度："+keyInfos.size());
			}
			
		}
		
		//判断是否存在key对应的锁
		if (lConcurrentHashMap.containsKey(key)) {
			
			//存在取对应key的锁
			lockInfo = lConcurrentHashMap.get(key);
			
		}else {

			//不存在创建新的锁
			ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
			Lock writeLock = readWriteLock.writeLock();
			
			lockInfo = new EntityLockInfo();
			lockInfo.setKey(key);
			lockInfo.setLock(writeLock);
			lockInfo.setTimestamp(new Date().getTime());
			//放入锁信息
			keyInfos.add(lockInfo);
			
		}
		
		//使锁生效
		lockInfo.lock();
		//将锁放入存放
		lConcurrentHashMap.put(key, lockInfo);
		
	}

	/**
	 * 根据key解开锁
	 * @param key
	 */
	public static void unLock(String key) {
		
		EntityLockInfo lockInfo = null;
		
		//判断是否存在该key对应的锁
		if (lConcurrentHashMap.containsKey(key)) {
			
			//找到对应的锁对象
			lockInfo = lConcurrentHashMap.get(key);
			
		}else {
			
			return;
			
		}
		
		//进行解锁
		lockInfo.unLock();
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		int i = 0;
		
		while (i<10000) {
			
			Thread.sleep(10l);
			System.out.println("=========>"+i);
			new Thread(() -> {
				
					String key = String.valueOf(100000+Math.round(Math.random()*99999));
				
			      lock(key);
			      try {
			        System.out.println(new Date() + key+" 1 s ");
			        try {
			          Thread.sleep(1000);
			        } catch (Exception ex) {
			        }
			        System.out.println(new Date() + key+" 1 e ");
			      } finally {
			        unLock(key);
			      }
			    }).start();
			
			new Thread(() -> {
				
				String key = String.valueOf(100000+Math.round(Math.random()*99999));
			
		      lock(key);
		      try {
		        System.out.println(new Date() + key+" 2 s ");
		        try {
		          Thread.sleep(1000);
		        } catch (Exception ex) {
		        }
		        System.out.println(new Date() + key+" 2 e ");
		      } finally {
		        unLock(key);
		      }
		    }).start();
			
			i++;
		}
		
		/*
		 * new Thread(() -> { lock("key2"); try { System.out.println(new Date() +
		 * "\tThread key2 started with read lock"); try { Thread.sleep(2000); } catch
		 * (Exception ex) { } System.out.println(new Date() + "\tThread key2 ended"); }
		 * finally { unLock("key2"); } }).start();
		 */
		
	}

}
