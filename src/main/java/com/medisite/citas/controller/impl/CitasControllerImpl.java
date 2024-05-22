package com.medisite.citas.controller.impl;

import com.medisite.citas.controller.CitasController;
import com.medisite.citas.repository.entity.CitaEntity;
import com.medisite.citas.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitasControllerImpl implements CitasController {

    @Autowired
    private CitasService citasService;
    @PostMapping("/create-cita")
    public CitaEntity createCita(@RequestBody CitaEntity citaEntity){
        return citasService.createCita(citaEntity);
    }

    @GetMapping("/get-cita/{id}")
    public CitaEntity getCitaById(@PathVariable long id_cita){
        return citasService.getCitaById(id_cita);
    }

    @DeleteMapping("/delete-cita/{id}")
    public void deleteCita(@PathVariable long id_cita){
        citasService.deleteCita(id_cita);
    }

    @PutMapping("/update-cita/{id}")
    public CitaEntity updateCita(@RequestBody CitaEntity citaEntity){
        return citasService.updateCita(citaEntity);
    }
}
