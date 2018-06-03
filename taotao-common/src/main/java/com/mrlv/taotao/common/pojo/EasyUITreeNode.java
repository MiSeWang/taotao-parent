package com.mrlv.taotao.common.pojo;

/**
 * easyUI树形控件节点格式
 * 2.3.	EasyUI tree数据结构
 * 数据结构中必须包含：
 * @Id	节点id
 * @Text	节点名称
 * @State	如果不是叶子节点就是close，叶子节点就是open。Close的节点点击后会在此发送请求查询子项目。
 */
public class EasyUITreeNode {
	
	private Long id;
	private String text;
	private String state;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
