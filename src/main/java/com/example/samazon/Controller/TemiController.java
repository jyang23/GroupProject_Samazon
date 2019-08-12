package com.example.samazon.Controller;


import com.example.samazon.Beans.Order;
import com.example.samazon.Beans.Product;
import com.example.samazon.Beans.User;
import com.example.samazon.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
public class TemiController {

    @RequestMapping("/")
    public String homepage() {
        return "homepage";
    }

    @RequestMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/add")
    public String testPage(Model model) {
        Order order = new Order();
//        order.setDate(new Date());
//        order.setProducts();


        model.addAttribute("order", order);
        return "testpage";
    }

    @PostMapping("/process")
    public String processForm(@Valid Order order, BindingResult result) {
//        if (result.hasErrors()) {
//            return "testpage";
//        }
//        product.setUser(userService.getUser());
//        productRepository.save(product);
        return "redirect:/";
    }

    @RequestMapping("/myprofile")
    public String getProfile(Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);
        model.addAttribute("myuser", user);
        return "profile";
    }
}

