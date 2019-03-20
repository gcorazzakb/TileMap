import { Component, OnInit } from '@angular/core';
import {RESTService} from "../rest.service";

@Component({
  selector: 'app-image-component',
  templateUrl: './image-component.component.html',
  styleUrls: ['./image-component.component.css']
})
export class ImageComponentComponent implements OnInit {

  constructor(private rest: RESTService) { }

  ngOnInit() {
  }

  getImg() {

  }
}
