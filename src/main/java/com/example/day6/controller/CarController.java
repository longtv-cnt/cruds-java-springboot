package com.example.day6.controller;

import com.example.day6.model.Car;
import com.example.day6.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    private CarService carService;
    private int id;
    private Car car;

    //    http://localhost:8080/car
//    http://localhost:8080/test
//    @RequestMapping(value = {"/car","test"})
    @GetMapping("")

    public ResponseEntity<?> getAllCars() {
        //Car car = new Car();
        List<Car> cars = carService.getAllCars();

//        car.setName("BMW");
//        car.setColor("Black");
//        car.setBrand("BMW");
//        car.setType("Sedan");
//        car.setYear(new Date(2021, 10, 10));
//        car.setPrice(1000000L);
//        car.setDescription("This is a BMW car");
//        //return car;
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> carById(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);

        //return car;
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }
    @PostMapping("")
//http://localhost:8080/car
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        carService.addCar(car);

        //return car;
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable("id") int id) {
        //Car car = new Car();
        carService.deleteCar(id);

        //return car;
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/search")
//    http://localhost:8080/car/search?brand=hang1
    public ResponseEntity<?>SearchCar(@RequestParam(value = "brand", required = false) String brand){
//                                      @RequestParam(value = "color", required = false) String color,
//                                      @RequestParam(value = "brand", required = false) String brand,
//                                      @RequestParam(value = "type", required = false) String type,
//                                      @RequestParam(value = "year", required = false) Date year,
//                                      @RequestParam(value = "price", required = false) Long price,
//                                      @RequestParam(value = "description", required = false) String description) {
        //Car car = new Car();
List<Car> cars = carService.getCarsByBrand(brand);
        //return car;
        return ResponseEntity.status(HttpStatus.OK).body(cars);

    }
@PutMapping("/{id}")
//    http://localhost:8080/car/1
    public ResponseEntity<?> updateCar(@PathVariable("id") int id, @RequestBody Car car) {
    this.id = id;
    this.car = car;
    Car car1 = carService.updateCar(id, car);
        return ResponseEntity.status(HttpStatus.OK).body(car1);

    }
    @GetMapping("/search2")
    public ResponseEntity<?> searchCarV2(@RequestParam(value = "query", required = false) String query) {
        List<Car> cars = carService.searchCarV2(query);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
}
