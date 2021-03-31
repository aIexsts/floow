
package com.challenge.floow.controllers;

import com.challenge.floow.model.DriverDto;
import com.challenge.floow.services.DriverService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    DriverService driverService;

    /**
     * Get all drivers list.
     *
     * @return the list
     */
    @GetMapping(value = "/drivers", produces = {"application/json"})
    public ResponseEntity<List<DriverDto>> getAll() {
        List<DriverDto> result = driverService.getAll();
        return ResponseEntity.ok().body(result);
    }

    /**
     * Gets drivers by date.
     * Date format: dd/MM/yyyy
     *
     * @return the drivers by date
     */
    @GetMapping(value = "/drivers/byDate", produces = {"application/json"})
    public ResponseEntity<?> getByDate(@RequestParam("date") String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime dateTime = formatter.parseDateTime(date);

            List<DriverDto> result = driverService.getByDate(dateTime);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred: " + e);
        }
    }

    /**
     * Create driver.
     *
     * @param driver
     * @return newly created driver
     */
    @PostMapping(value = "/driver/create", produces = {"application/json"})
    public ResponseEntity<?> createDriver(@Valid @RequestBody DriverDto driver) {
        DriverDto result = driverService.createDriver(driver);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Driver not created");
        }
    }
}
