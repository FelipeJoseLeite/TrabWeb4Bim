package com.example.controlefinanceiro.controller;

import com.example.controlefinanceiro.domain.Receita;
import com.example.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path ="/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaReceita(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("receita/inserir");
        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerDisciplina(@PathVariable("id") Long id){
        receitaService.delete(id);
        return "redirect:/receita";
    }

    @PostMapping
    public String salvarDisciplina(@Valid Receita receita,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("receita", receita);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/disciplina/criar";
        }

        receitaService.insert(receita);

        return "redirect:/receita";
    }
}
