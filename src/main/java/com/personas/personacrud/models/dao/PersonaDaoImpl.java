package com.personas.personacrud.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.personas.personacrud.models.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository //Clase que implementa la interfaz
public class PersonaDaoImpl implements IPersonaDao {

    //Actua como interfaz entre nuestra aplicacion y la base de datos
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional //permite trasacciones o acciones que hagamos para comunicarse con la base de datos
    @Override
    public List<Persona> findAll() {
        //sobreescribe el metodo de la interfaz y retorna una lista desde la base de datos
        return em.createQuery("from Persona").getResultList();
    }

    @Transactional
    @Override
    public void save(Persona persona) {

        if(persona.getId() != null && persona.getId() >0)
        {
            //Permite actualzar los datos 
           em.merge(persona);
        }
        else{
       //Permite guardar el objeto persona que llega
       em.persist(persona);
        }
 
    }

    @Override
    public Persona findOne(Long id) {
        //busca una persona dentro de la base de datos
        return em.find(Persona.class,id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Persona persona = findOne(id);
        em.remove(persona);
    }
    

}
