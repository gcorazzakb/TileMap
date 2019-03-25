import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapArrayComponent } from './map-array.component';

describe('MapArrayComponent', () => {
  let component: MapArrayComponent;
  let fixture: ComponentFixture<MapArrayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapArrayComponent ]
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
