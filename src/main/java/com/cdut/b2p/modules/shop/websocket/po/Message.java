package com.cdut.b2p.modules.shop.websocket.po;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

	private String id;
	// 发送者
	private String from_id;
	// 发送者
	private String from_img;
	// 发送者
	private String from_name;
	// 接收者
	private String to_id;
	// 接收者
	private String to_img;
	// 接收者
	private String to_name;
	// 发送的文本
	private String msg;
	// 发送日期
	private Date date;
	
	private String status;
	
	private String type;
	
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getFrom_img() {
		return from_img;
	}
	public void setFrom_img(String from_img) {
		this.from_img = from_img;
	}
	public String getFrom_name() {
		return from_name;
	}
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}
	public String getTo_img() {
		return to_img;
	}
	public void setTo_img(String to_img) {
		this.to_img = to_img;
	}
	public String getTo_name() {
		return to_name;
	}
	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
