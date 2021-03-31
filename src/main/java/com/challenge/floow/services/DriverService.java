package com.challenge.floow.services;

import com.challenge.floow.model.DriverDto;
import org.joda.time.DateTime;
import java.util.List;

public interface DriverService {
    List<DriverDto> getAll();
    List<DriverDto> getByDate(DateTime date);
    DriverDto createDriver(DriverDto driver);
}
