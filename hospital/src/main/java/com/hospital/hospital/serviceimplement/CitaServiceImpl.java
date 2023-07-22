package com.hospital.hospital.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.entity.Cita;
import com.hospital.hospital.repository.CitaRepository;
import com.hospital.hospital.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService{

    @Autowired
    CitaRepository citaRepository;

    @Override
    public List<Cita> findAll() {
        return this.citaRepository.findAll();
    }

    @Override
    public Cita findByIdCita(Long idCita) {
        return this.citaRepository.findById(idCita).get();
    }

    @Override
    public void createCita(Cita cita) {
        this.citaRepository.save(cita);
    }

    @Override
    public void updateCita(Cita cita) {
        this.citaRepository.save(cita);
    }

    @Override
    public void deleteCita(Long idCita) {
        this.citaRepository.deleteById(idCita);
    }

    
    
}
