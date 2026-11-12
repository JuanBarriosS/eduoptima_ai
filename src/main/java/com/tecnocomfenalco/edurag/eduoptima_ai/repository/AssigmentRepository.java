package com.tecnocomfenalco.edurag.eduoptima_ai.repository;

import com.tecnocomfenalco.edurag.eduoptima_ai.model.Assigment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssigmentRepository extends JpaRepository<Assigment, Long> {
    // Placeholder — activo para fase futura de persistencia de asignaciones

    // SELECT * FROM tabla - Retorna todos los registros
    List<Assigment> findAll();

    // SELECT * FROM tabla WHERE id = ? - Busca por clave primaria
    Optional<Assigment> findById(Long id);

    // INSERT / UPDATE - Guarda o actualiza según si el ID es nulo o no
    <S extends Assigment> S save(S entity);

    // DELETE FROM tabla WHERE id = ? - Elimina por clave primaria
    void deleteById(Long id);

    // SELECT COUNT(*) WHERE id = ? - Verifica existencia sin cargar el objeto
    boolean existsById(Long id);
}