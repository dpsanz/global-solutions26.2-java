package br.com.fiap.daniel561948.controller;

import br.com.fiap.daniel561948.dto.TrechoDTO;
import br.com.fiap.daniel561948.service.BairroService;
import br.com.fiap.daniel561948.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/trechos")
public class TrechoController {
    private static final List<String> TIPOS_CALCADA =
            List.of("Regular", "Irregular", "Escadaria", "Sem iluminação");

    private final TrechoService trechoService;
    private final BairroService bairroService;

    public TrechoController(TrechoService trechoService, BairroService bairroService) {
        this.trechoService = trechoService;
        this.bairroService = bairroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        return formulario(model);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        try {
            model.addAttribute("trecho", trechoService.buscarPorId(id));
            return formulario(model);
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/trechos";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO trecho, BindingResult erros,
                         Model model, RedirectAttributes redirect) {
        if (!erros.hasFieldErrors("tipoCalcada")
                && !TIPOS_CALCADA.contains(trecho.getTipoCalcada())) {
            erros.rejectValue("tipoCalcada", "tipoCalcada.invalido", "Selecione um tipo de calçada válido.");
        }
        if (erros.hasErrors()) {
            return formulario(model);
        }

        try {
            trechoService.salvar(trecho);
        } catch (IllegalArgumentException e) {
            erros.reject("trecho.salvar", e.getMessage());
            return formulario(model);
        }

        redirect.addFlashAttribute("sucesso", "Trecho salvo.");
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            trechoService.excluir(id);
            redirect.addFlashAttribute("sucesso", "Trecho excluído.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/trechos";
    }

    private String formulario(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        model.addAttribute("tiposCalcada", TIPOS_CALCADA);
        return "trechos/form";
    }
}