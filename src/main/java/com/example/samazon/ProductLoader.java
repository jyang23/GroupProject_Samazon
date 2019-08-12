package com.example.samazon;

import com.example.samazon.Beans.Product;
import com.example.samazon.Beans.Role;
import com.example.samazon.Beans.User;
import com.example.samazon.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductLoader implements CommandLineRunner {
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

    @Override
    public void run(String... strings) throws Exception {

        //Constructors
        //==============================================================================================================
        Product product = new Product("Airpods", "listening device", 199.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/Airpods_gl3iyx.jpg");
        productRepository.save(product);

        product = new Product("Laptop", "computing device,m3 processor", 1199.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/laptop_m0qqkq.jpg");
        productRepository.save(product);

        product = new Product("iPhone XS", "cellular device made by Apple Inc", 899.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/iPhonexs_lyaetg.jpg");
        productRepository.save(product);

        product = new Product("Beats Headphones", "Noise-Canceling Headphones", 299.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/Beats_Headphone_qdldok.jpg");
        productRepository.save(product);

        product = new Product("Apple Display Monitor", "A 27inch Monitor", 699.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/Monitor_f7qjwt.jpg");
        productRepository.save(product);

        product = new Product("Apple iPad", "12.3 inch 32GB device", 499.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/iPad_yoqpfz.jpg");
        productRepository.save(product);

        product = new Product("RazerBlade Mouse", "Gaming Mouse", 39.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/mouse_fxwn0s.jpg");
        productRepository.save(product);

        product = new Product("RazerBlade Black Widow Keyboard", "Gaming Keyboard", 39.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/keyboard_d6f2az.jpg");
        productRepository.save(product);

        product = new Product("USB C Connector", "High Speed Data Transfer Cord", 19.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/usbc_zlkq4j.jpg");
        productRepository.save(product);

        product = new Product("Apple MagSafe2 Charger", "MacBook Pro Charger(2016+)", 99.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/maccharger_bicf4h.jpg");
        productRepository.save(product);

        product = new Product("Samsung 72-inch Smart AMOLED TV", "Smart TV", 1399.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/tv_gblckc.jpg");
        productRepository.save(product);

        product = new Product("ESPON HD Projector", "Classroom Projector", 499.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/epson_adkt7m.jpg");
        productRepository.save(product);

        product = new Product("Sony Camcorder", "Ultra HD Picture Quality", 199.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/camera_tde0ve.jpg");
        productRepository.save(product);

        product = new Product("32GB Sandisk Micro SD Card", "SD Card", 9.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/sd_prsboh.jpg");
        productRepository.save(product);

        product = new Product("Western Digital 3TB SSD", "ON SALE!!", 599.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/ssd_dp5e6s.jpg");
        productRepository.save(product);

        product = new Product("Brother Laser Ink-Jet Printer", "All-in-One Printer", 199.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199826/samazon/printer_dqy90e.jpg");
        productRepository.save(product);

        product = new Product("Samsung 64GB Flash Drive", "64GB", 29.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/flasdrive_t9tmut.jpg");
        productRepository.save(product);

        product = new Product("LG Mini Fridge", "Fridge", 159.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/fridge_majrf0.jpg");
        productRepository.save(product);

        product = new Product("Panasonic Microwave", "100W Microwave Oven", 79.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/microwave_l5aetg.jpg");
        productRepository.save(product);

        product = new Product("Digital Alarm Clock Radio", "Outdated product ON SALE!!", 5.99, true, "https://res.cloudinary.com/queentemi/image/upload/v1565199825/samazon/clock_p05ooq.webp");
        productRepository.save(product);
    }
}
