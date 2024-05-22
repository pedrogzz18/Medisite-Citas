package com.medisite.citas.repository.impl;

import com.medisite.citas.model.TimeRangeRequest;
import com.medisite.citas.repository.CustomizedCitasRepository;
import com.medisite.citas.repository.entity.CitaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomizedCitasRepositoryImpl implements CustomizedCitasRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CitaEntity> getCitasByPacienteIdInTimeRange(long id_paciente, TimeRangeRequest request) {
        return em.createQuery("SELECT c FROM CitaEntity c WHERE c.idPaciente = :id_paciente AND " +
                        "(c.horaInicio BETWEEN :horaInicio AND :horaFin " +
                        "OR c.horaFin BETWEEN :horaInicio AND :horaFin " +
                        "OR c.horaInicio < :horaInicio AND c.horaFin > :horaFin)" +
                        "ORDER BY c.horaInicio ASC", CitaEntity.class).
                setParameter("id_paciente", id_paciente).
                setParameter("horaInicio", request.getTiempoInicio()).
                setParameter("horaFin", request.getTiempoFin()).
                getResultList();
    }

    @Override
    public boolean checkCita(CitaEntity request){
        long id_paciente = request.getIdPaciente();
        long id_medico = request.getIdMedico();
        LocalDateTime tiempo_inicio = request.getHoraInicio();
        LocalDateTime tiempo_fin = request.getHoraFin();

        List<CitaEntity> overlapped_citas = em.createQuery(
                        "SELECT c FROM CitaEntity c WHERE (c.idPaciente = :id_paciente OR c.idMedico = :id_medico) " +
                                "AND (c.horaInicio BETWEEN :horaInicio AND :horaFin " +
                                "OR c.horaFin BETWEEN :horaInicio AND :horaFin " +
                                "OR (c.horaInicio < :horaInicio AND c.horaFin > :horaFin))",
                        CitaEntity.class)
                .setParameter("id_paciente", id_paciente)
                .setParameter("id_medico", id_medico)
                .setParameter("horaInicio", tiempo_inicio)
                .setParameter("horaFin", tiempo_fin)
                .getResultList();

        return overlapped_citas.isEmpty();
    }
}
