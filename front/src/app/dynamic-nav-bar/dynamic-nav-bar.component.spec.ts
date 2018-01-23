import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicNavBarComponent } from './dynamic-nav-bar.component';

describe('DynamicNavBarComponent', () => {
  let component: DynamicNavBarComponent;
  let fixture: ComponentFixture<DynamicNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DynamicNavBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
