package com.revature.spring_mvc.web.util;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    String[] allowedUsers();
}
