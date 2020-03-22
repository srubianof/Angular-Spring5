package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
