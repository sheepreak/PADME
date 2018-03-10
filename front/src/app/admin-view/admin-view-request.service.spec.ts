import { TestBed, inject } from '@angular/core/testing';

import { AdminViewRequestService } from './admin-view-request.service';

describe('AdminViewRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminViewRequestService]
    });
  });

  it('should be created', inject([AdminViewRequestService], (service: AdminViewRequestService) => {
    expect(service).toBeTruthy();
  }));
});
