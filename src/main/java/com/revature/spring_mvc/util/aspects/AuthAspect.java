package com.revature.spring_mvc.util.aspects;

import com.revature.spring_mvc.exceptions.AuthenticationException;
import com.revature.spring_mvc.exceptions.InvalidRequestException;
import com.revature.spring_mvc.models.User;
import com.revature.spring_mvc.web.util.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuthAspect {

    private HttpServletRequest request;

    @Autowired
    public AuthAspect(HttpServletRequest request) {this.request = request;}

    @Around("@annotation(com.revature.spring_mvc.web.util.Secured)")
    public Object securedEndpoints(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured anno = method.getAnnotation(Secured.class);

        List<String> allowedUsers = Arrays.asList(anno.allowedUsers());
        HttpSession session = request.getSession(false);

        if(session == null){
            throw new AuthenticationException("No session found");
        }

        User requestingUser = (User) session.getAttribute("authUser");
        String username = requestingUser.getUsername();
        if(!allowedUsers.contains(username)){
            throw new InvalidRequestException("Forbidden request made to sensistive endpoint by:" + username);
        }

        Object returned = pjp.proceed(); // as long as the above works, it will continue on it's path to calling the endpoint

        System.out.println("Sesnsitive endpoint requested" + LocalDateTime.now().toString());

        return returned;
    }

}
