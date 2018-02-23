import { TestBed, inject } from '@angular/core/testing';

import { AdministrationRequestService } from './administration-request.service';

describe('AdministrationRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdministrationRequestService]
    });
  });

  it('should be created', inject([AdministrationRequestService], (service: AdministrationRequestService) => {
    expect(service).toBeTruthy();
  }));
});
