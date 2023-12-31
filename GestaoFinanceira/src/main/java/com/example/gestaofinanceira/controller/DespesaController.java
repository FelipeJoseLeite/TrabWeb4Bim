package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.service.CategoriaService;
import com.example.gestaofinanceira.service.DespesaService;
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
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaDespesa(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("cadastroDespesa");

        modelAndView.addObject("categoriaList", categoriaService.listAll());

        if (!model.containsAttribute("despesa")) {
            model.addAttribute("despesa", new Despesa());
            model.addAttribute("msg", new ArrayList<String>());
        }

        modelAndView.addObject("despesaList", despesaService.listAll());

        return modelAndView;
    }

    @PostMapping
    public String salvarDespesa(@Valid Despesa despesa,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("despesa", despesa);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);
        }else {
            despesaService.insert(despesa);
        }

        return "redirect:/despesa";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaDespesa(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("cadastroDespesa");

        modelAndView.addObject("categoriaList", categoriaService.listAll());

        if (!model.containsAttribute("despesa")) {
            modelAndView.addObject("despesa", new Despesa());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

}
