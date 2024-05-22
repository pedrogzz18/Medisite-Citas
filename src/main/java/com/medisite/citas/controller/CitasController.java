package com.medisite.citas.controller;


import com.medisite.citas.repository.entity.CitaEntity;

public interface CitasController {
    public CitaEntity createCita(CitaEntity citaEntity);

    public CitaEntity getCitaById(long id_cita);

    public void deleteCita(long id_cita);

    public CitaEntity updateCita(CitaEntity citaEntity);
}
