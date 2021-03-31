package com.challenge.floow;

import com.challenge.floow.model.Driver;
import com.challenge.floow.model.DriverDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DriverDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertDriverEntityToDriverDto_thenCorrect() {
        Driver driver = new Driver();
        driver.setFirstName(randomAlphabetic(6));
        driver.setLastName(randomAlphabetic(6));
        driver.setDob(new Date());

        DriverDto driverDto = modelMapper.map(driver, DriverDto.class);
        assertEquals(driver.getFirstName(), driverDto.getFirstName());
        assertEquals(driver.getLastName(), driverDto.getLastName());
        assertEquals(driver.getDob(), driverDto.getDob());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        DriverDto driverDto = new DriverDto();
        driverDto.setFirstName(randomAlphabetic(6));
        driverDto.setLastName(randomAlphabetic(6));
        driverDto.setDob(new Date());

        Driver driver = modelMapper.map(driverDto, Driver.class);
        assertEquals(driverDto.getFirstName(), driver.getFirstName());
        assertEquals(driverDto.getLastName(), driver.getLastName());
        assertEquals(driverDto.getDob(), driver.getDob());
    }

}
