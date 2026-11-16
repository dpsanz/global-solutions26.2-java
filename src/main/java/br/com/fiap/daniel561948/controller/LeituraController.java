package br.com.fiap.daniel561948.controller;

import br.com.fiap.daniel561948.dto.LeituraDTO;
import br.com.fiap.daniel561948.service.LeituraService;
import br.com.fiap.daniel561948.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/leituras")
public class LeituraController {
    private final LeituraService leituraService;
    private final TrechoService trechoService;

    public LeituraController(LeituraService leituraService, TrechoService trechoService) {
        this.leituraService = leituraService;
        this.trechoService = trechoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        return formulario(model);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        try {
            model.addAttribute("leitura", leituraService.buscarPorId(id));
            return formulario(model);
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/leituras";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO leitura, BindingResult erros,
                         Model model, RedirectAttributes redirect) {
        if (erros.hasErrors()) {
            return formulario(model);
        }

        try {
            leituraService.salvar(leitura);
        } catch (IllegalArgumentException e) {
            erros.reject("leitura.salvar", e.getMessage());
            return formulario(model);
        }

        redirect.addFlashAttribute("sucesso", "Leitura salva.");
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            leituraService.excluir(id);
            redirect.addFlashAttribute("sucesso", "Leitura excluída.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/leituras";
    }

    private String formulario(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }
}