import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamenFileComponent } from './examen-file.component';

describe('ExamenFileComponent', () => {
  let component: ExamenFileComponent;
  let fixture: ComponentFixture<ExamenFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamenFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamenFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
