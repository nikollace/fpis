import { Dobavljac } from "./dobavljac";
import { StavkaNarudzbenice } from "./stavka_narudzbenice";

export class Narudzbenica {
    constructor(
        private sifra: number,
        private dobavljac: Dobavljac,
        private datum: Date,
        private napomena: string,
        private stavke: [StavkaNarudzbenice]) {
    }
}