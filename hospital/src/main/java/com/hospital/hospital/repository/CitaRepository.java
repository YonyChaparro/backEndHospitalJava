package com.hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.entity.Cita;

public interface CitaRepository extends JpaRepository <Cita, Long>{
    
}