import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NarudzbenicaService {

  private apiUrl: String;

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/narudzbenica';
  }

  getNarudzbenica(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  getDobavljac(pib: string) {
    return this.http.get(`${this.apiUrl}/dobavljac/${pib}`);
  }

  getMaterijali() {
    return this.http.get(`${this.apiUrl}/materijal`);
  }

  getStavke(id: number) {
    return this.http.get(`${this.apiUrl}/stavka/${id}`);
  }

  getMaxSifra() {
    return this.http.get(`${this.apiUrl}/max`);
  }

  saveNarudzbenica(narudzbenica: any) {
    return this.http.post(`${this.apiUrl}`, narudzbenica);
  }

  updateNarudzbenica(narudzbenica: any) {
    return this.http.patch(`${this.apiUrl}/${narudzbenica.sifra}`, narudzbenica);
  }

  ids:number[] = [];

  obrisiStavku(rbr: Set<number>, sifra: any) {
    for (let item of rbr.values()) this.ids.push(item)
    return this.http.post(`${this.apiUrl}/stavka/${sifra}`, this.ids);
  }
}
