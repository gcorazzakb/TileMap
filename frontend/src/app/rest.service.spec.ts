import { TestBed } from '@angular/core/testing';

import { RESTService } from './rest.service';
import {HttpClientModule} from '@angular/common/http';

describe('RESTService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
    HttpClientModule
  ]}));

  it('should be created', () => {
    const service: RESTService = TestBed.get(RESTService);
    expect(service).toBeTruthy();
  });
});
