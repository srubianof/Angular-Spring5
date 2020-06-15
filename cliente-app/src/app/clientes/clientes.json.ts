import { Cliente } from './cliente';
import { from } from 'rxjs';
import {skipWhile} from 'rxjs/operators';

export const CLIENTES: Cliente[] = [
  { id: 1, nombre: 'a', apellido: 'a', email: 'a@a.com', createAt: '2020-03-17' },
  { id: 2, nombre: 'b', apellido: 'b', email: 'b@b.com', createAt: '2020-03-17' },
  { id: 3, nombre: 'c', apellido: 'c', email: 'c@c.com', createAt: '2020-03-17' },
  { id: 4, nombre: 'd', apellido: 'd', email: 'd@d.com', createAt: '2020-03-17' },
  { id: 5, nombre: 'e', apellido: 'e', email: 'e@e.com', createAt: '2020-03-17' },
  { id: 6, nombre: 'f', apellido: 'f', email: 'f@f.com', createAt: '2020-03-17' }
];

export const clientes$ = from(CLIENTES);
export const FILTROHASTAQUE = clientes$.pipe(skipWhile( cliente => cliente.id <5));
