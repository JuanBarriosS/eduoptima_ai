package com.tecnocomfenalco.edurag.eduoptima_ai.repository;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    // Métodos CRUD automáticos heredados de JpaRepository
    // List<Tutor> findBySpecialty(String specialty);

    // SELECT * FROM tabla - Retorna todos los registros
    List<Tutor> findAll();

    // SELECT * FROM tabla WHERE id = ? - Busca por clave primaria
    Optional<Tutor> findById(Long id);

    // INSERT / UPDATE - Guarda o actualiza según si el ID es nulo o no
    <S extends Tutor> S save(S entity);

    // DELETE FROM tabla WHERE id = ? - Elimina por clave primaria
    void deleteById(Long id);

    // SELECT COUNT(*) WHERE id = ? - Verifica existencia sin cargar el objeto
    boolean existsById(Long id);
}