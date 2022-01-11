import { Component, OnInit, Output } from '@angular/core';
import { NarudzbenicaService } from 'src/app/services/narudzbenica.service';
import { formatDate } from '@angular/common'
import { MatDialog } from '@angular/material/dialog';
import { ModalComponent } from '../modals/modal/modal.component';
import { Narudzbenica } from 'src/app/model/narudzbenica';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-narudzbenica',
  templateUrl: './narudzbenica.component.html',
  styleUrls: ['./narudzbenica.component.scss']
})
export class NarudzbenicaComponent {

  //model
  dobavljac: any;
  stavke: any;
  narudzbenica: any;

  clicked = false;
  novaClicked = true;
  otkazi2 = false;

  //podaci na formi
  sifra: any;
  napomena: any;
  datum: any;
  maxSifra: any;

  pib: any;
  nazivDobavljaca: any;

  dialogRef: any;

  //id-evi za brisanje
  selectedRowIds: Set<number> = new Set<number>();

  constructor(private service: NarudzbenicaService, private dialog: MatDialog) {
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

  confirmDialog() {
    this.dialogRef = this.dialog.open(DialogComponent)
  }

  ocisti() {
    this.napomena = null;
    this.datum = null;

    this.sifra = null;
    this.stavke = [];
    this.napomena = null;
    this.pib = null;
    this.nazivDobavljaca = null;
    this.datum = null;

    // this.novaClicked = false;
    this.otkazi2 = false;
  }

  getStavke(id: number) {
    this.service.getStavke(id).subscribe(res => {
      this.stavke = res;
      console.log(this.stavke)
    });
  }

  getDobavljac(pib: string) {
    this.service.getDobavljac(pib).subscribe(res => {
      this.dobavljac = res;
      this.nazivDobavljaca = this.dobavljac.naziv;
    }, error => {
      this.openDialog(true, 'Greška!', 'Dobavljač sa unetim pib-om ne postoji!')
    });
  }

  getNarudzbenica(id: number) {

    this.clicked = true;
    this.novaClicked = false;
    if (!this.sifra) {
      this.openDialog(true, 'Greška!', 'Unesite šifru!')
    } else {
      this.service.getNarudzbenica(id).subscribe(res => {
        this.narudzbenica = res;
        this.napomena = this.narudzbenica.napomena;
        this.datum = formatDate(new Date(this.narudzbenica.datum), "yyyy-MM-ddTHH:mm:ss", "en-US");
        console.log(this.narudzbenica)
        // dohvati stavke narudzbenice
        this.getStavke(id);
      },
        error => {
          this.openDialog(true, 'Greška!', 'Narudžbenica sa unetom šifrom ne postoji!')
          this.ocisti()
        });

      this.selectedRowIds = new Set<number>();
    }

  }

  getMaxSifra() {
    
    this.service.getMaxSifra().subscribe(res => {
      let max = Object.entries(res)[0];
      this.ocisti();
      this.sifra = max[1] + 1;

      this.stavke = [];
      this.clicked = true;
      // this.clicked = false;
      this.novaClicked = true;
    });

    this.selectedRowIds = new Set<number>();
  }

  sacuvajNarudzbenicu() {
    this.confirmDialog();
    console.log(this.novaClicked)
    this.dialogRef.afterClosed().subscribe((res: any) => {
      if (res) {
        //brisanje odabranih stavki
        this.service.obrisiStavku(this.selectedRowIds, this.sifra).subscribe(res => {
        });

        if (this.novaClicked) {
          const narudzbenica = new Narudzbenica(this.sifra, this.dobavljac, this.datum, this.napomena, this.stavke);
          this.service.saveNarudzbenica(narudzbenica).subscribe(res => {
            this.openDialog(false, 'Uspešno!', 'Uspešno ste dodali narudžbenicu!');
            this.ocisti();
          }, error => {
            this.openDialog(true, 'Greška!', 'Proverite podatke!');
          });
        } else {
          const narudzbenica = new Narudzbenica(this.sifra, this.dobavljac, this.datum, this.napomena, this.stavke);
          this.service.updateNarudzbenica(narudzbenica).subscribe(res => {
            this.openDialog(false, 'Uspešno!', 'Uspešno ste izmenili narudžbenicu!');
            this.ocisti();
          }, error => {
            this.openDialog(true, 'Greška!', 'Proverite podatke!');
          });
        }
      }
      this.dialogRef = null;
    });
  }

  otkazi() {
    this.confirmDialog();

    this.dialogRef.afterClosed().subscribe((res: any) => {
      if (res) {
        this.ocisti();
      }
      this.dialogRef = null;
    });
  }
}
