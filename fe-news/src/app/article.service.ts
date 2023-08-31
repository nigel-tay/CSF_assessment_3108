import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(
    private http: HttpClient
  ) { }

  postArticleToSpringBoot(formData: FormData) {
    return this.http.post<string>(
      'http://localhost:8080/receiveform',
      formData, 
      {responseType: 'json'});
  }

  getTagsWithCount(selectedMinute: string) {
    let params = new HttpParams().set('minute', selectedMinute);
    return this.http.get(
      'http://localhost:8080/gettagswithcount',
      {params: params}
    )
  }
}
