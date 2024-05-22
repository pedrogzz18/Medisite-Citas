package com.medisite.citas.repository;

import com.medisite.citas.repository.entity.CitaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitasRepository  extends CrudRepository<CitaEntity, Long>, CustomizedCitasRepository {
}
