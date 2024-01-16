package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.User;
import com.esliceu.movies.Repos.AdminRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private String email;
    private String password;
    @Autowired
    AdminRepo adminRepo;

    public String encriptarPassword(String password) {
        return DigestUtils.md5Hex(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminRepo getAdminRepo() {
        return adminRepo;
    }

    public void setAdminRepo(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public boolean validLogin(String email, String password) {
        User user = adminRepo.findByEmail(email);
        if (user == null) {
            System.out.println("Usuari no existent");
            return false;
        }
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            System.out.println("usuari existent" + "PasswordCorrecte");
            System.out.println(user.getPassword());
           // password = encriptarPassword(password);
            return true;
        } else {
            System.out.println("Usuari o password incorrectes");
            return false;
        }
    }

    public boolean registrarUsuari(String name, String email, String password) {
        User user = new User();
        user.setEmail(email);
        //hem de comprobar si existeix.
        user.setName(name);
        String encriptPass =encriptarPassword(password);
        user.setPassword(encriptPass);
        adminRepo.save(user);
        return true;
    }
}
