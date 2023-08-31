import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-view0-hashtags',
  templateUrl: './view0-hashtags.component.html',
  styleUrls: ['./view0-hashtags.component.scss']
})
export class View0HashtagsComponent implements OnInit{
  timeArray: string[] = ['5', '15', '30', '45', '60'];
  timeForm!: FormGroup;

  constructor(private fb: FormBuilder){ }

  ngOnInit(): void {
    this.timeForm = this.fb.group({
      minutes: this.fb.control(this.timeArray[0])
    });
  }


}
