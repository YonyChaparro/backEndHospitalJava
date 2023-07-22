package com.hospital.hospital.service;
import java.util.List;

import com.hospital.hospital.entity.Especialidad;

public interface EspecialidadService {

    public List <Especialidad> findAll();
    public Especialidad findByIdEspecialidad(Long idEspecialidad);
    public void createEspecialidad (Especialidad especialidad);
    public void updateEspecialidad(Especialidad especialidad);
    public void deleteEspecialidad (Long idEspecialidad);
    
}
