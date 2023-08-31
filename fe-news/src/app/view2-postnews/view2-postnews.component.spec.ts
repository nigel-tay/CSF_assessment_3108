import { ComponentFixture, TestBed } from '@angular/core/testing';

import { View2PostnewsComponent } from './view2-postnews.component';

describe('View2PostnewsComponent', () => {
  let component: View2PostnewsComponent;
  let fixture: ComponentFixture<View2PostnewsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [View2PostnewsComponent]
    });
    fixture = TestBed.createComponent(View2PostnewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
