package com.challenge.floow.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;

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
}
