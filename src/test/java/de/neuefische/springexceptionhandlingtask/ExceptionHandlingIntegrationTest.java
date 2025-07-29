package de.neuefische.springexceptionhandlingtask;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCarBrand_withInvalidBrand_returnsBadRequestAndErrorMessage() throws Exception {
        mockMvc.perform(get("/api/cars/bmw"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Only 'porsche' allowed")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    void getAnimalSpecies_withInvalidSpecies_returnsBadRequestAndErrorMessage() throws Exception {
        mockMvc.perform(get("/api/animals/cat"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Only 'dog' is allowed")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    void getAllCars_alwaysThrowsNoSuchElementException_returnsNotFoundAndErrorMessage() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("No Cars found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    void getAllAnimals_alwaysThrowsNoSuchElementException_returnsNotFoundAndErrorMessage() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("No Animals found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}