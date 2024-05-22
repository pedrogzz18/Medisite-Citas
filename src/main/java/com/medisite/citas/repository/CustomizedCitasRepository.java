package com.medisite.citas.repository;

import com.medisite.citas.model.TimeRangeRequest;
import com.medisite.citas.repository.entity.CitaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedCitasRepository {
    public List<CitaEntity> getCitasByPacienteIdInTimeRange(long id_paciente, TimeRangeRequest request);

    public boolean checkCita(CitaEntity request);
}
