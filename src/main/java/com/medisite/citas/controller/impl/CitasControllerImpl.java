package com.medisite.citas.controller.impl;

import com.medisite.citas.controller.CitasController;
import com.medisite.citas.model.TimeRangeRequest;
import com.medisite.citas.repository.entity.CitaEntity;
import com.medisite.citas.service.CitasService;
import com.medisite.citas.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitasControllerImpl implements CitasController {

    @Autowired
    private CitasService citasService;

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<?> createCita(HttpServletRequest request, @RequestBody CitaEntity citaEntity){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")){
            return ResponseEntity.status(403).body("not authorized");
        }
        String token = bearerToken.substring(7);
        if((jwtService.isPaciente(token) && jwtService.validateIdInToken(token, citaEntity.getIdPaciente()))
            || jwtService.isAdmin(token)){
            CitaEntity cita = citasService.createCita(citaEntity);
            if(cita == null) return ResponseEntity.status(500).body("appointment not avilable");
            return ResponseEntity.ok(cita);
        }
        return ResponseEntity.status(403).body("not authorized");
    }

    @Override
    public ResponseEntity<?> getCitaById(HttpServletRequest request, @PathVariable long id_cita){
        CitaEntity citaEntity = citasService.getCitaById(id_cita);
        String bearerToken = request.getHeader("Authorization");
        if(!bearerToken.startsWith("Bearer ")){
            return ResponseEntity.status(403).body("not authorized");
        }
        String token = bearerToken.substring(7);
        if((jwtService.isPaciente(token) && jwtService.validateIdInToken(token, citaEntity.getIdPaciente()))
            || (jwtService.isMedico(token) && jwtService.validateIdInToken(token, citaEntity.getIdMedico()))
            || jwtService.isAdmin(token)){
            return ResponseEntity.ok(citaEntity);
        }
        return ResponseEntity.status(403).body("not authorized");
    }

    @Override
    public ResponseEntity<?> deleteCita(HttpServletRequest request, @PathVariable long id_cita){
        CitaEntity citaEntity = citasService.getCitaById(id_cita);
        String bearerToken = request.getHeader("Authorization");
        if(!bearerToken.startsWith("Bearer ")){
            return ResponseEntity.status(403).body("not authorized");
        }
        String token = bearerToken.substring(7);
        if((jwtService.isPaciente(token) && jwtService.validateIdInToken(token, citaEntity.getIdPaciente()))
                || jwtService.isAdmin(token)){
            citasService.deleteCita(id_cita);
            return ResponseEntity.ok().body("Cita eliminada");
        }
        return ResponseEntity.status(403).body("not authorized");
    }

    @Override
    public ResponseEntity<?> updateCita(HttpServletRequest request, @RequestBody CitaEntity citaEntity, @PathVariable long id_cita){
        CitaEntity cita = citasService.getCitaById(id_cita);
        String bearerToken = request.getHeader("Authorization");
        if(!bearerToken.startsWith("Bearer ")){
            return ResponseEntity.status(403).body("not authorized");
        }
        String token = bearerToken.substring(7);
        if((jwtService.isPaciente(token) && jwtService.validateIdInToken(token, cita.getIdPaciente()))
                || jwtService.isAdmin(token)){
            citaEntity.setIdCita(id_cita);
            CitaEntity updatedCita = citasService.updateCita(citaEntity);
            if(updatedCita == null) return ResponseEntity.ok().body(citasService.updateCita(citaEntity));
            else return ResponseEntity.internalServerError().body("Horario no disponible");
        }
        return ResponseEntity.status(403).body("not authorized");
    }

    @Override
    public ResponseEntity<?> getCitasByPacienteIdInTimeRange(HttpServletRequest request,
                                                             @PathVariable long id_paciente,
                                                             @RequestBody TimeRangeRequest timeRequest){
        String bearerToken = request.getHeader("Authorization");
        if(!bearerToken.startsWith("Bearer ")){
            return ResponseEntity.status(403).body("not authorized");
        }
        String token = bearerToken.substring(7);
        if((jwtService.isPaciente(token) && jwtService.validateIdInToken(token, id_paciente))
                || jwtService.isAdmin(token)){
            return ResponseEntity.ok().body(citasService.getCitasByPacienteIdInTimeRange(id_paciente, timeRequest));
        }
        return ResponseEntity.status(403).body("not authorized");
    }
}
