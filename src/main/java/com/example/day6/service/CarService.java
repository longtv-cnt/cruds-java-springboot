package com.example.day6.service;

import com.example.day6.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();
    public Car getCarById(int id);

  public   List<Car> getCarsByBrand(String brand);
    public Car addCar(Car car);
    public Car updateCar(int id, Car car);
    public boolean deleteCar(int id);

    List<Car> searchCarV2(String query);
}
