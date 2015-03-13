
package com.coder.dream.demo.web.vo;

public class DemoVo {
	
	private String param1;
	
	private String childName;

	public DemoVo(){
		
	}
	
	public DemoVo(String param1,String childName){
		this.param1 = param1;
		this.childName = childName;
	}
	
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
}

