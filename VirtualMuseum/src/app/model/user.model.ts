export class User {
   public ime: string;
   public prezime: string;
   public  korisnickoIme: string;
   public lozinka: string;

   public email: string;
   public id: number | undefined;
   public isAdmin:number;
   public isActive:number;
   public isApproved:number;
   public token:string;

    constructor(ime: string, prezime: string, korisnickoIme: string, lozinka: string, email:string,isAdmin:number,isActive:number,isApproved:number,token:string) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email=email;
        this.isAdmin=isAdmin;
        this.isActive=isActive;
        this.isApproved=isApproved;
        this.token=token;
    }
}