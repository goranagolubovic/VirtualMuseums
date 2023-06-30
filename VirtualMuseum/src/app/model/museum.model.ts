export class Museum {
    public id: number;
    public naziv: string;
    public adresa: string;
    public  brojTelefona: string;
    public grad: string;
    public drzava: string;
    public geolokacija: string;
    public tipMuzejaId: string;
    public cijenaUlaznice:number;
 
     constructor(id:number,naziv: string, adresa: string, brojTelefona: string, grad: string, drzava: string, geolokacija: string, tipMuzejaId:string,cijenaUlaznice:number) {
         this.id=id;
        this.naziv = naziv;
         this.adresa = adresa;
         this.brojTelefona = brojTelefona;
         this.grad = grad;
         this.drzava=drzava;
         this.geolokacija=geolokacija;
         this.tipMuzejaId=tipMuzejaId;
         this.cijenaUlaznice=cijenaUlaznice;
     }
}
