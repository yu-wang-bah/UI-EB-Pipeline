package com.bah.service;

import com.bah.domain.model.Client;
import com.bah.domain.respository.ClientRepository;
import com.bah.service.exception.ClientNameAlreadyExistsException;
import com.bah.service.exception.InvalidClientNameException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class CreateClientServiceTest {

    private CreateClientService createClientService;
    private ClientRepository clientRepositoryMock;

    @Before
    public void setUp() {
        clientRepositoryMock = Mockito.mock(ClientRepository.class);
        createClientService = new CreateClientService(clientRepositoryMock);
    }

    @Test
    public void createClientSuccessfuly() {
        when(clientRepositoryMock.findByName(eq("Mary"))).thenReturn(Optional.empty());
        doAnswer(returnsFirstArg()).when(clientRepositoryMock).save(any(Client.class));

        Client client = createClientService.createClient("Mary");
        assertEquals("Mary", client.getName());
        assertNotNull(client.getNumber());
    }

    @Test(expected = InvalidClientNameException.class)
    public void createClientWithEmptyName() {
        createClientService.createClient("");
    }

    @Test(expected = ClientNameAlreadyExistsException.class)
    public void createClientWithExistingName() {
    	when(clientRepositoryMock.findByName(eq("Kevin"))).thenReturn(Optional.of(new Client("Kevin")));
    	
        createClientService.createClient("Kevin");
    }
}