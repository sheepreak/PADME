import { TestBed, inject } from '@angular/core/testing';

import { MedicalDocService } from './medical-doc.service';

describe('MedicalDocService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MedicalDocService]
    });
  });

  it('should be created', inject([MedicalDocService], (service: MedicalDocService) => {
    expect(service).toBeTruthy();
  }));
});
