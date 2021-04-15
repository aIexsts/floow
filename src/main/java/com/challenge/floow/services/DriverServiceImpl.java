package com.challenge.floow.services;

import com.challenge.floow.model.Driver;
import com.challenge.floow.model.DriverDto;
import com.challenge.floow.repository.DriverRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<DriverDto> getAll() {
        return driverRepository.getAllDrivers()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDto> getByDate(DateTime date) {
        return driverRepository.getDriversByDate(date)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDto createDriver(DriverDto driver) {
        if (driver != null && driver.getFirstName() != null && driver.getLastName() != null && driver.getDob() != null){
            Driver createdDriver = driverRepository.addDriver(convertToModel(driver));
            return convertToDto(createdDriver);
        }
        return null;
    }

    private Driver convertToModel(DriverDto driverDTO) {
        return modelMapper.map(driverDTO, Driver.class);
    }

    private DriverDto convertToDto(Driver driver) {
        return modelMapper.map(driver, DriverDto.class);
    }
}
