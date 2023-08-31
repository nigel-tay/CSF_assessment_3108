import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view2-postnews',
  templateUrl: './view2-postnews.component.html',
  styleUrls: ['./view2-postnews.component.scss']
})
export class View2PostnewsComponent implements OnInit{

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
    console.log(this.articleForm.value);
  }

  goToView0() {
    this.router.navigate(['/']);
  }

  addTags() {
    this.tagsArray = [... new Set(this.articleForm.value.tags.trim().split(' '))] as string[];
  }
}
