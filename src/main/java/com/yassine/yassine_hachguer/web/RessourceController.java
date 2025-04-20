package com.yassine.yassine_hachguer.web;

import com.yassine.yassine_hachguer.entities.Ressource;
import com.yassine.yassine_hachguer.repository.RessourceRepository;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@AllArgsConstructor
@Controller
public class RessourceController {

    private RessourceRepository ressourceRepository;
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<Ressource> ressourcesPage = ressourceRepository.findByTitreContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listeRessources", ressourcesPage.getContent());
        model.addAttribute("pages",new int[ressourcesPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "ressources";


    }
    @GetMapping("/admin/delete")
    public String delete(Long code, String keyword, int page){
        ressourceRepository.deleteById(code);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/formRessource")
    public String formRessource(Model model){
        model.addAttribute("ressource", new Ressource());
        return "formRessource";
    }

    @PostMapping(path = "/admin/save")
    public String save(@Valid Ressource ressource, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formRessource";
        ressourceRepository.save(ressource);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editRessource")
    public String editRessource(Model model, Long code, String keyword, int page){

        Ressource ressource = ressourceRepository.findById(code).orElse(null);
        if(ressource==null) throw new RuntimeException("Ressource Introuvable");
        model.addAttribute(ressource);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editRessource";

    };
    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }






}
