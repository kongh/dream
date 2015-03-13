
package com.coder.dream.base.file.upload;

/**
 * 文件存盘策略
 * 
 */
public interface FileStoreStrategy {
	
	/**
	 * 获取存储
	 */
	public String getStoreDir();
	
	/**
	 * 获取存储文件名称
	 * 
	 * @param sourceName 原文件名称
	 */
	public String getStoreFileName(String sourceName);
}

