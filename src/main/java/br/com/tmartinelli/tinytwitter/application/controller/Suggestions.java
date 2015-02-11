package br.com.tmartinelli.tinytwitter.application.controller;

public class Suggestions {

	private String value;
	private String data;

	public Suggestions(String value, String data) {
		this.value = value;
		this.data = data;
	}

	public String getValue() {
		return value;
	}
	
	public String getData() {
		return data;
	}
}
