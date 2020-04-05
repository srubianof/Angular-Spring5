import { Injectable } from '@angular/core';
import { CLIENTES } from './clientes.json';
import { Cliente } from './cliente';
import { of, Observable } from "rxjs";
import { HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class ClienteService {
  private urlEndPoint: string = 'http://localhost:8080/api/clientes';

  private httpHeaders = new HttpHeaders({'Content-type' : 'application/json'})
  constructor(private http: HttpClient) { }
  //Lo que retorna el metodo debe ser un stream
  getClientes(): Observable<Cliente[]> {
    //Convertir el listado de clientes en un observable o Stream
    // return of(CLIENTES);
    return this.http.get<Cliente[]>(this.urlEndPoint);
  }

  create(cliente: Cliente) : Observable<Cliente>{
    //De esta manera envio a la URL un cliente, como en el post de la API tengo un return aca tambien
    //envio la url, el nuevo cliente y el headers
    return this.http.post<Cliente>(this.urlEndPoint, cliente,{headers:this.httpHeaders})
  }

  getCliente(id): Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`)
  }

  update(cliente: Cliente): Observable<Cliente>{
    //id del cliente a actualizar, el objeto cliente con todos los datos para le udate y cabeceras
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers :this.httpHeaders})
  }

  delete(id: number): Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }
}
