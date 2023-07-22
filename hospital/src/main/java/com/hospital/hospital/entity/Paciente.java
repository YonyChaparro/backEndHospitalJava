package com.hospital.hospital.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Paciente {

    @Min(value = 1)
    @Id 
    private Long numeroIdentificacion;

    @NotNull
    @Size(max = 50)
    private String nombre;

    @NotNull
    @Size(max = 50)
    private String apellido;

    @NotNull
    @Min(value = 1)
    @Max(value = 110)
    private Integer edad;

    @NotNull
    @Min(value = 10000)
    private Long numeroTelefono;

    public Paciente() {

    }

    public Paciente(@NotNull @Min(1) Long numeroIdentificacion, @NotNull @Size(max = 50) String nombre,
            @NotNull @Size(max = 50) String apellido, @NotNull @Min(1) @Max(110) Integer edad,
            @NotNull @Min(10000) Long numeroTelefono) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroTelefono = numeroTelefono;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(Long numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
}
