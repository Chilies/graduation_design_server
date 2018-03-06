package edu.sctu.graduation.common;

import java.util.List;



public class ResponseData {

	private Integer code = 200;
	private String msg = "success";
	private List<Object> data;


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
}
