import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from "./cliente.service";
import swal from "sweetalert2";
import {FILTROHASTAQUE} from "./clientes.json";

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
})

//En esta clase tengo el listado de los clientes
export class ClientesComponent implements OnInit {

  clientes: Cliente[];

  constructor(private ClienteService: ClienteService) { }
  //Registrar el observador a nuestros clientes(observable)
  ngOnInit() {
    //suscribir o registrar el observador a nuestros clientes
    // this.ClienteService.getClientes().subscribe(
    //   (clientes) => this.clientes = clientes
    // );
    this.ClienteService.getClientes().subscribe(
      (clientes) => this.clientes = clientes
    );
    FILTROHASTAQUE.subscribe(
      val => console.log(val)
    )

  }

  delete(cliente: Cliente): void {

    swal({
      title: 'Esta seguro?',
      text: `Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.ClienteService.delete(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swal(
              'Cliente Eliminado!',
              `Cliente ${cliente.nombre} eliminado con exito.`,
              'success'
            )
          }
        )
      }
    })





  }

}
