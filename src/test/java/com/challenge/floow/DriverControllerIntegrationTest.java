package com.challenge.floow;

import com.challenge.floow.controllers.DriverController;
import com.challenge.floow.model.DriverDto;
import com.challenge.floow.services.DriverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DriverControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DriverController sut;
    @Mock
    private DriverService driverService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void getAllDrivers_Ok() throws Exception {
        ArrayList<DriverDto> drivers = new ArrayList<>();
        DriverDto driverDto1 = new DriverDto("Bob", "Johns", new Date(), new Date());
        DriverDto driverDto2 = new DriverDto("Chris", "Test", new Date(), new Date());
        drivers.add(driverDto1);
        drivers.add(driverDto2);

        when(driverService.getAll()).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].firstname", is(driverDto1.getFirstName())))
                .andExpect(jsonPath("$.[1].firstname", is(driverDto2.getFirstName())));

        verify(driverService,times(1)).getAll();
    }

    @Test
    public void getAllDrivers_no_data_Ok() throws Exception {
        ArrayList<DriverDto> drivers = new ArrayList<>();

        when(driverService.getAll()).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(driverService,times(1)).getAll();
    }

    @Test
    public void getDrivers_by_date_Ok() throws Exception {
        String dateInString = "11/07/2020";

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateInString);

        ArrayList<DriverDto> drivers = new ArrayList<>();
        DriverDto driverDto1 = new DriverDto("Bob", "Johns", dateTime.toDate(), new Date());
        DriverDto driverDto2 = new DriverDto("Chris", "Test", dateTime.toDate(), new Date());
        drivers.add(driverDto1);
        drivers.add(driverDto2);

        DateTime dayBefore = dateTime.minusDays(1);
        when(driverService.getByDate(dayBefore)).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers/byDate?date=10/07/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(driverService,times(1)).getByDate(dayBefore);
    }

    @Test
    public void getDrivers_by_date_empty_no_drivers_after_date_Ok() throws Exception {
        String dateInString = "11/07/2020";

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateInString);

        ArrayList<DriverDto> drivers = new ArrayList<>();
        DriverDto driverDto1 = new DriverDto("Bob", "Johns", dateTime.toDate(), new Date());
        DriverDto driverDto2 = new DriverDto("Chris", "Test", dateTime.toDate(), new Date());
        drivers.add(driverDto1);
        drivers.add(driverDto2);

        DateTime dayAfter = dateTime.plusDays(1);
        when(driverService.getByDate(dayAfter)).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers/byDate?date=10/07/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getDrivers_by_date_BadRequest() throws Exception {
        String dateInString = "11/07/2020";

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateInString);

        ArrayList<DriverDto> drivers = new ArrayList<>();
        DriverDto driverDto1 = new DriverDto("Bob", "Johns", dateTime.toDate(), new Date());
        DriverDto driverDto2 = new DriverDto("Chris", "Test", dateTime.toDate(), new Date());
        drivers.add(driverDto1);
        drivers.add(driverDto2);

        DateTime dayAfter = dateTime.plusDays(1);
        when(driverService.getByDate(dayAfter)).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers/byDate?date=10/14/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDriver_statusNotModified() throws Exception {

        DriverDto driverDto = new DriverDto("Bob", "Johns", new Date(), new Date());

        when(driverService.createDriver(driverDto)).thenReturn(null);

        mockMvc.perform(post("/api/driver/create").contentType(MediaType.APPLICATION_JSON).content(getDriverAsBytes(driverDto)))
                .andExpect(status().isNotModified());
    }

    private byte[] getDriverAsBytes(Object thing) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(thing);
    }
}
