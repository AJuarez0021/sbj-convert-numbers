package com.work.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.converter.dto.NumberRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 *
 * @author ajuar
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NumberToWordsControllerTest {
      
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testConvertNumber() throws Exception {
        NumberRequestDTO request = new NumberRequestDTO(123L);
        
        mockMvc.perform(post("/v1/numbers/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(123))
                .andExpect(jsonPath("$.words").value("ciento veintitres"));
    }
    
    @Test
    void testConvertNumberNegative() throws Exception {
        NumberRequestDTO request = new NumberRequestDTO(-1L);
        
        mockMvc.perform(post("/v1/numbers/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(-1))
                .andExpect(jsonPath("$.words").value("menos uno"));
    }
}
