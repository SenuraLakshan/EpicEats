import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-restaurant-search',
  templateUrl: './restaurant-search.page.html',
  styleUrls: ['./restaurant-search.page.scss'],
})
export class RestaurantSearchPage implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,) {
  }

  ngOnInit() {
  }

  async logout() {
    await this.authService.logout();
    this.router.navigateByUrl('/', {replaceUrl: true});
  }

}
