package com.coder.dream.base.utils;

public class NullUtil {

	/**
	 * 判断是否为NULL
	 * 
	 * @param obj
	 * @return
	 */
	public static final boolean isNull(Object obj) {
		if (null == obj) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否为非NULL
	 * 
	 * @param obj
	 * @return
	 */
	public static final boolean isNotNull(Object obj) {
		if (isNull(obj)) {
			return false;
		} else {
			return true;
		}
	}

}
