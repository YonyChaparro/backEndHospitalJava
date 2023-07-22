package com.hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital.entity.Especialidad;

@Repository
public interface EspecialidadRepository  extends JpaRepository <Especialidad, Long>{
    
}
