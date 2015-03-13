
package com.coder.dream.base.utils;

public class FileUtil {

	public static String getFileSuffix(String file){
		int lastIndexOf = file.lastIndexOf(".");
		String suffix = file.substring(lastIndexOf);
		return suffix;
	}
}

