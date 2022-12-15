package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/create")
    public String creat(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        productService.save(product);
        redirect.addFlashAttribute("success", "Product has been create successfully!!!");
        return "redirect:/product";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Product has update successfully!!");
        return "redirect:/product";
    }

    @GetMapping("{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "view";
    }

    @GetMapping("{id}/delete")
    public String showRemoveForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "delete";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect){
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Product has been delete successfully!!!");
        return "redirect:/product";
    }
    @GetMapping("/search")
    public String searchByName(HttpServletRequest request, Model model){
        List<Product> products;
        String search = request.getParameter("search");
      products = productService.findByName(search);
       model.addAttribute("products", products);
       return "index";
    }
}
