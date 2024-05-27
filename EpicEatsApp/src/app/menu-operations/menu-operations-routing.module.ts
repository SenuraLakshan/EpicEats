import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuOperationsPage } from './menu-operations.page';

const routes: Routes = [
  {
    path: '',
    component: MenuOperationsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuOperationsPageRoutingModule {}
