import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionFileComponent } from './prescription-file.component';

describe('PrescriptionFileComponent', () => {
  let component: PrescriptionFileComponent;
  let fixture: ComponentFixture<PrescriptionFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrescriptionFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrescriptionFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
