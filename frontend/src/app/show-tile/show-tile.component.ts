import {Component, Input, OnInit, OnChanges} from '@angular/core';
import {RESTService} from '../rest.service';
import {Util} from '../Util';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-show-tile',
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css']
})
export class ShowTileComponent implements OnInit, OnChanges {

  @Input() tileID: number;

  img: string | ArrayBuffer;
  block: boolean[][];

  constructor(private rest: RESTService, private route: ActivatedRoute) { }


  ngOnInit() {
    if ( this.tileID != null ) {
      this.getImg();
    } else {
      this.route.params.subscribe(params => {
        this.tileID = params.id;
        console.log('Routing Mode', this.tileID);
        this.getImg();
      });
    }
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
