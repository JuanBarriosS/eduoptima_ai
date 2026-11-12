package com.tecnocomfenalco.edurag.eduoptima_ai.controller;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Tutor;
import com.tecnocomfenalco.edurag.eduoptima_ai.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    // GET /tutors → Listar todos los tutores
    @GetMapping
    public String listTutors(Model model) {
        model.addAttribute("tutors", tutorService.findAll());
        return "tutors/list";
    }

    // GET /tutors/new → Mostrar formulario vacío
    @GetMapping("/new")
    public String newTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutors/form";
    }

    // POST /tutors/save → Guardar o actualizar tutor
    @PostMapping("/save")
    public String saveTutor(@Valid @ModelAttribute("tutor") Tutor tutor,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "tutors/form"; // Re-muestra el formulario con errores
        }
        tutorService.save(tutor);
        return "redirect:/tutors";
    }

    // GET /tutors/edit/{id} → Cargar datos en formulario
    @GetMapping("/edit/{id}")
    public String editTutor(@PathVariable Long id, Model model) {
        model.addAttribute("tutor", tutorService.findById(id));
        return "tutors/form";
    }

    // GET /tutors/delete/{id} → Eliminar tutor
    @GetMapping("/delete/{id}")
    public String deleteTutor(@PathVariable Long id) {
        tutorService.deleteById(id);
        return "redirect:/tutors";
    }
}