
package com.coder.dream.base.file.upload.support;

import java.util.UUID;

import com.coder.dream.base.file.upload.FileStoreStrategy;
import com.coder.dream.base.utils.FileUtil;

/**
 * 默认文件储存策略
 * <p>
 * 	1.文件存储于指定根目录
 *  2.文件名称按UUID命名
 * </p>
 */
public class DefaultFileStoreStrategy implements FileStoreStrategy{
	
	protected String rootPath;
	
	public String getStoreDir() {
		return rootPath;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getStoreFileName(String sourceName) {
		String suffix = FileUtil.getFileSuffix(sourceName);
		UUID randomUUID = UUID.randomUUID();
		String desName = randomUUID.toString() + suffix;
		return desName;
	}
}

