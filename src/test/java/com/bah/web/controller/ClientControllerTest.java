package com.bah.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bah.domain.model.Client;
import com.bah.service.CreateClientService;
import com.bah.service.exception.ClientNameAlreadyExistsException;
import com.bah.web.to.CreateClientRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreateClientService createClientServiceMock;

    @Autowired
    ObjectMapper objectMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerTest.class);


    @Test
    public void testCreateClientSuccessfully()  {
        given(createClientServiceMock.createClient("Foo")).willReturn(new Client("Foo"));

        try {
			mockMvc.perform(post("/clients")
			        .contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
			        .andExpect(status().isCreated())
			        .andExpect(jsonPath("$.name", is("Foo")))
			        .andExpect(jsonPath("$.number", notNullValue()));
		} catch (JsonProcessingException e) {
			logger.info("error happened in the ClientControllerTest class", e);
		} catch (Exception e) {
			logger.info("error happened in the ClientControllerTest class", e);
		}
    }

    @Test
    public void testCreateClientWithEmptyName()  {
        try {
			mockMvc.perform(post("/clients")
			    .contentType(MediaType.APPLICATION_JSON)
			    .content(objectMapper.writeValueAsBytes(new CreateClientRequest(""))))
			    .andExpect(status().isBadRequest());
		} catch (JsonProcessingException e) {
			logger.info("error happened in the ClientControllerTest class", e);

		} catch (Exception e) {
			logger.info("error happened in the ClientControllerTest class", e);

		}
    }

    @Test
    public void testCreateClientWithExistingName()  {
        given(createClientServiceMock.createClient("Keith")).willThrow(new ClientNameAlreadyExistsException());

        try {
			mockMvc.perform(post("/clients")
			    .contentType(MediaType.APPLICATION_JSON)
			    .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Keith"))))
			    .andExpect(status().isConflict());
		} catch (JsonProcessingException e) {
			logger.info("error happened in the ClientControllerTest class", e);

		} catch (Exception e) {
			logger.info("error happened in the ClientControllerTest class", e);

		}
    }
}