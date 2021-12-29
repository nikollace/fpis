export class RadnikRequest {
    constructor(private jmbg: string, private ime_prezime: string, private koeficijent: number, private status: string,
        private pozicija: string,
        private datum_zaposlenja: Date) {
    }
}