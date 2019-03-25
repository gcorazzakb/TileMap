import { Component, OnInit } from '@angular/core';
import {RESTService} from "../rest.service";
import {Util} from "../Util";

@Component({
  selector: 'app-map-array',
  templateUrl: './map-array.component.html',
  styleUrls: ['./map-array.component.css']
})
export class MapArrayComponent implements OnInit {
  private mapArray: Object;

  seed: number;

  constructor(private rest: RESTService ) {}

  ngOnInit() {
  }

  getMapArray(seed){
    this.rest.getMapArray(seed).subscribe(ma =>{
      console.log(ma);
      this.mapArray = ma; //is it good? I mean.. no how to make it better?
      this.getImg(0,0,3);
    });

  }

  getImg(x, y, z) {
    this.rest.getTileImg(this.mapArray[x][y][z].id).subscribe(data => {
      let fileReader = Util.createImageFromBlob(data, ()=>{
        this.mapArray[x][y][z].img=fileReader.result;
      });
    }, error => {
      console.log(error);
    });
  }
}
