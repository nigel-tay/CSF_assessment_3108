import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view2-postnews',
  templateUrl: './view2-postnews.component.html',
  styleUrls: ['./view2-postnews.component.scss']
})
export class View2PostnewsComponent implements OnInit{

  @ViewChild('imageInput') imageInput!: ElementRef;

  articleForm!: FormGroup;
  tagsArray: string[] = [];

  constructor(private fb: FormBuilder, private router: Router) {}


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

    console.log(formData.forEach(v => console.log(v)));
    // firstVaulueFrom(
    //   this.http.post('http://localhost:8080/upload', formData)
    //   ).then(() => {  })
    //   .catch((error) => {  })
  }

  goToView0() {
    this.router.navigate(['/']);
  }

  addTags() {
    this.tagsArray = [... new Set(this.articleForm.value.tags.trim().split(' '))] as string[];
  }
}
