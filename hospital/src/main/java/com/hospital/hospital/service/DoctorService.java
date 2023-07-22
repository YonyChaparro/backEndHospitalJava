package com.hospital.hospital.service;
import java.util.List;

import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Especialidad;

public interface DoctorService {
    public List <Doctor> findAll();
    public Doctor findByIdDoctor(Long idDoctor);
    public void crearDoctor (Doctor doctor);
    public void updateDoctor (Doctor doctor);
    public void deleteDoctor (Long idDoctor);
    List<Doctor> findByEspecialidad(Especialidad especialidad);
}
