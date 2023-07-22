package com.hospital.hospital.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Especialidad;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{
    
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor findByIdDoctor(Long idDoctor) {
        return this.doctorRepository.findById(idDoctor).get();
    }

    @Override
    public void crearDoctor(Doctor doctor) {
        this.doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        this.doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long idDoctor) {
        this.doctorRepository.deleteById(idDoctor);
    }

    @Override
    public List<Doctor> findByEspecialidad(Especialidad especialidad) {
        return doctorRepository.findByEspecialidad(especialidad);
    }
}
