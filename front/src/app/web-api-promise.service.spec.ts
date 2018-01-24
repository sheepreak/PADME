import { TestBed, inject } from '@angular/core/testing';

import { WebApiPromiseService } from './web-api-promise.service';

describe('WebApiPromiseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WebApiPromiseService]
    });
  });

  it('should be created', inject([WebApiPromiseService], (service: WebApiPromiseService) => {
    expect(service).toBeTruthy();
  }));
});
