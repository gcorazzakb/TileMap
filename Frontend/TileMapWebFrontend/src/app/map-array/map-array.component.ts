import {Component, OnInit} from '@angular/core';
import {RESTService} from "../rest.service";
import {Util} from "../Util";
import {element} from "protractor";
import {ɵSharedStylesHost} from "@angular/platform-browser";


@Component({
  selector: 'app-map-array',
  templateUrl: './map-array.component.html',
  styleUrls: ['./map-array.component.css']
})
export class MapArrayComponent implements OnInit {
  private mapArray: any[][][];

  mapWidth: number;
  mapHeight: number;

  seed: number;
  zoom = 2;

  mapImg;

  showTile;


  constructor(private rest: RESTService) {
  }

  ngOnInit() {
  }

  loadMap(seed) {
    this.seed = seed;
    this.getMapImg();
    this.getMapArray();
  }

  getMapArray() {
    this.rest.getMapArray(this.seed).subscribe(ma => {
      console.log(ma);
      this.mapArray = <any>ma; //is it good? I mean.. no how to make it better?
      this.mapWidth= this.mapArray[0].length;
      this.mapHeight= this.mapArray[0][0].length;
      //this.getImg(0,0,3);
    });
  }

  getMapImg() {
    this.rest.getMapImg(this.seed).subscribe(data => {
      let fileReader = Util.createImageFromBlob(data, () => {
        this.mapImg = fileReader.result;
      });
    }, error => {
      console.log(error);
    })
  }

  getImg(l, x, y) {
    this.rest.getTileImg(this.mapArray[l][x][y].id).subscribe(data => {
      let fileReader = Util.createImageFromBlob(data, () => {
        this.mapArray[l][x][y].img = fileReader.result;
      });
    }, error => {
      console.log(error);
    });
  }

  clickOnTile(event: MouseEvent) {
    let id= +(<Element>event.target).id;
    let x = id % this.mapWidth;
    let y = Math.floor(id / this.mapWidth);

    console.log(x+"!"+y);
    this.showTile.x=x;
    this.showTile.y=y;
  }

  zoomIn() {
    this.zoom++;
    if (this.zoom>2){
      this.zoom=2;
    }
    this.setZoom();
  }

  zoomOut() {
    this.zoom--;
    if (this.zoom<0){
      this.zoom=0;
    }
    this.setZoom();
  }

  setZoom(){
    let element = document.getElementById("zoomDiv");
    element.classList.forEach(value => element.classList.remove(value));
    if (this.zoom==0){
      element.classList.add("scaled1");
    }else if(this.zoom==1){
      element.classList.add("scaled2");
    }else{
      element.classList.add("scaled3");
    }
  }
}
