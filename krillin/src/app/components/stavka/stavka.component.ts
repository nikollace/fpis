import { Component, Input, OnInit, Output } from '@angular/core';
import { NarudzbenicaService } from 'src/app/services/narudzbenica.service';
import { EventEmitter } from '@angular/core';
import { StavkaNarudzbeniceRequest } from 'src/app/model/stavka_narudzbenice';
import { Materijal } from 'src/app/model/materijal';
import { Narudzbenica } from 'src/app/model/narudzbenica';

@Component({
  selector: 'app-stavka',
  templateUrl: './stavka.component.html',
  styleUrls: ['./stavka.component.scss']
})
export class StavkaComponent implements OnInit {

  @Input() stavke: any;
  @Input() sifra: any;
  @Output() stavkeChange = new EventEmitter();

  kolicina: number = 1;
  jm: string = '';
  materijal: Materijal = new Materijal(-1, '');
  materijali: any;

  constructor(private service: NarudzbenicaService) {
    this.service.getMaterijali().subscribe(res => {
      this.materijali = res;
    });
  }

  ngOnInit(): void {
    if (this.stavke)
      this.stavke.push(new StavkaNarudzbeniceRequest(1, this.kolicina, this.jm, this.materijal))
  }

  dodaj() {
    if (this.stavke.length != 0) {
      this.stavke.push(new StavkaNarudzbeniceRequest(
        this.stavke[this.stavke.length - 1].redni_broj + 1,
        this.kolicina, this.jm, new Materijal(-1, '')))
    } else {
      this.stavke.push(new StavkaNarudzbeniceRequest(1, this.kolicina, this.jm, new Materijal(-1, '')))
    }
  }

  @Input() selectedRowIds: Set<number> = new Set<number>();
  @Output() selectedRowIdsChange = new EventEmitter();

  selectedId: any;

  obrisi(id: number) {

    const stavkica = this.stavke.find((stavka: any) => stavka.redni_broj == id);
    if (Object.keys(stavkica).length == 4) {
      this.stavke = this.stavke.filter((stavka: any) => stavka.redni_broj != id);
    }

    if (this.selectedRowIds.has(id)) {
      this.selectedRowIds.delete(id);
    }
    else {
      this.selectedRowIds.add(id);
    }
  }

  rowIsSelected(id: number) {
    return this.selectedRowIds.has(id);
  }
}
