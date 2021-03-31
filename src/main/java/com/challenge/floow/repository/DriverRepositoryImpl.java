package com.challenge.floow.repository;

import com.challenge.floow.model.Driver;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    private static final Logger logger = LoggerFactory.getLogger(DriverRepositoryImpl.class);

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("drivers.csv"));
            CsvToBeanBuilder<Driver> beanBuilder = new CsvToBeanBuilder<>(reader);
            beanBuilder.withType(Driver.class);
            drivers = beanBuilder.build().parse();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        return drivers;
    }

    @Override
    public List<Driver> getDriversByDate(DateTime date) {
        List<Driver> drivers = getAllDrivers();
        return drivers.stream().filter(item -> new DateTime(item.getCreatedDate()).isAfter(date)).collect(Collectors.toList());
    }

    @Override
    public Driver addDriver(Driver driver) {

        try (Writer writer = new FileWriter("drivers.csv")) {
            List<Driver> allDrivers = getAllDrivers();
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            // update driver details:
            driver.setId(allDrivers.size() + 1);
            driver.setCreatedDate(new Date());
            allDrivers.add(driver);

            // update csv file:
            sbc.write(allDrivers);
            return driver;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

        return null;
    }
}
