package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserServices userServices;
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //Invalidar la session actual si existe
        if (session != null) {
            //Tanca la sessio
            session.invalidate();
        }
        System.out.println("Log Out tancada la sessio");
        return "redirect:/movieSearch"; // Puedes redirigir a la página de inicio de sesión u otra página según tu aplicación
    }
    @PostMapping("/login")
    public String login(
            HttpSession session,
            Model model,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletResponse resp
    ) {
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        Long lastFailedLoginTime = (Long) session.getAttribute("lastFailedLoginTime");
        boolean userInPause = false;
        userServices.setEmail(email);
        userServices.setPassword(userServices.encriptarPassword(password));
       // String nameUser=userServices.getNameOfUser(email);
        if (loginAttempts == null || loginAttempts < 3) {
            if (userServices.validLogin(userServices.getEmail(), userServices.getPassword())) {
                session.setAttribute("email", userServices.getEmail());
                session.setAttribute("loginAttempts", 0);
                session.setAttribute("usuariLogueat", true);
                //session.setAttribute("name",nameUser);
                //Redireccio a movieSearch
                return "redirect:/adminArea";
            } else {
                if (loginAttempts == null) {
                    loginAttempts = 1;
                } else {
                    loginAttempts++;
                }
                session.setAttribute("loginAttempts", loginAttempts);
                model.addAttribute("missatgeError", "No se ha pogut fer el login");
                return "login";
            }
        } else if (loginAttempts >= 3 && (System.currentTimeMillis() - lastFailedLoginTime) < 60000) {
            loginAttempts = 1;
            session.setAttribute("loginAttempts", loginAttempts);
            userInPause = false;
        } else {
            userInPause = true;
        }
        if (userInPause) {
            model.addAttribute("missatgeErrorAttempts", "Has pasat el nombre maxim de intents. Per tornar a provar has de esperar 1 minut.");
            return "login";
        } else {
            session.setAttribute("lastFailedLoginTime", System.currentTimeMillis());
            return "login";
        }
    }
}
