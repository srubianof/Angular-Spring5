import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from "./cliente.service";

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];

  constructor(private ClienteService: ClienteService) { }
//Registrar el observador a nuestros clientes(observable)
  ngOnInit() {
    //suscribir o registrar el observador a nuestros clientes
    this.ClienteService.getClientes().subscribe(
      (clientes) => this.clientes = clientes
    );
  }

}
