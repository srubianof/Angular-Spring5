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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
  public void testListAll() throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/clientes/", String.class);
    assertTrue(response.getStatusCode()==HttpStatus.OK);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode responseJson = objectMapper.readTree(response.getBody());
    assertTrue(responseJson.toString().contains("{\"id\":1,\"nombre\":\"Elsinore\",\"apellido\":\"Adhams\",\"email\":\"eadhams0@blogtalkradio.com\",\"createAt\":\"2019-08-29\"}"));
  }
}

