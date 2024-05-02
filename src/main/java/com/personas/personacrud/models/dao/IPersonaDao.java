package com.personas.personacrud.models.dao;

import java.util.List;

import com.personas.personacrud.models.Persona;

public interface IPersonaDao {

    //Metodo que retorna una Lista de personas
    public List<Persona>findAll();

    //Metodo vacio que permitira crear un objeto de la clase Persona
    public void save(Persona persona);

    //Metodo para buscar una persona dentro de la base de datos
    public Persona findOne(Long id);

    public void delete(Long id);
}
