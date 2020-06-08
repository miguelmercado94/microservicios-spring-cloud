package com.miguelMercado.project3.controller;
import java.util.List;
import java.util.Map;

public class ErrorMessage {

	private String mode;
	private List<Map<String,String>> message;
	
	public ErrorMessage() {
	}
	
	public ErrorMessage(String mode, List<Map<String, String>> message) {
		this.mode = mode;
		this.message = message;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<Map<String, String>> getMessage() {
		return message;
	}
	public void setMessage(List<Map<String, String>> message) {
		this.message = message;
	}
	
	
	
}