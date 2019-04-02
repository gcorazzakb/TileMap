import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapArrayComponent } from './map-array.component';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

describe('MapArrayComponent', () => {
  let component: MapArrayComponent;
  let fixture: ComponentFixture<MapArrayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapArrayComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      imports: [
        FormsModule,
        HttpClientModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapArrayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
