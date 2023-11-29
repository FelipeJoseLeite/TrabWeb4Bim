package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;


    @GetMapping
    public ModelAndView listaReceita(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("cadastroReceita");

        if (model.containsAttribute("receita"))
            modelAndView.addObject("receita",
                    model.getAttribute("receita"));
        else {
            modelAndView.addObject("receita", receitaService.listAll());
        }

        return modelAndView;
    }

    @PostMapping
    public String salvarReceita(@Valid Receita receita,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("receita", receita);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/receita/criar";
        }

        receitaService.insert(receita);

        return "redirect:/receita";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaReceita(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("cadastroReceita");

        if (model.containsAttribute("receita")) {
            modelAndView.addObject("receita", model.getAttribute("receita"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("receita", new Receita());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

}
