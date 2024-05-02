package com.personas.personacrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.personas.personacrud.models.Persona;
import com.personas.personacrud.models.dao.IPersonaDao;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller//Permite que Spring lo reconozca como controlador
public class PersonaController {

    @Autowired
    IPersonaDao personaDao;

    @GetMapping("/listar")//Mapea este metodo hacia la vista listar
    public String listar(Model model) {
        model.addAttribute("titulo","Listado de Personas");//Crea Un titulo que ahora esta disponible para la vista
        model.addAttribute("personas", personaDao.findAll()); //Se comunica con la interfaz y relaliza el metodo find all
        return "listar";//retorna la vista
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Persona persona = new Persona();
        model.addAttribute("titulo", "Formulario de Persona");
        model.addAttribute("persona", persona);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Persona persona, BindingResult result,Model model) {
        if(result.hasErrors())
        {
            System.out.println("Entro aqui");
            model.addAttribute("titulo", "Formulario de Persona");
            return "form";
        }
        else{
            System.out.println("me vale riat");
            personaDao.save(persona); // Guarda a la persona que le llega desde el metodo de arriba en labase de datos
            return "redirect:/listar";
        }
     
    }

    @GetMapping("/editar/{id}") //metodo que obtiene el id de la persona para poder editarlo
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        Persona persona = null;
        if(id>0)
        {
            persona = personaDao.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.addAttribute("titulo","Edicion de Persona");
        model.addAttribute("persona",persona);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        if(id>0)
        {
            personaDao.delete(id);
        }
        return "redirect:/listar";
    }
    
    
    
    
    
    
}
