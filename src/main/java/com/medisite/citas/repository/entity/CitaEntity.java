package com.medisite.citas.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cita")
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    private long idCita;

    @Column(name="id_paciente")
    private long idPaciente;

    @Column(name="id_medico")
    private long idMedico;

    @Column(name="hora_inicio")
    private LocalDateTime horaInicio;

    @Column(name="hora_fin")
    private LocalDateTime horaFin;

}
