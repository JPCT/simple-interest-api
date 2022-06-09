package com.jpct.simpleinterestapi.app.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jpct.simpleinterestapi.app.rest.request.SimpleInterestRequest;
import com.jpct.simpleinterestapi.app.rest.response.SimpleInterestResponse;
import com.jpct.simpleinterestapi.domain.service.SimpleInterestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@EnableWebMvc
public class SimpleInterestControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @Mock
    private SimpleInterestService simpleInterestService;
    @InjectMocks
    private SimpleInterestController subject;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
        mapper = new ObjectMapper().findAndRegisterModules().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void shouldReturnStatusOk() throws Exception
    {
        SimpleInterestRequest simpleInterestRequest = SimpleInterestRequest.newBuilder()
                .terms(10)
                .rate(3.0)
                .amount(100.0)
                .build();
        List<SimpleInterestResponse> simpleInterestResponseList = Collections.singletonList(SimpleInterestResponse.newBuilder()
                        .paymentDate(LocalDate.now())
                        .amount(100.0)
                        .paymentNumber(1)
                .build());
        when(simpleInterestService.calculateSimpleInterest(any())).thenReturn(simpleInterestResponseList);

        MvcResult result = mockMvc.perform(post("/api/interest").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(simpleInterestRequest))).andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
        Object object = mapper.readValue(response.getBytes(), SimpleInterestResponse[].class);
        assertNotNull(object);

        verify(simpleInterestService, times(1)).calculateSimpleInterest(any());
    }

    @Test
    public void shouldReturnStatusBadRequest() throws Exception
    {
        SimpleInterestRequest simpleInterestRequest = SimpleInterestRequest.newBuilder()
                .terms(1)
                .rate(3.0)
                .amount(100.0)
                .build();

        mockMvc.perform(post("/api/interest").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(simpleInterestRequest))).andExpect(status().isBadRequest()).andReturn();
    }

    private String asJsonString(final Object obj)
    {
        try
        {
            return mapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}