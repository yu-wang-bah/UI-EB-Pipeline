package com.bah.domain.respository;

import com.bah.domain.model.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository  {

    Optional<Client> findByName(String name);

    Optional<Client> findByNumber(String number);
    
    public Client save(Client client);

}