package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model){
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categoria", categorias);
        return "cadastroCategoria";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return "redirect:/cadastroCategoria";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        return "cadastroCategoria";
    }

    @PostMapping("/editar")
    public String salvarCategoriaEditada(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.salvarCategoria(categoria);
        return "redirect:/cadastroCategoria";
    }

    @PostMapping("/nova")
    public String salvarNovaCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.salvarCategoria(categoria);
        return "redirect:/cadastroCategoria";
    }

    @GetMapping("/nova")
    public String novaCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "cadastroCategoria";
    }

}
