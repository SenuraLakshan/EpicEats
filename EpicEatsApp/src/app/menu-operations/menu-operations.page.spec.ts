import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MenuOperationsPage } from './menu-operations.page';

describe('MenuOperationsPage', () => {
  let component: MenuOperationsPage;
  let fixture: ComponentFixture<MenuOperationsPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuOperationsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
