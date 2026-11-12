package com.tecnocomfenalco.edurag.eduoptima_ai.controller;

import com.tecnocomfenalco.edurag.eduoptima_ai.dto.OptimizationResponse;
import com.tecnocomfenalco.edurag.eduoptima_ai.service.OptimizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OptimizationController {

    private final OptimizationService optimizationService;

    public OptimizationController(OptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @GetMapping("/optimize")
    public String optimize(Model model) {
        OptimizationResponse response = optimizationService.optimize();
        model.addAttribute("response", response);
        model.addAttribute("assignments", response.getAssignments());
        return "optimization/result";
    }
}