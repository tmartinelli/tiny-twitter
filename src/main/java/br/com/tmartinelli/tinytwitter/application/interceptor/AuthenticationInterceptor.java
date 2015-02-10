package br.com.tmartinelli.tinytwitter.application.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.application.controller.AuthenticationController;

@Intercepts
@AcceptsWithAnnotations(Restrict.class)
public class AuthenticationInterceptor {

	private LoggedUser loggedUser;
	private Result result;

	public AuthenticationInterceptor() {
		this(null, null);
	}
	
	@Inject
	public AuthenticationInterceptor(LoggedUser loggedUser, Result result) {
		this.loggedUser = loggedUser;
		this.result = result;
	}

	@AroundCall
    public void around(SimpleInterceptorStack stack) {
		if (loggedUser.isLogged()) {
			stack.next();
        } else {
        	result.redirectTo(AuthenticationController.class).login();
        }
    }
}
