package br.com.tmartinelli.tinytwitter.infrastructure.jsp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;

@Named("format")
public class Format {

	private ResourceBundle bundle;

	public Format() {
		this(null);
	}
	
	@Inject
	public Format(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	public String localDateTime(LocalDateTime date) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(bundle.getString("format.datetime"));
		return date.format(pattern);
	}
}
