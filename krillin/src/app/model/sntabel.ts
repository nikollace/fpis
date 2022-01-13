import { Materijal } from "./materijal";
import { Narudzbenica } from "./narudzbenica";

export class StavkaNarudzbeniceTable {
    constructor(
        private narudzbenica: number,
        private redni_broj: number,
        private kolicina: number, 
        private jm: string, 
        private materijal: Materijal,
        private tabela: boolean) {
    }
}