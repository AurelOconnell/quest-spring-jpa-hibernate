package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SchoolController {

    // TODO : get school repository by dependency injection

    private SchoolRepository repository;

    public SchoolController(SchoolRepository repositoryInjected) {
        this.repository = repositoryInjected;
    }

    @GetMapping("/schools")
    public @ResponseBody List<School> getAllSchools() {

        // TODO : find all schools

        return repository.findAll();
    }

    @GetMapping("/school")
    public String getSchool(Model model,
            @RequestParam(required = false) Long id) {

        // TODO : find one school by id

        School school = new School();
        if (id != null) {
            Optional<School> optionalSchool = repository.findById(id);
            if (optionalSchool.isPresent()) {
                school = optionalSchool.get();
            }
        }
        model.addAttribute("school", school);
        return "school";
    }

    @PostMapping("/school")
    public @ResponseBody String addNewSchool(@ModelAttribute School school) {

        // TODO : create or update a school

        repository.save(school);

        return "saved";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {

        // TODO : delete a school

        repository.deleteById(id);

        return "redirect:/schools";
    }
}
