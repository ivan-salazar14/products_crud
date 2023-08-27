package io.isalazar.products.products.controller;

import io.isalazar.products.products.model.ProductsDTO;
import io.isalazar.products.products.service.ProductsService;
import io.isalazar.products.products.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/productss")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(final ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("productss", productsService.findAll());
        return "products/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("products") final ProductsDTO productsDTO) {
        return "products/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("products") @Valid final ProductsDTO productsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "products/add";
        }
        productsService.create(productsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("products.create.success"));
        return "redirect:/productss";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("products", productsService.get(id));
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("products") @Valid final ProductsDTO productsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "products/edit";
        }
        productsService.update(id, productsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("products.update.success"));
        return "redirect:/productss";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        productsService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("products.delete.success"));
        return "redirect:/productss";
    }

}
