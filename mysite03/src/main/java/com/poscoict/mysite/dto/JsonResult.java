package com.poscoict.mysite.dto;

public class JsonResult {
	private String result; /* "success" or "fail" */
	private Object data; /* if success, set */
	private String message; /* if fail, set */

	public JsonResult() {
	}

	public JsonResult(String result, Object data) {
		this.result = result;
		this.data = data;
	}

	public JsonResult(String result, String message) {
		this.result = result;
		this.message = message;
	}

	public static JsonResult success(Object data) {
		return new JsonResult("success", data);
	}

	public static JsonResult fail(String message) {
		return new JsonResult("fail", message);
	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "JsonResult [result=" + result + ", data=" + data + ", message=" + message + "]";
	}
}
