import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RestaurantSearchPage } from './restaurant-search.page';

const routes: Routes = [
  {
    path: '',
    component: RestaurantSearchPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RestaurantSearchPageRoutingModule {}
