package com.hospital.hospital.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.entity.Especialidad;
import com.hospital.hospital.repository.EspecialidadRepository;
import com.hospital.hospital.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Override
    public List<Especialidad> findAll() {
        
        return this.especialidadRepository.findAll();
    }

    @Override
    public Especialidad findByIdEspecialidad(Long idEspecialidad) {
        return this.especialidadRepository.findById(idEspecialidad).get();
    }

    @Override
    public void createEspecialidad(Especialidad especialidad) {
        this.especialidadRepository.save(especialidad);
    }

    @Override
    public void updateEspecialidad(Especialidad especialidad) {
        this.especialidadRepository.save(especialidad);
    }

    @Override
    public void deleteEspecialidad(Long idEspecialidad) {
        this.especialidadRepository.deleteById(idEspecialidad);
    }
    

}
