import { TestBed, inject } from '@angular/core/testing';

import { PatientListServiceService } from './patient-list-service.service';

describe('PatientListServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PatientListServiceService]
    });
  });

  it('should be created', inject([PatientListServiceService], (service: PatientListServiceService) => {
    expect(service).toBeTruthy();
  }));
});
