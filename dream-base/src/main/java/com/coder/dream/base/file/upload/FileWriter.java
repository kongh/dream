
package com.coder.dream.base.file.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存盘器
 * 
 */
public interface FileWriter {
	/**
	 * 存储文件
	 * 
	 * @throws IOException 
	 */
	public List<File> write(MultipartFile... writingFile) throws IOException;
	
	/**
	 * 设置文件存盘策略
	 */
	public void setFileStoreStrategy(FileStoreStrategy strategy);
	
	/**
	 * 获取文件存盘策略
	 */
	public FileStoreStrategy getFileStoreStrategy();
}

