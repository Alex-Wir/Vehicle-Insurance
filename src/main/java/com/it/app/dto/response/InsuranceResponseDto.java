package com.it.app.dto.response;

public class InsuranceResponseDto {

    private Long id;
    private Float payment;
    private Float amount;
    private CarResponseDto car;
    private ShiftIdResponseDto shift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCar(CarResponseDto car) {
        this.car = car;
    }

    public CarResponseDto getCar() {
        return car;
    }

    public ShiftIdResponseDto getShift() {
        return shift;
    }

    public void setShift(ShiftIdResponseDto shiftId) {
        this.shift = shiftId;
    }
}
