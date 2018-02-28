import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDocComponent } from './user-doc.component';

describe('UserDocComponent', () => {
  let component: UserDocComponent;
  let fixture: ComponentFixture<UserDocComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDocComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
