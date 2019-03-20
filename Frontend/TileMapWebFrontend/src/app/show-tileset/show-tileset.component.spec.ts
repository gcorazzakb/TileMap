import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTilesetComponent } from './show-tileset.component';

describe('ShowTilesetComponent', () => {
  let component: ShowTilesetComponent;
  let fixture: ComponentFixture<ShowTilesetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowTilesetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowTilesetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
