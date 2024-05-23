package com.medisite.citas.controller;


import com.medisite.citas.model.TimeRangeRequest;
import com.medisite.citas.repository.entity.CitaEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/citas")
public interface CitasController {

    @PostMapping("/create-cita")
    public ResponseEntity<?> createCita(HttpServletRequest request, @RequestBody CitaEntity citaEntity);

    @GetMapping("/get-cita/{id_cita}")
    public ResponseEntity<?> getCitaById(HttpServletRequest request, @PathVariable long id_cita);

    @DeleteMapping("/delete-cita/{id_cita}")
    public ResponseEntity<?> deleteCita(HttpServletRequest request, @PathVariable long id_cita);

    @PutMapping("/update-cita/{id_cita}")
    public ResponseEntity<?> updateCita(HttpServletRequest request, @RequestBody CitaEntity citaEntity, @PathVariable long id_cita);

    @GetMapping("/get-citas/{id_paciente}")
    public ResponseEntity<?> getCitasByPacienteIdInTimeRange(HttpServletRequest request,
                                                             @PathVariable long id_paciente,
                                                             @RequestBody TimeRangeRequest timeRequest);
}
