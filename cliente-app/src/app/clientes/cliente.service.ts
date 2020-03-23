import { Injectable } from '@angular/core';
import { CLIENTES } from './clientes.json';
import { Cliente } from './cliente';
import { of, Observable } from "rxjs";

@Injectable()
export class ClienteService {

  constructor() { }
  //Lo que retorna el metodo debe ser un stream
  getClientes(): Observable<Cliente[]> {
    //Convertir el listado de clientes en un observable o Stream 
    return of(CLIENTES);
  }
}
