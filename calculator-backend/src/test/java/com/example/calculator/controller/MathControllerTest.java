package com.example.calculator.controller;

import com.example.calculator.dto.DoMathRequest;
import com.example.calculator.service.MathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathService.class)
public class MathControllerTest {
    @MockBean
    private MathService mathServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doMath_success() throws Exception {
        DoMathRequest data = new DoMathRequest(
                6,
                4,
                "+"
        );

        when(mathServiceMock.doMath(data.getOperand1(), data.getOperand2(), data.getOperation())).thenReturn(10.0);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/doMath")
                .content(asJsonString(data))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"calcResponse\": 10.0}"))
                .andReturn();
    }

    @Test
    public void doMath_invalidOperation() throws Exception {
        DoMathRequest data = new DoMathRequest(
                6,
                0,
                "/"
        );

        when(mathServiceMock.doMath(data.getOperand1(), data.getOperand2(), data.getOperation())).thenReturn(10.0);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/doMath")
                .content(asJsonString(data))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot divide by 0"))
                .andReturn();
    }

    private byte[] asJsonString(DoMathRequest data) {
        try {
            return new ObjectMapper().writeValueAsString(data).getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
