package com.champions.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.champions.models.User;

@Aspect
@Component
public class AuthAspect {
	
	private LoggedInUserGetter lg;
	@Autowired
	public AuthAspect(LoggedInUserGetter lg) {
		this.lg = lg;
	}
	
	@Around("@annotation(auth) && execution(* com.champions.controllers..*(..))")
	public Object authenticateUser(ProceedingJoinPoint pjp, Authen auth) throws Throwable {
		User currentUser = lg.getLoggedInUser();
		
		if(currentUser == null) {
			throw new UnauthorizedException(HttpStatus.FORBIDDEN, "Sorry, you are unauthorized for this end point!");	
		} else {
			
			for (String role : auth.roles()) {
				if (currentUser.getRole().equals(role)) {
					return pjp.proceed();
				}
			}
			throw new UnauthorizedException(HttpStatus.FORBIDDEN, "Sorry, you are unauthorized for this end point!");
		}
		
		
	}

}
