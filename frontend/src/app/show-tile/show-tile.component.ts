import {Component, Input, OnInit} from '@angular/core';
import {RESTService} from "../rest.service";
import {Util} from "../Util";

@Component({
  selector: 'app-show-tile',
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css']
})
export class ShowTileComponent implements OnInit {

  @Input() tileID: number;

  img: any;
  block: boolean[][];

  constructor(private rest: RESTService) { }


  ngOnInit() {
    this.getImg();
  }

  getImg() {
    this.rest.getTileImg(this.tileID).subscribe(data => {
      let fileReader = Util.createImageFromBlob(data, () => {
        this.img = fileReader.result;
      });
    }, error => {
      console.log(error);
    })
  }
}