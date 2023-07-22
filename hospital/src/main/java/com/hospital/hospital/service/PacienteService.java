package com.hospital.hospital.service;

import java.util.List;
import com.hospital.hospital.entity.Paciente;


public interface PacienteService {
    
    public List <Paciente> findAll();
    public Paciente findByIdPaciente(Long idPaciente);
    public void createPaciente (Paciente paciente);
    public void updatePaciente (Paciente paciente);
    public void deletePaciente (Long idPaciente);
}
