package com.challenge.floow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class DriverDto {
    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_birth")
    private Date dob;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("created_at")
    private Date createdDate;

    public DriverDto() {}

    public DriverDto(String firstName, String lastName, Date dob, Date createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.createdDate = createdDate;
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
        DriverDto driverDto = (DriverDto) o;
        return Objects.equals(firstName, driverDto.firstName) && Objects.equals(lastName, driverDto.lastName) && Objects.equals(dob, driverDto.dob) && Objects.equals(createdDate, driverDto.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob, createdDate);
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdDate=" + createdDate +
                '}';
    }
}
