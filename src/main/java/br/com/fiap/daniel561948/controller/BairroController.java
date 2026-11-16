package br.com.fiap.daniel561948.controller;

import br.com.fiap.daniel561948.dto.BairroDTO;
import br.com.fiap.daniel561948.dto.PainelItemDTO;
import br.com.fiap.daniel561948.service.BairroService;
import br.com.fiap.daniel561948.service.PainelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bairros")
public class BairroController {
    private final BairroService bairroService;
    private final PainelService painelService;

    public BairroController(BairroService bairroService, PainelService painelService) {
        this.bairroService = bairroService;
        this.painelService = painelService;
    }

    @ModelAttribute("zonas")
    public List<String> zonas() {
        return List.of("Centro", "Norte", "Sul", "Leste", "Oeste");
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bairro", bairroService.buscarPorId(id));
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO bairro,
                         BindingResult erros, RedirectAttributes redirect) {
        if (!erros.hasFieldErrors("zona") && !zonas().contains(bairro.getZona())) {
            erros.rejectValue("zona", "zona.invalida", "Selecione uma zona válida.");
        }

        if (erros.hasErrors()) {
            return "bairros/form";
        }

        try {
            bairroService.salvar(bairro);
        } catch (IllegalArgumentException e) {
            erros.reject("bairro.salvar", e.getMessage());
            return "bairros/form";
        }

        redirect.addFlashAttribute("sucesso", "Bairro salvo.");
        return "redirect:/bairros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            bairroService.excluir(id);
            redirect.addFlashAttribute("sucesso", "Bairro excluído.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/bairros";
    }

    @GetMapping("/{id}/painel")
    public String painel(@PathVariable("id") Long id, Model model) {
        BairroDTO bairro = bairroService.buscarPorId(id);
        List<PainelItemDTO> itens = painelService.montarPainel(id);

        model.addAttribute("bairroNome", bairro.getNome());
        model.addAttribute("itens", itens);
        model.addAttribute("qtdNormal", contar(itens, "NORMAL"));
        model.addAttribute("qtdAtencao", contar(itens, "ATENCAO"));
        model.addAttribute("qtdAlerta", contar(itens, "ALERTA"));
        return "bairros/painel";
    }

    private long contar(List<PainelItemDTO> itens, String status) {
        return itens.stream()
                .filter(item -> status.equals(item.ultimoStatus()))
                .count();
    }
}