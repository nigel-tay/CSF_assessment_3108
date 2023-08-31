import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(
    private http: HttpClient
  ) { }

  postArticleToSpringBoot(formData: FormData) {
    return this.http.post(
      'http://localhost:8080/receiveform',
      formData, 
      {responseType: 'json'});
  }
}
