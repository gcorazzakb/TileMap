import { Component, OnInit } from '@angular/core';
import {RESTService} from "../rest.service";

@Component({
  selector: 'app-rest-button',
  templateUrl: './rest-button.component.html',
  styleUrls: ['./rest-button.component.css']
})
export class RestButtonComponent implements OnInit {

  constructor(private rest: RESTService) { }

  upper: String;
  input: String;

  ngOnInit() {
  }

  toUpper(upper: String){
    this.rest.getUpperCase(upper).subscribe(up => this.upper = JSON.parse(JSON.stringify(up)).a);
  }


  getImg() {
    this.rest.getImg().subscribe(up => this.upper = JSON.parse(JSON.stringify(up)).a);
  }
}
