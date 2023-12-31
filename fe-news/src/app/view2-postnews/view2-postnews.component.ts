import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ArticleService } from '../article.service';

@Component({
  selector: 'app-view2-postnews',
  templateUrl: './view2-postnews.component.html',
  styleUrls: ['./view2-postnews.component.scss']
})
export class View2PostnewsComponent implements OnInit{

  @ViewChild('imageInput') imageInput!: ElementRef;

  articleForm!: FormGroup;
  tagsArray: string[] = [];
  newsId: string = "";

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private aService: ArticleService
    ) {}


  ngOnInit(): void {
    this.articleForm = this.fb.group({
      title: this.fb.control<string>('', [Validators.required, Validators.minLength(5)]),
      photo: this.fb.control(null, [Validators.required]),
      description: this.fb.control<string>('', [Validators.required, Validators.minLength(5)]),
      tags: this.fb.control<string>('')
    })
  }

  handleArticleSubmit() {
    this.articleForm.value.tags = [...this.tagsArray];
    const formData = new FormData();
    formData.set('title', this.articleForm.value.title);
    formData.set('photo', this.imageInput.nativeElement.files[0]);
    formData.set('description', this.articleForm.value.description);
    formData.set('tags', this.articleForm.value.tags);

    this.aService.postArticleToSpringBoot(formData)
    .subscribe(data => {
      let jsonObj = JSON.stringify(data);
      if (JSON.parse(jsonObj)['newsId'].length <= 0 ) {
        alert(`You've got an error: ${JSON.parse(jsonObj)['error']}`)
      }
      else {
        alert(`This is the objectId from the article you've just posted ${JSON.parse(jsonObj)['newsId']}`);
        this.router.navigate(['/']);
      }

    });
  }

  goToView0() {
    this.router.navigate(['/']);
  }

  addTags() {
    this.tagsArray = [... new Set(this.articleForm.value.tags.trim().split(' '))] as string[];
  }

  removeTag(tag: string) {
    this.tagsArray = [...this.tagsArray.filter(ele => ele !== tag)]
  }
}
