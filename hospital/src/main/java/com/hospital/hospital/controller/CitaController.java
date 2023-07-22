package com.hospital.hospital.controller;


import java.util.List;
import java.util.Random;

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

import com.hospital.hospital.entity.Cita;
import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Especialidad;
import com.hospital.hospital.entity.Paciente;
import com.hospital.hospital.service.CitaService;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.EspecialidadService;
import com.hospital.hospital.service.PacienteService;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Cita>> findAll() {
        List<Cita> citas = citaService.findAll();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Paciente paciente = cita.getPaciente(); // Obtenemos el paciente directamente del JSON
        if (paciente != null) {
            Long idEspecialidad = cita.getEspecialidad().getId();
            Especialidad especialidad = especialidadService.findByIdEspecialidad(idEspecialidad);
            if (especialidad != null) {
                List<Doctor> doctoresByEspecialidad = doctorService.findByEspecialidad(especialidad);
                if (!doctoresByEspecialidad.isEmpty()) {
                    int indiceAleatorio = new Random().nextInt(doctoresByEspecialidad.size());
                    Doctor doctorSeleccionado = doctoresByEspecialidad.get(indiceAleatorio);
                    cita.setDoctor(doctorSeleccionado);

                    cita.setPaciente(paciente);
                    cita.setEspecialidad(especialidad);

                    citaService.createCita(cita);
                    return new ResponseEntity<>(cita, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si no hay doctores con esa
                                                                       // especialidad
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si la especialidad no se encuentra
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si el paciente no se encuentra en el
        }
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Cita> findByIdCita(@PathVariable("id")Long idCita){
        Cita cita = citaService.findByIdCita(idCita);
        if(cita != null){
            return new ResponseEntity<>(cita, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idCita}")
public ResponseEntity<Cita> updateCita(@PathVariable Long idCita, @RequestBody Cita citaActualizada) {
    // Verificamos si la cita existe en la base de datos
    Cita citaExistente = citaService.findByIdCita(idCita);
    if (citaExistente != null) {
        // Obtenemos el paciente y especialidad actualizados desde el cuerpo de la solicitud
        Paciente pacienteActualizado = citaActualizada.getPaciente();
        Especialidad especialidadActualizada = citaActualizada.getEspecialidad();

        // Verificamos si el paciente y especialidad actualizados existen en la base de datos
        Paciente paciente = pacienteService.findByIdPaciente(pacienteActualizado.getNumeroIdentificacion());
        Especialidad especialidad = especialidadService.findByIdEspecialidad(especialidadActualizada.getId());

        if (paciente != null && especialidad != null) {
            // Actualizamos los campos de la cita existente con los nuevos datos
            citaExistente.setPaciente(paciente);
            citaExistente.setEspecialidad(especialidad);

            // Guardamos los cambios en la base de datos
            citaService.updateCita(citaExistente);
            return new ResponseEntity<>(citaExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si el paciente o especialidad no existen
        }
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Manejo de error si la cita no existe
    }

    
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable("id") Long idCita){
        Cita cita = citaService.findByIdCita(idCita);
        if(cita != null){
            citaService.deleteCita(idCita);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
