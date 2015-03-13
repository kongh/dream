
package com.coder.dream.base.file.upload.support;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * 按月分类管理文件策略
 * 
 */
public class MonthlyFileStoreStrategy extends DefaultFileStoreStrategy{

	@Override
	public String getStoreDir() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance();
		String format = dateFormat.format(date);
		return rootPath + File.separator + format;
	}
}

