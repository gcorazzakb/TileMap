import {Component, OnInit} from '@angular/core';
import {RESTService} from "../rest.service";

@Component({
  selector: 'app-show-tileset',
  templateUrl: './show-tileset.component.html',
  styleUrls: ['./show-tileset.component.css']
})
export class ShowTilesetComponent implements OnInit {
  names:String[];

  constructor(private rest: RESTService) {
  }

  ngOnInit() {
    this.rest.getTileSetNames().subscribe(names => {
      let parse = JSON.parse(JSON.stringify(names)).names;
      for  (let name in parse){
        this.names.push(name);
      }
    });
  }

}
