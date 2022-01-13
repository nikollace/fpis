import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { RadnikRequest } from 'src/app/model/radnik';
import { RadnikUpdateRequest } from 'src/app/model/radnik_update';
import { RadnikService } from 'src/app/services/radnik.service';
import { ModalComponent } from '../modals/modal/modal.component';
import { formatDate } from '@angular/common'

@Component({
  selector: 'app-radnik',
  templateUrl: './radnik.component.html',
  styleUrls: ['./radnik.component.scss']
})
export class RadnikComponent implements OnInit {

  pozicije: string[] = ['programer', 'direktor', 'ceo', 'cto', 'product_manager', 'project_manager', 'operativac'];
  statusi: string[] = ['stalni_rad', 'privremeni_rad', 'praksa'];

  radnik: any;

  pronadjiClicked: boolean = false;

  sifra: any;
  jmbg: any;
  ime_prezime: any;
  koeficijent: any;
  pozicija: string = '';
  status: string = '';
  datum_zaposlenja: any;

  constructor(private service: RadnikService, private dialog: MatDialog) {

  }

  ngOnInit(): void {
  }

  ocisti() {
    this.sifra = null;
    this.jmbg = null;
    this.ime_prezime = null;
    this.koeficijent = null;
    this.pozicija = '';
    this.status = '';
    this.datum_zaposlenja = null;
    this.pronadjiClicked = false;
  }

  popuniFormu(sifra: number, jmbg: string, ime_prezime: string, koeficijent: number, pozicija: string,
    status: string, datum_zaposlenja: Date) {
    this.sifra = sifra;
    this.jmbg = jmbg;
    this.ime_prezime = ime_prezime;
    this.koeficijent = koeficijent;
    this.pozicija = pozicija;
    this.status = status;
    this.datum_zaposlenja = datum_zaposlenja;
  }

  get(id: string) {
    if (!this.sifra) {
      this.openDialog(true, 'Greška!', 'Unesite šifru radnika!');
    } else {
      this.pronadjiClicked = true;
      this.service.get(id).subscribe(res => {
        if (res == null) {
          this.ocisti();
          this.openDialog(true, 'Greška!', 'Radnik sa tom šifrom ne postoji!');
        } else {
          this.radnik = res;
          this.radnik.datum_zaposlenja = formatDate(
            new Date(this.radnik.datum_zaposlenja),
            "yyyy-MM-ddTHH:mm:ss", "en-US");
          this.popuniFormu(this.radnik.sifra,
            this.radnik.jmbg, this.radnik.ime_prezime,
            this.radnik.koeficijent, this.radnik.pozicija,
            this.radnik.status, this.radnik.datum_zaposlenja);
        }
      })
    }

  }

  insert(registerForm: NgForm) {
    if (!this.pronadjiClicked) {
      if (registerForm.status !== 'INVALID') {
        this.radnik = new RadnikRequest(
          registerForm.value.jmbg,
          registerForm.value.ime_prezime,
          registerForm.value.koeficijent,
          registerForm.value.status,
          registerForm.value.pozicija,
          registerForm.value.datum_zaposlenja);

        this.service.insert(this.radnik).subscribe(res => {
          if (res) {
            this.ocisti();
            this.openDialog(false, 'Uspešno!', 'Uspešno ste dodali radnika!');
          }
        });
        this.pronadjiClicked = false;
      } else {
        this.openDialog(true, 'Greška!', 'Forma nije validna!');
      }
    }
  }

  update(registerForm: NgForm) {
    this.radnik = new RadnikUpdateRequest(
      registerForm.value.jmbg,
      registerForm.value.ime_prezime,
      registerForm.value.koeficijent,
      registerForm.value.pozicija,
      registerForm.value.status,
      registerForm.value.datum_zaposlenja);

    if (registerForm.status !== 'INVALID') {
      this.service.update(registerForm.value.sifra, this.radnik).subscribe(res => {
        if (res) {
          this.openDialog(false, 'Uspešno!', 'Uspešno ste izmenili radnika!');
        }
      });
      this.ocisti();
    } else {
      this.openDialog(true, 'Greška!', 'Forma nije validna!');
    }
  }

  openDialog(isError: boolean, dialogTitle: string, dialogText: string): void {
    this.dialog.open(ModalComponent, {
      width: '500px',
      data: {
        isError: isError,
        dialogTitle: dialogTitle,
        dialogText: dialogText,
      },
    });
  }
}
