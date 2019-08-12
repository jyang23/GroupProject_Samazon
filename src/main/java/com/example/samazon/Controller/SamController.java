package com.example.samazon.Controller;

import com.example.samazon.Beans.Order;
import com.example.samazon.Beans.User;
import com.example.samazon.CustomUserDetails;
import com.example.samazon.Repositories.*;
import com.example.samazon.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class SamController {

    @Autowired
    SSUserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/confirmation")
    public String confirmationCourses(Model model, Principal principal) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Order order = new Order();
        order = orderRepository.findByUserAndOrdered(user, 0);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "confirmation";
    }
}