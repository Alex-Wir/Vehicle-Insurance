package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Insurance;
import com.it.app.repository.InsuranceRepository;
import com.it.app.service.CarService;
import com.it.app.service.InsuranceService;
import com.it.app.service.ShiftService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private final LocalizedMessageSource localizedMessageSource;
    private final InsuranceRepository insuranceRepository;
    private final CarService carService;
    private final ShiftService shiftService;

    public InsuranceServiceImpl(LocalizedMessageSource localizedMessageSource, InsuranceRepository insuranceRepository, CarService carService, ShiftService shiftService) {
        this.localizedMessageSource = localizedMessageSource;
        this.insuranceRepository = insuranceRepository;
        this.carService = carService;
        this.shiftService = shiftService;
    }

    @Override
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public Insurance findById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.insurance.notExist", new Object[]{})));
    }

    @Override
    public Insurance save(Insurance insurance) {
        validate(insurance.getId() != null, localizedMessageSource.getMessage("error.insurance.notHaveId", new Object[]{}));
        return saveAndFlush(insurance);
    }

    @Override
    public Insurance update(Insurance insurance) {
        validate(insurance.getId() == null, localizedMessageSource.getMessage("error.insurance.haveId", new Object[]{}));
        return saveAndFlush(insurance);
    }

    @Override
    public void delete(Insurance insurance) {
        validate(insurance.getId() == null, localizedMessageSource.getMessage("error.insurance.haveId", new Object[]{}));
        insuranceRepository.delete(insurance);
    }

    @Override
    public void deleteById(Long id) {
        insuranceRepository.deleteById(id);
    }

    private Insurance saveAndFlush(Insurance insurance) {
        validate(insurance.getCar() == null || insurance.getCar().getId() == null, localizedMessageSource.getMessage("error.insurance.car.isNull", new Object[]{}));
        validate(insurance.getShift() == null || insurance.getShift().getId() == null, localizedMessageSource.getMessage("error.insurance.shift.isNull", new Object[]{}));
        insurance.setCar(carService.findById(insurance.getCar().getId()));
        insurance.setShift(shiftService.findById(insurance.getShift().getId()));
        return insuranceRepository.saveAndFlush(insurance);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
