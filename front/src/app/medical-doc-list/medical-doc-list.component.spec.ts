import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalDocListComponent } from './medical-doc-list.component';

describe('MedicalDocListComponent', () => {
  let component: MedicalDocListComponent;
  let fixture: ComponentFixture<MedicalDocListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalDocListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalDocListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
