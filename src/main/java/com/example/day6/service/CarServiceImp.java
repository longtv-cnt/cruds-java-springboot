package com.example.day6.service;

import com.example.day6.exception.NotFoundException;
import com.example.day6.model.Car;
import com.example.day6.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;
//    private static List<Car> cars=new ArrayList<>();
//
//    private static void initCars() {
//        Car car1 = new Car(1,"xe1","mau1","hang1","3 chỗ",new Date(2021,1,1),10000L,
//                "mo ta 1");
//        Car car2 = new Car(2,"xe2","mau2","hang2","7 chỗ",new Date(2023,1,1),10000L,
//                "mo ta 2");
//        cars.add(car1);
//        cars.add(car2);
//
//
//    }
//    static {
//        initCars();
//    }

    @Override
    public List<Car> getAllCars() {
//        return cars;
        return carRepository.findAll();
    }

   @Override
//   lấy xe theo id
    public Car getCarById(int id) {
//        for (Car car : cars) {
//            if (car.getId() == id) {
//                return car;
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id: " + id));
//            }
    }

    //        throw new NotFoundException("Car not found with id: " + id);
//    }
    @Override
//    lấy xe theo brand truyền vào brand
    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);

//        List<Car> carsByBrand = new ArrayList<>();
//        for (Car car : cars) {
//            if (car.getBrand().contains(brand)) {
//                carsByBrand.add(car);
//            }
//        }
//        return carsByBrand;
    }

//sửa thông tin truyền vào id và car(thông tin mới)
    @Override
    public Car updateCar(int id, Car car) {
        Car car1 = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id:" + id));
        if (car != null) {
            if (car.getName() != null) {
                car1.setName(car.getName());
            }
            if (car.getColor() != null) {
                car1.setColor(car.getColor());
            }
            if (car.getBrand() != null) {
                car1.setBrand(car.getBrand());
            }
            if (car.getType() != null) {
                car1.setType(car.getType());
            }
            if (car.getYear() != null) {
                car1.setYear(car.getYear());
            }
            if (car.getPrice() != null) {
                car1.setPrice(car.getPrice());
            }
            if (car.getDescription() != null) {
                car1.setDescription(car.getDescription());
            }

            carRepository.save(car1);
            return car1;
        }
        throw new NotFoundException("Car not found with id: " + id);

    }

    @Override
    public Car addCar(Car car) {
        if (car != null) {
            return carRepository.save(car);
        }
//        car = new Car(cars.size() + 1, car.getName(), car.getColor(), car.getBrand(), car.getType(), car.getYear(), car.getPrice(), car.getDescription());
//        cars.add(car);
        return null;
    }
//    xóa xe theo id

    @Override
    public boolean deleteCar(int id) {
           Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found with id:" + id));
            if (car != null) {
                carRepository.delete(car);
                return true;
            }
            return false;

    }
    @Override
    public List<Car> searchCarV2(String query) {
        return carRepository.searchCarV2(query);
    }
}