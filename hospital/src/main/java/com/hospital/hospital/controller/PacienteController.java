package com.hospital.hospital.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospital.entity.Paciente;
import com.hospital.hospital.service.PacienteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    @GetMapping 
    public ResponseEntity<List<Paciente>> findAll(){
        List<Paciente> pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
       pacienteService.createPaciente(paciente);
       return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable("id") Long idPaciente) {
        Paciente paciente = pacienteService.findByIdPaciente(idPaciente);
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable("id") Long idPaciente, @RequestBody Paciente pacienteDetails) {
        Paciente paciente = pacienteService.findByIdPaciente(idPaciente);
        if (paciente != null) {
            paciente.setNombre(pacienteDetails.getNombre());
            paciente.setApellido(pacienteDetails.getApellido());
            paciente.setEdad(pacienteDetails.getEdad());
            paciente.setNumeroTelefono(pacienteDetails.getNumeroTelefono());

            pacienteService.updatePaciente(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable("id") Long idPaciente){
        Paciente paciente = pacienteService.findByIdPaciente(idPaciente);
        if(paciente != null ){
            pacienteService.deletePaciente(idPaciente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}
