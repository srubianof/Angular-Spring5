import { Injectable } from '@angular/core';
import { CLIENTES } from './clientes.json';
import { Cliente } from './cliente';
import { of, Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ClienteService {
private urlEndPoint:string = 'http://localhost:8080/api/clientes';
  constructor(private http: HttpClient) { }
  //Lo que retorna el metodo debe ser un stream
  getClientes(): Observable<Cliente[]> {
    //Convertir el listado de clientes en un observable o Stream
    // return of(CLIENTES);
    return this.http.get<Cliente[]>(this.urlEndPoint);
  }
}
