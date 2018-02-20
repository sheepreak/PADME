import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrationFileComponent } from './administration-file.component';

describe('AdministrationFileComponent', () => {
  let component: AdministrationFileComponent;
  let fixture: ComponentFixture<AdministrationFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrationFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrationFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
