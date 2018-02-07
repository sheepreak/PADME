import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationFileComponent } from './consultation-file.component';

describe('ConsultationFileComponent', () => {
  let component: ConsultationFileComponent;
  let fixture: ComponentFixture<ConsultationFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultationFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultationFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
