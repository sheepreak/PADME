import { TestBed, inject } from '@angular/core/testing';

import { MedicalFileService } from './medical-file.service';

describe('MedicalFileService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MedicalFileService]
    });
  });

  it('should be created', inject([MedicalFileService], (service: MedicalFileService) => {
    expect(service).toBeTruthy();
  }));
});
