package com.challenge.floow.services;

import com.challenge.floow.model.Driver;
import com.challenge.floow.repository.DriverRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public List<Driver> getAll() {
        return driverRepository.getAllDrivers();
    }

    @Override
    public List<Driver> getByDate(DateTime date) {
        return driverRepository.getDriversByDate(date);
    }

    @Override
    public Driver addDriver(Driver driver) {
        return driverRepository.addDriver(driver);
    }
}
