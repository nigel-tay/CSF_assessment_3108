import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Tag } from '../tag';
import { ArticleService } from '../article.service';

@Component({
  selector: 'app-view0-hashtags',
  templateUrl: './view0-hashtags.component.html',
  styleUrls: ['./view0-hashtags.component.scss']
})
export class View0HashtagsComponent implements OnInit{
  timeArray: string[] = ['5', '15', '30', '45', '60'];
  timeForm!: FormGroup;
  currentMinutes: string = '5';
  tagsWithCount!: string[];

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private aService: ArticleService
    ){ }

  ngOnInit(): void {
    this.timeForm = this.fb.group({
      minutes: this.fb.control(this.timeArray[0])
    });
    this.aService.getTagsWithCount(this.currentMinutes)
      .subscribe(v => {
        let jsonString = JSON.stringify(v)
        let jsonObject = JSON.parse(jsonString)
        for (let obj in jsonObject) {
          this.tagsWithCount.push(obj);
        }
      })
  }

  assignMinutes(timeValue: string) {
    this.currentMinutes = timeValue;
    this.aService.getTagsWithCount(this.currentMinutes)
        .subscribe(v => console.log(v));
  }

  getTags() {
    this.router.navigate(['/view2'])
  }

  goToTagArticles(tags: any) {
    this.router.navigate(['/view1', tags])
  }
}
