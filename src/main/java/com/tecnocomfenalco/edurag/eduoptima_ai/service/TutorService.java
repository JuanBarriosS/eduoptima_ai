package com.tecnocomfenalco.edurag.eduoptima_ai.service;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Tutor;
import com.tecnocomfenalco.edurag.eduoptima_ai.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Tutor findById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }
}