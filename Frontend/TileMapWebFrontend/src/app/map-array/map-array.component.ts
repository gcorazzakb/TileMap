import { Component, OnInit } from '@angular/core';
import {RESTService} from "../rest.service";
import {Util} from "../Util";

@Component({
  selector: 'app-map-array',
  templateUrl: './map-array.component.html',
  styleUrls: ['./map-array.component.css']
})
export class MapArrayComponent implements OnInit {
  private mapArray: any[][][];

  seed: number;

  constructor(private rest: RESTService ) {}

  ngOnInit() {
  }

  getMapArray(seed){
    this.rest.getMapArray(seed).subscribe(ma =>{
      console.log(ma);
      this.mapArray =<any> ma; //is it good? I mean.. no how to make it better?
      this.getImg(0,0,3);
    });
  }

  getImg(l, x, y) {
    this.rest.getTileImg(this.mapArray[l][x][y].id).subscribe(data => {
      let fileReader = Util.createImageFromBlob(data, ()=>{
        this.mapArray[l][x][y].img=fileReader.result;
      });
    }, error => {
      console.log(error);
    });
  }

  private drawMap() {
    var canvas = <HTMLCanvasElement>document.getElementById("mapCanvas");
    canvas.width=this.mapArray[0].length*16;
    canvas.height=this.mapArray[0][0].length*16;
    canvas.style.display= "block";

    let g = canvas.getContext("2d");
    for (let l=0; l<this.mapArray.length;l++){
      for (let x=0; x<this.mapArray[0].length;x++){
        for (let y=0; y<this.mapArray[0][0].length;y++){
          if (this.mapArray[l][x][y]!=null){
            /*this.getImg(this.mapArray[l][x][y].id,
              (img) => {console.log(img)} // g.drawImage(img, x*16, y *16 )
            );*/
            this.rest.getTileImg(14).subscribe(data => console.log(data));
          }
        }
      }
    }

  }
}
