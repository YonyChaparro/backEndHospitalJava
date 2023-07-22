package com.hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.hospital.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente, Long> {
}
