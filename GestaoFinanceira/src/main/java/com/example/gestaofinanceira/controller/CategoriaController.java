package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model){
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "categorias/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @PostMapping("/editar")
    public String salvarCategoriaEditada(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.salvarCategoria(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/nova")
    public String salvarNovaCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.salvarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/nova")
    public String novaCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

}
