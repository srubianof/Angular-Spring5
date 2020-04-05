import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente"
import { ClienteService } from "./cliente.service"
import { Router, ActivatedRoute } from "@angular/router"
//Libreria para que las respuestas sean mostradas de manera mas amigable
import swal from "sweetalert2"

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  cliente: Cliente = new Cliente()
  titulo: string = "Crear cliente"

  //Inyecto las clases necesarias
  constructor(private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    //Cargo los detalles del cliente seleccionado cuando el boton se activa
    this.cargarCliente()
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente)
      }
    })
  }
  //paso el cliente y lo suscribo
  //cuando tengo el objeto creado, retorno la respuesta que contiene el nuevo objeto creado
  //y la idea es redirijir al listado de vuelta para mostrar el cliente creado
  create(): void {
    this.clienteService.create(this.cliente)
      .subscribe(cliente => {
        this.router.navigate(['/clientes'])
        //Con esto muestro la respuesta de creacion exitosa del cliente/
        //Esa libreria sale de sweetalert2.github.io
        swal('Nuevo Cliente', `Cliente ${cliente.nombre} creado con exito`, 'success')
      })
  }

  update(): void{
    this.clienteService.update(this.cliente)
    .subscribe(cliente=>{
      this.router.navigate(['/clientes'])
      //Con esto muestro la respuesta de creacion exitosa del cliente/
      //Esa libreria sale de sweetalert2.github.io
      swal('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito`, 'success')
    })
  }

}
