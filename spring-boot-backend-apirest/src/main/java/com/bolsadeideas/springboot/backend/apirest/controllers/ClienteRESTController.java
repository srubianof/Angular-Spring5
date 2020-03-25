package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
  public Cliente show(@PathVariable Long id) {
    return clienteService.findById(id);
  }


  //Mapeo para que el metodo de tipo POST me cree un objeto cliente
  //nos envian el cliente en formato json (por eso requestbody) y lo transformo al objeto cliente
  @PostMapping("/clientes")
//  Retorno el estado de la creacion
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente create(@RequestBody Cliente cliente) {
//    ESTA MANERA DE CREAR FECHAS QUEDA EN LA ENTIDAD CLIENTE MEJOR PUESTA
//    cliente.setCreateAt(new Date());
    return clienteService.save(cliente);
  }


  // tener metodo update y su verbo es el PUT
  @PutMapping("/clientes/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
    Cliente clienteAcutal = clienteService.findById(id);
//    modificar cliente actual (viene de DB)
    clienteAcutal.setApellido((cliente.getApellido()));
    clienteAcutal.setNombre((cliente.getNombre()));
    clienteAcutal.setEmail((cliente.getEmail()));

    return clienteService.save(clienteAcutal);

  }


  @DeleteMapping("/clientes/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    clienteService.delete(id);
  }
}
