export class News {
    public title: string;
    public link: string;
    public description: string;
    public pubDate: string;
    public image:string;
 
 
     constructor(title: string, link: string, description: string, date: string,image:string) {
         this.title = title;
         this.link = link;
         this.description = description;
         this.pubDate = date;
         this.image=image;
     }
}
