package com.it.app.service;

import com.it.app.model.Shift;

import java.util.List;

/**
 * Service interface for Shift entity
 */
public interface ShiftService {

    List<Shift> findAll();

    Shift findById(Long id);

    Shift save(Shift shift);

    Shift update(Shift shift);

    void delete(Shift shift);

    void deleteById(Long id);

    List<Shift> findAllByUserId(Long id);
}
