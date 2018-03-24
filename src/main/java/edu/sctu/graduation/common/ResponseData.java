package edu.sctu.graduation.common;

public class ResponseData<T> {

	private Integer code = 200;
	private String msg = "success";
//	private List<Object> data;
	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	//	public List<Object> getData() {
//		return data;
//	}
//
//	public void setData(List<Object> data) {
//		this.data = data;
//	}
}
