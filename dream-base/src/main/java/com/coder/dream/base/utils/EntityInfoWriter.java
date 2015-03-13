package com.coder.dream.base.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.coder.dream.base.entity.BaseEntity;
import com.coder.dream.base.web.session.SessionManager;

/**
 * 
 * 实体[新增/修改]信息填写工具类
 * 
 * @author konghang
 *
 */
public class EntityInfoWriter {

	/**
	 * 擦写<br><br>
	 * 
	 * 根据entity的id值，自动判断该实体是新增OR修改
	 * 
	 * @param entity
	 */
	public static void write(BaseEntity entity){
		if(StringUtils.isBlank(entity.getId())){
			writeForCreate(entity);
		}else{
			writeForUpdate(entity);
		}
	}
	
	/**
	 *  擦写<br><br>
	 *  
	 *  填写新增信息
	 *  
	 * @param entity
	 */
	public static void writeForCreate(BaseEntity entity){
		entity.setCreateDate(new Date());
		Object activeUserObj = SessionManager.getActiveUser();
		if(activeUserObj != null){
			BaseEntity activeUser = (BaseEntity) activeUserObj;
			entity.setCreateUserId(activeUser.getId());
		}
	}
	
	/**
	 * 擦写<br><br>
	 * 
	 * 填写更新信息
	 * 
	 * @param entity
	 */
	public static void writeForUpdate(BaseEntity entity){
		entity.setUpdateDate(new Date());
		Object activeUserObj = SessionManager.getActiveUser();
		if(activeUserObj != null){
			BaseEntity activeUser = (BaseEntity) activeUserObj;
			entity.setUpdateUserId(activeUser.getId());
		}
	}
}
