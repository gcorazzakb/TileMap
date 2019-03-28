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
import { DragDropModule } from "@angular/cdk/drag-drop";
import { ShowMapComponent } from './show-map/show-map.component';
import { ShowTileComponent } from './show-tile/show-tile.component';
import { ShowTilComponent } from './show-til/show-til.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    RestButtonComponent,
    ImageComponentComponent,
    ShowTilesetComponent,
    MapArrayComponent,
    LayeredTileComponent,
    ShowMapComponent,
    ShowTileComponent,
    ShowTilComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DragDropModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
