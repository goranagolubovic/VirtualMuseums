import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FileUploadService } from './file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  fileToUpload: FileList | null = null;
  fileUploadService: any;
  constructor(private dialog:MatDialog,fileUploadService: FileUploadService) { this.fileUploadService = fileUploadService; }

  ngOnInit(): void {
  }
  public close(){
    this.dialog.closeAll();
  }
  handlePictureInput(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = (target.files as FileList)
    if(files.length<5 || files.length>10) {
      alert("Please choose between 5 and 10 pictures!")
      return
    }
    this.fileToUpload = files
  }
  handleVideoInput(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = (target.files as FileList)
    if(files.length>1 && files.length<0) {
      alert("Please choose 1 video!")
      return
    }
    this.fileToUpload = files
  }
  uploadPictureToActivity(virtualPresentation:any,date:any,time:any,duration:any) {
    this.fileUploadService.postPicture(this.fileToUpload,virtualPresentation,date,time,duration);
    alert("Pictures are uploaded!")
  }
  uploadLinkToActivity(link:any,virtualPresentation:any,date:any,time:any,duration:any) {
    this.fileUploadService.postLink(link,virtualPresentation,date,time,duration);
    alert("Youtube link is uploaded!")
  }

}
