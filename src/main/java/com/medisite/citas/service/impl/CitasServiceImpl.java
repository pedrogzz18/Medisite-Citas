package com.medisite.citas.service.impl;

import com.medisite.citas.repository.CitasRepository;
import com.medisite.citas.repository.entity.CitaEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.medisite.citas.service.CitasService;

@Service
public class CitasServiceImpl implements CitasService {
    @Autowired
    private CitasRepository citasRepository;
    @Override
    public CitaEntity createCita(CitaEntity citaEntity){
        if(citasRepository.checkCita(citaEntity)) return citasRepository.save(citaEntity);
        else return null;
    }

    @Override
    public CitaEntity getCitaById(long id_cita){
        return citasRepository.findById(id_cita).get();
    }

    @Override
    public void deleteCita(long id_cita){
        citasRepository.deleteById(id_cita);
    }

    @Override
    public CitaEntity updateCita(CitaEntity citaEntity){
        return citasRepository.save(citaEntity);
    }
}
