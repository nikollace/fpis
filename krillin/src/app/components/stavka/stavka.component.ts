import { Component, Input, OnInit, Output } from '@angular/core';
import { NarudzbenicaService } from 'src/app/services/narudzbenica.service';
import { EventEmitter } from '@angular/core';
import { StavkaNarudzbenice } from 'src/app/model/stavka_narudzbenice';
import { Materijal } from 'src/app/model/materijal';
import { StavkaNarudzbeniceTable } from 'src/app/model/sntabel';

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
    if (this.stavke) {
      this.stavke.push(new StavkaNarudzbeniceTable(this.sifra, 1, this.kolicina, this.jm, this.materijal, true))
      console.log("prosledjene stavke", this.stavke)
    }
  }

  dodaj() {
    let max = 1;
    if (this.stavke.length != 0) {
      this.stavke.forEach((element:any) => {
        if(element.redni_broj > max) {
          max = element.redni_broj;
        }
      });
      this.stavke.push(new StavkaNarudzbeniceTable(
        this.sifra,
        max + 1,
        this.kolicina, this.jm, new Materijal(-1, ''), true))
    } else {
      this.stavke.push(new StavkaNarudzbeniceTable(this.sifra, 1, this.kolicina, this.jm, new Materijal(-1, ''), true))
    }
  }

  @Input() selectedRowIds: Set<number> = new Set<number>();
  @Output() selectedRowIdsChange = new EventEmitter();

  selectedId: any;

  obrisi(id: number) {

    const stavkica = this.stavke.find((stavka: any) => stavka.redni_broj == id);
    if (Object.keys(stavkica).length == 6) {
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
