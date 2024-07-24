import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private baseUrl = 'http://localhost:8090/cliente';

  constructor(private http: HttpClient) { }

  getCliente(tipo: string, numero: string): Observable<any> {
    let params = new HttpParams().set('tipo', tipo).set('numero', numero);
    return this.http.get(this.baseUrl, { params });
  }
}
