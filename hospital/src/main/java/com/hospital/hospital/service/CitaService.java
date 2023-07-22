package com.hospital.hospital.service;

import java.util.List;

import com.hospital.hospital.entity.Cita;

public interface CitaService {
    public List <Cita> findAll();
    public Cita findByIdCita(Long idCita);
    public void createCita(Cita cita);
    public void updateCita (Cita cita);
    public void deleteCita (Long idCita);
    
}
