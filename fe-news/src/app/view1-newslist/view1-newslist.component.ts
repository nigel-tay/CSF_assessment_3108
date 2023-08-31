import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../article.service';
import { Article } from '../article';

@Component({
  selector: 'app-view1-newslist',
  templateUrl: './view1-newslist.component.html',
  styleUrls: ['./view1-newslist.component.scss']
})
export class View1NewslistComponent implements OnInit{

  tagParam: string = "";
  articleList: Article[] = [{
    title: "Dummy title",
    photo: "https://fredbarney.sgp1.cdn.digitaloceanspaces.com/aaa5d64c",
    description: "Dummy description",
    tags: ['fun', 'exciting']
  }];

  constructor( 
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private aService: ArticleService
    ){ }

  ngOnInit(): void {
    this.tagParam = this.activatedRoute.snapshot.params['minutes']
  }

  backtoViewZero() {
    this.router.navigate(['/'])
  }

  goToView2() {
    this.router.navigate(['/view2'])
  }
}
