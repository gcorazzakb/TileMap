import {Component, Input, OnInit, OnChanges} from '@angular/core';
import {RESTService} from '../rest.service';
import {Util} from '../Util';

@Component({
  selector: 'app-show-tile',
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css']
})
export class ShowTileComponent implements OnInit, OnChanges {

  @Input() tileID: number;

  img: string | ArrayBuffer;
  block: boolean[][];

  constructor(private rest: RESTService) { }


  ngOnInit() {
    this.getImg();
  }

  ngOnChanges() {
    this.getImg();
  }

  getImg() {
    this.rest.getTileImg(this.tileID).subscribe(data => {
      const fileReader = Util.createImageFromBlob(data, () => {
        this.img = fileReader.result;
      });
    }, error => {
      console.log(error);
    });
  }
}
