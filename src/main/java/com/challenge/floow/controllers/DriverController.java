
package com.challenge.floow.controllers;

import com.challenge.floow.model.Driver;
import com.challenge.floow.model.DriverDTO;
import com.challenge.floow.services.DriverService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    // TODO: add proper exception handler
    // TODO: add unit tests
    // TODO: refactoring


    @Autowired
    DriverService driverService;

    /**
     * Get all drivers list.
     * @return the list
     */
    @GetMapping(value ="/drivers", produces = {"application/json"})
    public List<Driver> getAll() {
        return driverService.getAll();
    }

    /**
     * Gets drivers by date.
     * Date format: dd/MM/yyyy
     * @return the drivers by date
     */
    @GetMapping(value = "/drivers/byDate", produces = {"application/json"})
    public List<Driver> getByDate(@RequestParam("date") String date) {
        // TODO validate date:
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime dateTime = formatter.parseDateTime(date);
            return driverService.getByDate(dateTime);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Create driver.
     * @param driver
     * @return newly created driver
     */
    @PostMapping(value = "/driver/create", produces = {"application/json"})
    public Driver createDriver(@Valid @RequestBody DriverDTO driver) {
        ModelMapper modelMapper = new ModelMapper();
        Driver map = modelMapper.map(driver, Driver.class);
        return driverService.addDriver(map);
    }
}
