package com.challenge.floow.services;

import com.challenge.floow.model.Driver;
import org.joda.time.DateTime;
import java.util.List;

public interface DriverService {
    List<Driver> getAll();
    List<Driver> getByDate(DateTime date);
    Driver addDriver(Driver driver);
}
