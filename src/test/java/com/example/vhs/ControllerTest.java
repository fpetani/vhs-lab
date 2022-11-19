package com.example.vhs;

import com.example.vhs.controller.VHSController;
import com.example.vhs.exception.EntityDoesNotExistException;
import com.example.vhs.repository.VHSRepository;
import com.example.vhs.service.RentalService;
import com.example.vhs.service.VHSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VHSService vhsService;

    @MockBean
    private VHSRepository vhsRepository;

    @MockBean
    private VHSController vhsController;

    @MockBean
    private RentalService rentalService;


    @Test
    public void GivenExistingId_When_FindVHSById_ShouldReturnResponse() throws Exception {
        mvc.perform(get("/vhs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenNonExistingID_When_FindVHSById_ShouldThrowError() throws Exception {
        when(vhsController.findById(ArgumentMatchers.any())).thenThrow(new EntityDoesNotExistException());

        mvc.perform(get("/vhs/300")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GivenValidRequest_When_CreateRental_ShouldReturnResponse() throws Exception{
        mvc.perform(post("/rental")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(GenerateClass.generateRentalForm())))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenValidRequest_When_UpdateRental_ShouldReturnResponse() throws Exception{
        mvc.perform(put("/rental/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(GenerateClass.generateReturnForm())))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenBadRequest_When_CreateRental_ShouldReturnError() throws Exception{
        mvc.perform(post("/rental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(GenerateClass.generateBadRentalForm())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GivenBadRequest_When_UpdateRental_ShouldReturnError() throws Exception{
        mvc.perform(put("/rental/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(GenerateClass.generateBadReturnForm())))
                .andExpect(status().isBadRequest());
    }

}
