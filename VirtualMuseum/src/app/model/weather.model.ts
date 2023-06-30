export class Weather {
    public temp: string;
    public feelsLike: string;
    public dateTime: string;
    public city: string;
    public icon:string;
 
     constructor(temp: string, feelsLike: string, dateTime: string, city: string,icon:string) {
         this.temp = temp;
         this.feelsLike = feelsLike;
         this.dateTime = dateTime;
         this.city = city;
         this.icon=icon;
     }
}
