package com.example.samazon.Controller;

import com.example.samazon.Beans.History;
import com.example.samazon.Beans.Order;
import com.example.samazon.Beans.Product;
import com.example.samazon.Beans.User;
import com.example.samazon.CustomUserDetails;
import com.example.samazon.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Controller
public class JosephController {

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
            userRepository.save(user);
            model.addAttribute("created", true);
        }
        return "login";
    }

    @RequestMapping("/list")
    public String homePage(Principal principal, Model model) {
        model.addAttribute("list", productRepository.findAll());
        return "show";
    }

    @RequestMapping("/finalize")
    public String finalize(Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        for (History history : historyRepository.findAllByUserAndStatus(user, 0)) {
            history.setStatus(1);
            historyRepository.save(history);
        }
        Order order = orderRepository.findByUserAndOrdered(user, 0);
        order.setOrdered(1);
        orderRepository.save(order);
        model.addAttribute("list", productRepository.findAll());
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "search") String text, Principal principal, Model model) {
        text = text.toLowerCase();
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getName().toLowerCase().contains(text)) {
                products.add(product);
            }
        }
        model.addAttribute("list", products);
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);
        return "show";
    }

    @RequestMapping("/addCart/{id}")
    public String add(@PathVariable("id") long id, Principal principal, Model model) {
        model.addAttribute("list", productRepository.findAll());
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);

        History history = new History();
        history.setUser(user);

        Product product = productRepository.findById(id);
        history.setProductId(product.getId());
        historyRepository.save(history);
        return "show";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);

        ArrayList<History> historyList = historyRepository.findAllByProductId(id);
        if (historyList.size() > 0) {
            historyRepository.deleteById(historyList.get(0).getId());
        }
        historyList = historyRepository.findAllByUser(user);
        return addToCart(model, historyList);
    }

    @RequestMapping("/cart")
    public String myCart(Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);
        ArrayList<History> historyList = historyRepository.findAllByUserAndStatus(user, 0);
        return addToCart(model, historyList);
    }

    @RequestMapping("/checkout")
    public String checkout(Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", user);
        model.addAttribute("users", user);
        Order toDelete = orderRepository.findByUserAndOrdered(user, 0);
        if (toDelete != null) {
            orderRepository.delete(toDelete);
        }
        Order order = historyToOrder(user);
        orderRepository.save(order);
        model.addAttribute("order", order);
        return "shipping";
    }

    private String addToCart(Model model, ArrayList<History> historyList) {
        double total = 0;
        if (historyList.size() > 0) {
            ArrayList<Product> products = new ArrayList<>();
            for (History prev : historyList) {
                Product product = productRepository.findById(prev.getProductId());
                products.add(product);
                total += product.getPrice();
            }
            model.addAttribute("list", products);
        } else {
            model.addAttribute("list", null);
        }
        total = ((int) (total * 100)) / 100.0;
        model.addAttribute("total", total);
        return "cart";
    }

    private Order historyToOrder(User user) {
        ArrayList<History> historyList = historyRepository.findAllByUserOrderByProductId(user);
        Order order = new Order();
        double total = 0;
        if (historyList.size() > 0) {
            ArrayList<Product> products = new ArrayList<>();
            for (History prev : historyList) {
                Product product = productRepository.findById(prev.getProductId());
                products.add(product);
                total += product.getPrice();
            }
            order.setProducts(products);
            order.setDate(LocalDate.now().toString());
            double tax = total * 0.06;
            order.setUser(user);
            total = ((int) (total * 100)) / 100.0;
            tax = ((int) (tax * 100)) / 100.0;
            order.setTotal(total);
            order.setTax(tax);
            return order;
        } else {
            return null;
        }
    }

//    class ObjectCount {
//        private Object object;
//        private int count;
//
//        ObjectCount(Object object, int count) {
//            this.object = object;
//            this.count = count;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public Object getObject() {
//            return object;
//        }
//
//        public void setObject(Object object) {
//            this.object = object;
//        }
//    }
}
