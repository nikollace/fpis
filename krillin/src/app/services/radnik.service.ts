import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RadnikService {

  private apiUrl: String;

  constructor(private http: HttpClient) { 
    this.apiUrl = 'http://localhost:8080/radnik';
  }

  get(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  insert(data: any) {
    return this.http.post(`${this.apiUrl}`, data);
  }

  update(id: number, data: any) {
    console.log(id, data)
    return this.http.patch(`${this.apiUrl}/${id}`, data);
  }
}
