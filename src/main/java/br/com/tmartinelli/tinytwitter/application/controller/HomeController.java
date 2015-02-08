package br.com.tmartinelli.tinytwitter.application.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.tmartinelli.tinytwitter.application.Restrict;

@Controller
public class HomeController {

	@Get("/")
	@Restrict
	public void home() {
		
	}
}
