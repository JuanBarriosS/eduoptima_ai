package com.tecnocomfenalco.edurag.eduoptima_ai.controller;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Student;
import com.tecnocomfenalco.edurag.eduoptima_ai.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /students → Listar todos los estudiantes
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    // GET /students/new → Mostrar formulario vacío
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "students/form"; // Re-muestra el formulario con errores
        }
        studentService.save(student);
        return "redirect:/students"; // Patrón POST-Redirect-GET
    }

    // GET /students/edit/{id} → Cargar datos en formulario
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "students/form";
    }

    // GET /students/delete/{id} → Eliminar estudiante
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}