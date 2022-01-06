import { Materijal } from "./materijal";
import { Narudzbenica } from "./narudzbenica";

export class StavkaNarudzbeniceRequest {
    constructor(
        private redni_broj: number,
        private kolicina: number, 
        private jm: string, 
        private materijal: Materijal) {
    }
}