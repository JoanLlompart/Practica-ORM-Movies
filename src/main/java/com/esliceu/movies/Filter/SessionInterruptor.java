package com.esliceu.movies.Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterruptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("Dins el filtre.");
        String user = (String) session.getAttribute("email");
        request.setAttribute("email",user);
        if (user == null) {
            // si el usuari no esta logeat se redirigeix a el login
            System.out.println("No ets administrador");
            response.sendRedirect("/login");
        } else {
            System.out.println("Sessio activa");
        }
        return true;
    }
}
