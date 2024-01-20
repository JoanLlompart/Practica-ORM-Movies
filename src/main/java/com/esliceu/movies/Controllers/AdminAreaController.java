package com.esliceu.movies.Controllers;

import com.esliceu.movies.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminAreaController {
    @Autowired
    UserServices userServices;

    @GetMapping("/adminArea")
    public String adminGet(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        return "adminArea";

    }
    @PostMapping("/adminArea")
    public String adminPost(HttpServletRequest req, HttpSession session) {
        String email = (String) session.getAttribute("email");
        userServices.setEmail(email);

        return "adminArea";
    }



    /*
    @PostMapping("/add")
    public String addProducto(@ModelAttribute Producto producto) {
        productoService.addProducto(producto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        return "index";
    }

    @PostMapping("/update")
    public String updateProducto(@ModelAttribute Producto producto) {
        productoService.updateProducto(producto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return "redirect:/";
    }
    */

}
