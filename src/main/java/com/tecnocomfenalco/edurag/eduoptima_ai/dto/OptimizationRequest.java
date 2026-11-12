package com.tecnocomfenalco.edurag.eduoptima_ai.dto;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Student;
import com.tecnocomfenalco.edurag.eduoptima_ai.model.Tutor;

import java.util.List;

public class OptimizationRequest {

    private List<Student> students; // Entidades JPA directamente (serialización Jackson)
    private List<Tutor> tutors;

    public OptimizationRequest() {}

    public OptimizationRequest(List<Student> students, List<Tutor> tutors) {
        this.students = students;
        this.tutors = tutors;
    }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<Tutor> getTutors() { return tutors; }
    public void setTutors(List<Tutor> tutors) { this.tutors = tutors; }
}