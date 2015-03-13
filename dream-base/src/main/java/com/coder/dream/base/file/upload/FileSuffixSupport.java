
package com.coder.dream.base.file.upload;

/**
 * 文件后缀支持
 * 
 */
public interface FileSuffixSupport {
	
	/**
	 * 是否重置支持的文件后缀
	 * 
	 */
	public boolean canResetSupportSuffixs();
	
	/**
	 * 默认支持的文件后缀
	 * 
	 */
	public String[] getSupportSuffixs();
	
	/**
	 * 追加支持的文件后缀
	 * 
	 * @param appendSuffixs
	 */
	public String[] appendSupportSuffixs(String[] appendSuffixs);
	
	/**
	 * 设置支持的文件后缀
	 * 
	 * @param suffixs
	 */
	public void setSupportSuffixs(String[] suffixs);
	
	/**
	 * 是否支持的后缀
	 */
	public boolean isSupportSuffix(String suffix);
}

