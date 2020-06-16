package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//Mapeo todos los metodos del REST
@RequestMapping("/api")
public class ClienteRESTController {

  //Inyecto un componente Spring buscando una implementacion contreta (ClienteServiceImlp)
  @Autowired
  private IClienteService clienteService;


  //Mapeo para que el metodo de tipo GET se pueda utilizar
  @GetMapping("/clientes")
  public List<Cliente> index() {
    return clienteService.findAll();
  }


  //Mapeo para que el metodo de tipo GET me retorne clientes por id
  @GetMapping("/clientes/{id}")
  //  Retorno el estado de la creacion (por defecto es OK)
//  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> show(@PathVariable Long id) {

    //Basicamente se controla el error verificando si el usuario con el id parametro existe
    Cliente cliente = null;
    Map<String, Object> response = new HashMap<>();
    try {
      cliente = clienteService.findById(id);
    } catch (DataAccessException e) {
      response.put("Mensaje", "Error al realizar la consulta en la base de datos");
      response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (cliente == null) {
      response.put("Mensaje", "El cliente con id: ".concat(id.toString().concat(" no existe en la base de datos")));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
  }


  //Mapeo para que el metodo de tipo POST me cree un objeto cliente
  //nos envian el cliente en formato json (por eso requestbody) y lo transformo al objeto cliente
  @PostMapping("/clientes")
  public ResponseEntity<?> create(@RequestBody Cliente cliente) {
    //Basicamente se trata de controlar los errores al tener duplicado un email (refer to Entity Cliente, unique email) y
    //cumplir con condiciones de no null para nombre y apellido
    Cliente clienteNew = null;
    Map<String, Object> response = new HashMap<>();
    try {
      clienteNew = clienteService.save(cliente);
    } catch (DataAccessException e) {
      response.put("Mensaje", "Error al realizar la consulta en la base de datos");
      response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put("Mensaje", "El cliente ha sido creado con exito");
    response.put("Cliente", clienteNew);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }


  // tener metodo update y su verbo es el PUT
  @PutMapping("/clientes/{id}")
  public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
    Cliente clienteActual = clienteService.findById(id);
    Map<String, Object> response = new HashMap<>();
    Cliente clienteUpdated = null;

    if (clienteActual == null) {
      response.put("Mensaje", "No se pudo editar el cliente con id: ".concat(id.toString().concat(" no existe en la base de datos")));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    try {
      clienteActual.setApellido(cliente.getApellido());
      clienteActual.setNombre(cliente.getNombre());
      clienteActual.setEmail(cliente.getEmail());
      clienteActual.setCreateAt(cliente.getCreateAt());

      clienteUpdated = clienteService.save(clienteActual);

    } catch (DataAccessException e) {
      response.put("Mensaje", "Error al actualizar el cliente en la base de datos");
      response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put("Mensaje", "El cliente ha sido actualizado con exito");
    response.put("Cliente", clienteUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }


  @DeleteMapping("/clientes/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    clienteService.delete(id);
  }
}
