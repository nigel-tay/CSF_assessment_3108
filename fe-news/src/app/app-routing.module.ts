import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View2PostnewsComponent } from './view2-postnews/view2-postnews.component';
import { View1NewslistComponent } from './view1-newslist/view1-newslist.component';
import { View0HashtagComponent } from './view0-hashtag/view0-hashtag.component';

const routes: Routes = [
  {path: "", component: View0HashtagComponent},
  {path: "view1", component: View1NewslistComponent},
  {path: "view2", component: View2PostnewsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
