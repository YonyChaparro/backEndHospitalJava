package com.hospital.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Especialidad;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.EspecialidadService;

@RestController
@RequestMapping("/doctores")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        List<Doctor> doctores = doctorService.findAll();
        return new ResponseEntity<>(doctores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Long id = doctor.getEspecialidad().getId(); // Obtenemos el ID de la especialidad desde el objeto Doctor
                                                    // recibido en la solicitud
        Especialidad especialidad = especialidadService.findByIdEspecialidad(id); // Buscamos la especialidad por su ID
                                                                                  // en la base de datos
        if (especialidad != null) {
            doctor.setEspecialidad(especialidad); // Asignamos la especialidad encontrada al doctor
            doctorService.crearDoctor(doctor); // Guardamos el doctor en la base de datos
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si la especialidad no se encuentra
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findByIdDoctor(@PathVariable("id") Long idDoctor) {
        Doctor doctor = doctorService.findByIdDoctor(idDoctor);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long idDoctor, @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorService.findByIdDoctor(idDoctor);

        if (doctor != null) {
            Long especialidadId = doctorDetails.getEspecialidad().getId();
            Especialidad especialidad = especialidadService.findByIdEspecialidad(especialidadId);

            if (especialidad != null) {
                // Actualizamos los atributos del doctor con los datos proporcionados en el
                // objeto doctorDetails
                doctor.setNombre(doctorDetails.getNombre());
                doctor.setApellido(doctorDetails.getApellido());
                doctor.setConsultorio(doctorDetails.getConsultorio());
                doctor.setEmail(doctorDetails.getEmail());

                // Asignamos la nueva especialidad al doctor
                doctor.setEspecialidad(especialidad);

                // Guardamos los cambios en la base de datos
                doctorService.updateDoctor(doctor);

                return new ResponseEntity<>(doctor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si la especialidad no se encuentra
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si el doctor no se encuentra
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") Long idDoctor) {
        Doctor doctor = doctorService.findByIdDoctor(idDoctor);
        if (doctor != null) {
            doctorService.deleteDoctor(idDoctor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
