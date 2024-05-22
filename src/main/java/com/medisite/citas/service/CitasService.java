package com.medisite.citas.service;

import com.medisite.citas.repository.entity.CitaEntity;
import org.springframework.stereotype.Service;

public interface CitasService {
    public CitaEntity createCita(CitaEntity citaEntity);

    public CitaEntity getCitaById(long id_cita);

    public void deleteCita(long id_cita);

    public CitaEntity updateCita(CitaEntity citaEntity);
}
