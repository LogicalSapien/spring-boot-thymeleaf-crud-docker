package com.logicalsapien.controller;

import com.logicalsapien.entity.Galaxy;
import com.logicalsapien.repository.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GalaxyController {

    @Autowired
    GalaxyRepository galaxyRepository;

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/index";
    }

    @GetMapping("/creategalaxy")
    public String showSignUpForm(Galaxy galaxy, ModelMap model) {
        model.addAttribute("content", "add-galaxy");
        return "index";
    }

    @PostMapping("/addgalaxy")
    public String addGalaxy(@Valid Galaxy galaxy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-galaxy";
        }

        galaxyRepository.save(galaxy);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showGalaxies(ModelMap model) {
        model.addAttribute("galaxies", galaxyRepository.findAll());
        model.addAttribute("content", "list-galaxies");
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
        Galaxy galaxy = galaxyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid galaxy Id:" + id));

        model.addAttribute("galaxy", galaxy);
        model.addAttribute("content", "update-galaxy");
        return "index";
    }

    @PostMapping("/update/{id}")
    public String updateGalaxy(@PathVariable("id") long id, @Valid Galaxy galaxy,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            galaxy.setId(id);
            return "update-galaxy";
        }

        galaxyRepository.save(galaxy);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteGalaxy(@PathVariable("id") long id, Model model) {
        Galaxy galaxy = galaxyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid galaxy Id:" + id));
        galaxyRepository.delete(galaxy);
        return "redirect:/index";
    }
}