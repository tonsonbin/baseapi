package com.tb.app.common.utils.lockTools;

import java.util.concurrent.locks.Lock;

/**
 * 键信息
 * @author Think
 *
 */
class EntityLockInfo {

	/**
	 * 锁对象
	 */
	private Lock lock;
	
	/**
	 * 
	 * 键
	 * 
	 */
	private String key;
	
	/**
	 * 加入锁的时间节点
	 */
	private long timestamp;
	
	/**
	 * 锁状态-是否是锁住
	 */
	private boolean locked;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isLocked() {
		return locked;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
	
	public void unLock() {
		
		this.lock.unlock();
		this.locked = false;
		
	}
	public void lock() {
		
		this.lock.lock();
		this.locked = true;
		
	}
	
}
