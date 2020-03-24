package com.bolsadeideas.springboot.backend.apirest.models.services;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Demarco esta clase como una clase de servicio en Spring para guardarlo en el contenedor de spring
//para poder inyectarlo en el controlador
@Service
public class ClienteServiceImpl implements IClienteService {
  //Inyecto el clienteDao
  @Autowired
  private IClienteDao clienteDao;

  @Override
  //Sobreecribo la transaccionalidad del metodo de spring
  @Transactional(readOnly = true)
  public List<Cliente> findAll() {
    return (List<Cliente>) clienteDao.findAll();
  }

  @Override
  @Transactional
  public Cliente save(Cliente cliente) {
    return clienteDao.save(cliente);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    clienteDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Cliente findById(Long id) {
    return clienteDao.findById(id).orElse(null);
  }

}

