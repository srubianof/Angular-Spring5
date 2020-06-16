package com.bolsadeideas.springboot.backend.apirest;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootBackendApirestApplicationTests {
  @Autowired
  private IClienteDao clienteDao;

  @Test
  public void testListAllAndContainclien1() throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/clientes/", String.class);
    assertTrue(response.getStatusCode()==HttpStatus.OK);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode responseJson = objectMapper.readTree(response.getBody());
    assertTrue(responseJson.toString().contains("{\"id\":1,\"nombre\":\"Elsinore\",\"apellido\":\"Adhams\",\"email\":\"eadhams0@blogtalkradio.com\",\"createAt\":\"2019-08-29\"}"));
  }
  @Test
  public void shouldGetClient5() throws IOException{
    RestTemplate restTemplate = new RestTemplate();
    Cliente cliente = new Cliente();
    cliente.setNombre("NOMBRE2");
    cliente.setApellido("APELLIDO2");
    cliente.setEmail("EMAIL2");
    cliente.setCreateAt(new Date(2020,01,01));
    HttpEntity<Cliente> request = new HttpEntity<>(cliente);
    ResponseEntity<Cliente> response = restTemplate.postForEntity("http://localhost:8080/api/clientes/", request, Cliente.class);
    assertTrue(response.getStatusCode()==HttpStatus.CREATED);
//    ObjectMapper objectMapper = new ObjectMapper();
//    JsonNode responseJson = objectMapper.readTree(response.getBody());
//    assertTrue(responseJson.toString().contains("\"id\":5,\"nombre\":\"Ermengarde\",\"apellido\":\"Lecointe\",\"email\":\"elecointe4@de.vu\",\"createAt\":\"2019-06-22\"}"));
  }
}

