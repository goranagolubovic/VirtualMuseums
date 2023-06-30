import { HttpClient } from '@angular/common/http';
import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  httpClient: HttpClient;

  constructor(httpClient: HttpClient) { this.httpClient = httpClient; }
  postLink(link:any,virtualPresentation:any,date:any,time:any,duration:any) {
    const endpoint = 'http://localhost:8080/admin/upload-video';
    const formData: FormData = new FormData();
    formData.append("link", link.value);
    formData.append('folderName', virtualPresentation.value)
    formData.append("date", date.value);
    formData.append("time", time.value);
    formData.append("duration", duration.value);
    this.httpClient
      .post(endpoint, formData)
      .subscribe(data => console.log(data))
  }
  postPicture(filesToUpload: FileList,virtualPresentation:any,date:any,time:any,duration:any) {
    const endpoint = 'http://localhost:8080/admin/upload-picture';
    const formData: FormData = new FormData();
    for (let i = 0; i < filesToUpload.length; i++) {
      const fileToUpload = filesToUpload[i]
      formData.append("files", fileToUpload, fileToUpload.name);
    } 
    formData.append('folderName', virtualPresentation.value)
    formData.append("date", date.value);
    formData.append("time", time.value);
    formData.append("duration", duration.value);

    this.httpClient
      .post(endpoint, formData)
      .subscribe(data => console.log(data))
  }
}
