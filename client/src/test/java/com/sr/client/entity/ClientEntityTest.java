package com.sr.client.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ClientEntityTest {
    @Test
    public void testClientIsAdult() {
        // Arrange
        ClientEntity client = new ClientEntity();

        client.setName("Sebastian Rueda");
        client.setGender("Male");
        client.setIdentification("1759203464");
        client.setPhone("0963767743");
        client.setAddress("Quito");
        client.setPassword("12345");
        client.setStatus(Boolean.TRUE);
        client.setAge(8);
        client.setCode(6L);

        // Act
        boolean esMayorDeEdad = client.getAge()>18;

        // Assert
        assertFalse(esMayorDeEdad, "Client must be older than or equal to 18 years");
    }

    @Test
    public void testClientHasPassword() {
        // Arrange
        ClientEntity client = new ClientEntity();

        client.setName("Sebastian Rueda");
        client.setGender("Male");
        client.setIdentification("1759203464");
        client.setPhone("0963767743");
        client.setAddress("Quito");
        client.setPassword("12345");
        client.setStatus(Boolean.TRUE);
        client.setAge(8);
        client.setCode(6L);

        // Act
        boolean hasPassword = client.getPassword()!=null;

        // Assert
        assertTrue(hasPassword, "Client has password set");
    }
}
