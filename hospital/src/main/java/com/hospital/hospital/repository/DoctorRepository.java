package com.hospital.hospital.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Especialidad;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByEspecialidad(Especialidad especialidad);
}