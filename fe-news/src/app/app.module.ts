import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { View0HashtagsComponent } from './view0-hashtags/view0-hashtags.component';
import { View1NewslistComponent } from './view1-newslist/view1-newslist.component';
import { View2PostnewsComponent } from './view2-postnews/view2-postnews.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    View0HashtagsComponent,
    View2PostnewsComponent,
    View1NewslistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
