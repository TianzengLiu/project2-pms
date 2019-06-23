package com.champions.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.champions.annotations.Authen;
import com.champions.exceptions.UnauthorizedException;
import com.champions.models.User;
import com.champions.util.LoggedInUserGetter;

@Aspect
@Component
public class AuthAspect {
	
	
	private LoggedInUserGetter lg;
	@Autowired
	public AuthAspect(LoggedInUserGetter lg) {
		this.lg = lg;
	}
	
	
	@Around("@annotation(auth) && execution(* com.champions.controllers..*(..))")//learn more about p[ointcut expressions look up aspectJ documentation
	public Object authenticateUser(ProceedingJoinPoint pjp, Authen auth ) throws Throwable {
		User currentUser = lg.getLoggedInUser();
		System.out.println(currentUser);
		if(currentUser == null) {
			throw new UnauthorizedException();
		} else {
			for(String role : auth.roles()) {
				if(currentUser.getRole().getRoleName().equals(role) ) {
					return pjp.proceed();
				}
			}
			throw new UnauthorizedException();
		}
	}
		
}
