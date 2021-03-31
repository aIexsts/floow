package com.challenge.floow.repository;

import com.challenge.floow.model.Driver;
import org.joda.time.DateTime;
import java.util.List;


public interface DriverRepository {

    List<Driver> getAllDrivers();

    List<Driver> getDriversByDate(DateTime date);

    Driver addDriver(Driver driver);
}
