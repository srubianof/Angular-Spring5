import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente"
import { ClienteService } from "./cliente.service"
import { Router } from "@angular/router"

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  cliente: Cliente = new Cliente()
  titulo: string = "Crear cliente"

  //Inyecto las clases necesarias
  constructor(private clienteService: ClienteService,
    private router: Router) { }

  ngOnInit(): void {
  }
//paso el cliente y lo suscribo
//cuando tengo el objeto creado, retorno la respuesta que contiene el nuevo objeto creado
//y la idea es redirijir al listado de vuelta para mostrar el cliente creado
  public create(): void {
    this.clienteService.create(this.cliente).subscribe(
      response => this.router.navigate(['/clientes'])
    )
  }
}
