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

import com.hospital.hospital.entity.Especialidad;
import com.hospital.hospital.service.EspecialidadService;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins ="*")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Especialidad>> findAll(){
        List<Especialidad> especialidades = especialidadService.findAll();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Especialidad> createEspecialidad (@RequestBody Especialidad especialidad){
        especialidadService.createEspecialidad(especialidad);
        return new ResponseEntity<>(especialidad, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> findByIdEspecialidad(@PathVariable("id") Long idEspecialidad){
        Especialidad especialidad = especialidadService.findByIdEspecialidad(idEspecialidad);
        if(especialidad != null){
            return new ResponseEntity<>(especialidad, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable("id") Long idEspecialidad, @RequestBody Especialidad especialidadDetails){
        Especialidad especialidad = especialidadService.findByIdEspecialidad(idEspecialidad);
        if(especialidad != null){
            especialidad.setNombre(especialidadDetails.getNombre());
            especialidadService.updateEspecialidad(especialidad);
            return new ResponseEntity<>(especialidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable("id") Long idEspecialidad){
        Especialidad especialidad = especialidadService.findByIdEspecialidad(idEspecialidad);
        if(especialidad != null){
            especialidadService.deleteEspecialidad(idEspecialidad);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
