package com.hospital.hospital.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.entity.Paciente;
import com.hospital.hospital.repository.PacienteRepository;
import com.hospital.hospital.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    PacienteRepository pacienteRepository;
    
    @Override
    public List<Paciente> findAll() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente findByIdPaciente(Long idPaciente) {
        return this.pacienteRepository.findById(idPaciente).get();
    }

    @Override
    public void createPaciente(Paciente paciente) {
        this.pacienteRepository.save(paciente);
    }

    @Override
    public void updatePaciente(Paciente paciente) {
       this.pacienteRepository.save(paciente);
    }

    @Override
    public void deletePaciente(Long idPaciente) {
        this.pacienteRepository.deleteById(idPaciente);
    }

    

}
