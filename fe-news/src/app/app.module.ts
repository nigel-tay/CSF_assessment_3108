import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { View2PostnewsComponent } from './view2-postnews/view2-postnews.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { View0HashtagComponent } from './view0-hashtag/view0-hashtag.component';
import { View1NewslistComponent } from './view1-newslist/view1-newslist.component';

@NgModule({
  declarations: [
    AppComponent,
    View0HashtagComponent,
    View2PostnewsComponent,
    View1NewslistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
