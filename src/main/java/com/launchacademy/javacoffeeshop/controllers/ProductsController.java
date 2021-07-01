package com.launchacademy.javacoffeeshop.controllers;

import com.launchacademy.javacoffeeshop.models.Product;
import com.launchacademy.javacoffeeshop.services.ProductService;
import com.launchacademy.javacoffeeshop.services.ProductSessionRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

  private ProductService productService;
  private String errors;

  @Autowired
  public void setProductService(ProductSessionRelatedService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.findAll());
    return "products/index";
  }

  @GetMapping("/show/{id}")
  public String showProduct(@PathVariable Integer id, Model model) {
    if (id <= 0 || id > productService.findAll().get(productService.findAll().size() - 1).getId()) {
      return "products/not_found";
    } else {
      model.addAttribute("product", productService.get(id));
      return "products/show";
    }
  }

  @GetMapping("/new")
  public String createProduct(@ModelAttribute Product product) {
    return "products/new";
  }

  @GetMapping("/errors")
  public String getErrors(Model model) {
    model.addAttribute("errors", errors);
    return "products/form_errors";
  }


  @PostMapping()
  public String addProduct(@ModelAttribute Product product) {
    product.setId(productService.findAll().get(productService.findAll().size() - 1).getId() + 1);
    if (product.validate().equals("")) {
      productService.addToList(product);
      return "redirect:/products";
    } else {
      errors = product.validate();
      return "redirect:/products/errors";
    }
  }

  @DeleteMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Integer id) {
    Product product = productService.get(id);
    product.setId(-1);
    return "redirect:/products";
  }
}
