package com.coder.dream.introduce.web.vo.sm;

import com.coder.dream.introduce.entity.sm.DictionaryItem;

/**
 * 适配select2数组渲染
 * 
 * @author konghang
 *
 */
public class DictionaryItemComboVo {

	private String id;
	
	private String text;

	public DictionaryItemComboVo(){
		
	}
	
	public DictionaryItemComboVo(DictionaryItem item){
		this.id = item.getValue();
		this.text = item.getName();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
