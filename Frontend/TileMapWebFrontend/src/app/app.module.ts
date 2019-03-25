import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestButtonComponent } from './rest-button/rest-button.component';
import { HttpClientModule }    from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import { ImageComponentComponent } from './image-component/image-component.component';
import { ShowTilesetComponent } from './show-tileset/show-tileset.component';
import { MapArrayComponent } from './map-array/map-array.component';
import { LayeredTileComponent } from './layered-tile/layered-tile.component';

@NgModule({
  declarations: [
    AppComponent,
    RestButtonComponent,
    ImageComponentComponent,
    ShowTilesetComponent,
    MapArrayComponent,
    LayeredTileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
