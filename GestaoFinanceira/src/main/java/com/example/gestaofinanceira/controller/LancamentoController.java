package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.service.LancamentoService;
import com.example.gestaofinanceira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;


    @GetMapping
    public ModelAndView listaLancamentos(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("consultaLancamento");

        if (model.containsAttribute("lancamentoList"))
            modelAndView.addObject("lancamentoList", model.getAttribute("lancamentoList"));
        else {
            modelAndView.addObject("lancamentoList", lancamentoService.findAll());
        }

        return modelAndView;
    }

    @GetMapping(path = "/filtro")
    public String filtraLancamento(@RequestParam("dataInicio") String dataInicio,
                                    @RequestParam("dataFim") String dataFim,
                                   @RequestParam("tipo") String tipoLancamento,
                                   RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("lancamentoList", lancamentoService.findFiltros(dataInicio, dataFim, tipoLancamento));

        return "redirect:/lancamento";
    }

}
