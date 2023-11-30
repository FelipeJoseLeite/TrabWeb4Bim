package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("cadastroCategoria");

        if (!model.containsAttribute("categoria")) {
            model.addAttribute("categoria", new Categoria());
            model.addAttribute("msg", new ArrayList<String>());
        }

        modelAndView.addObject("categoriaList", categoriaService.listAll());

        return modelAndView;
    }

    @PostMapping
    public String salvarCategoria(@Valid Categoria categoria,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoria", categoria);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);
        }else {
            categoriaService.insert(categoria);
        }

        return "redirect:/categoria";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaCategoria(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("cadastroCategoria");

        if (model.containsAttribute("categoria")) {
            modelAndView.addObject("categoria", new Categoria());
            modelAndView.addObject("msg", new ArrayList<String>());

        }

        return modelAndView;
    }


}
