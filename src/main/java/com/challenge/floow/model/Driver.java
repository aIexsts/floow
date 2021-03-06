package com.challenge.floow.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;
import java.util.Objects;

public class Driver {
    @CsvBindByName(column = "Id", required = true)
    private long id;

    @CsvBindByName(column = "First Name", required = true)
    private String firstName;

    @CsvBindByName(column = "Last Name", required = true)
    private String lastName;

    @CsvDate("dd/MM/yyyy hh:mm:ss")
    @CsvBindByName(column = "DOB", required = true)
    private Date dob;

    @CsvDate("dd/MM/yyyy hh:mm:ss")
    @CsvBindByName(column = "Created Date", required = true)
    private Date createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(dob, driver.dob) && Objects.equals(createdDate, driver.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dob, createdDate);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdDate=" + createdDate +
                '}';
    }
}
