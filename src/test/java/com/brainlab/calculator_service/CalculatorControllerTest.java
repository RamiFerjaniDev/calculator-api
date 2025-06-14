package com.brainlab.calculator_service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

// Unit tests for CalculatorController
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // tests if the addition returns the correct value
    @Test
    void testAddValidOperands() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", "7,-7,3.5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.sum").value(3.5));
    }
    // tests if only one operands return the same value
    @Test
    void testAddSingleOperand() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", "42"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sum").value(42));
    }
    // tests if an error is returned if we add non numerical values
    @Test
    void testAddInvalidOperands() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", "42,tru"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Invalid input: all operands must be numbers."));
    }
    // tests if empty parameters return 0
    @Test
    void testAddEmptyOperands() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sum").value(0));
    }
    // tests if fractionnal numbers return correct results
    @Test
    void testAddDoublesOperands() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", "8.5,8.9"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sum").value(17.4));
    }
    // tests if spaces in the input doesn't cause any bad requests or result
    @Test
    void testSpacesOperands() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("operands", "8 ,   3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sum").value(11));
    }
    // tests if operands are missing
    @Test
    void testAdd_MissingOperandsParameter() throws Exception {
        mockMvc.perform(get("/calculator/add")) // no .param() at all
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Missing 'operands' parameter."));
    }

}