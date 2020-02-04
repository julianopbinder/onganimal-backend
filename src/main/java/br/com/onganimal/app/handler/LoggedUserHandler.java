package br.com.onganimal.app.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import br.com.onganimal.app.annotation.LoggedUser;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.service.OwnerService;
import br.com.onganimal.app.util.JwtTokenUtils;

/**
 * Classe responsvel por fazer a injeção do owner logado nos controlles que tem a anotação LoggedUser
 */

public class LoggedUserHandler implements HandlerMethodArgumentResolver {
	
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";
	
	private ApplicationContext context;
	
	public LoggedUserHandler(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(LoggedUser.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Owner owner = null;
		
		try {
			HttpServletRequest request
					= (HttpServletRequest) webRequest.getNativeRequest();
	
			String token = request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
	
			JwtTokenUtils jwtTokenUtils = context.getBean(JwtTokenUtils.class);
			String username = jwtTokenUtils.getUsernameFromToken(token);
					
			if (username.isEmpty()) {
				return WebArgumentResolver.UNRESOLVED;
			} else {
				OwnerService ownerService = context.getBean(OwnerService.class);
				owner = ownerService.getByEmail(username);
			}
		} catch (Exception e) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return owner;
		
	}

}