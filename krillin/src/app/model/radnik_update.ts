export class RadnikUpdateRequest {
    constructor(
        private jmbg: string,
        private ime_prezime: string, 
        private koeficijent: number, 
        private pozicija: string, 
        private status: string,
        private datum_zaposlenja: Date, ) {
    }
}