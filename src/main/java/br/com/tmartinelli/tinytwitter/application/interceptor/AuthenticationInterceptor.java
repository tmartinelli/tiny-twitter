package br.com.tmartinelli.tinytwitter.application.interceptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.application.controller.AuthenticationController;

@Intercepts
@RequestScoped
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

	@BeforeCall
    public void before() {
		if (!loggedUser.isLogged()) {
        	result.redirectTo(AuthenticationController.class).login();
        }
    }
	
	@Accepts
	public boolean accepts(ControllerMethod method) {
	    return method.containsAnnotation(Restrict.class);
	}
}
