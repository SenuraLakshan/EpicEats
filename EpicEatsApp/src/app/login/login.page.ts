import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AlertController, LoadingController} from '@ionic/angular';
import {AuthService} from '../services/auth.service';
import {doc, Firestore, getDoc, setDoc} from '@angular/fire/firestore';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  credentials!:FormGroup;
  formData =new FormGroup({
    fName: new FormControl(),
    lName: new FormControl(),
    location: new FormControl(),
    restaurantName: new FormControl(),
  });
  public selectedSegment = 'login';
  public isARestaurantOwner = false;

  constructor(
    private fb: FormBuilder,
    private loadingController: LoadingController,
    private alertController: AlertController,
    private authService: AuthService,
    private router: Router,
    private firestore: Firestore,
  ) {
  }

  ngOnInit() {
    this.credentials = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get email() {
    return this.credentials.get('email');
  }

  get password() {
    return this.credentials.get('password');
  }

  async register() {
    const loading = await this.loadingController.create();
    await loading.present();

    const user = await this.authService.register(this.credentials.value);
    await loading.dismiss();

    if (user) {
      const userDocRef = doc(this.firestore, `users/${user.user.uid}`);
      await setDoc(userDocRef, {
        fName: this.formData.value.fName,
        lName: this.formData.value.lName,
        email: user.user.email,
        isRestaurantOwner:this.isARestaurantOwner});
      if(this.isARestaurantOwner){
        const restaurantDocRef = doc(this.firestore, `restaurants/${user.user.uid}`);
        await setDoc(restaurantDocRef, {
          name: this.formData.value.restaurantName,
          location: this.formData.value.location,
          id:user.user.uid
        });
        this.router.navigateByUrl('/menu-operations', {replaceUrl: true});
      }else{
        const customerDocRef = doc(this.firestore, `customers/${user.user.uid}`);
        await setDoc(customerDocRef, {
          fName: this.formData.value.fName,
          lName: this.formData.value.lName,
          email: user.user.email
        });
        this.router.navigateByUrl('/restaurant-search', {replaceUrl: true});
      }

    } else {
      this.showAlert('Registration failed', 'Please try again!');
    }
  }

  async login() {
    const loading = await this.loadingController.create();
    await loading.present();

    const user = await this.authService.login(this.credentials.value);
    await loading.dismiss();

    if (user) {
      const getDocRef =doc(this.firestore, `users/${user.user.uid}`);
      const dbDoc = await getDoc(getDocRef);
      if(dbDoc.get('isRestaurantOwner')){
        this.router.navigateByUrl('/menu-operations', {replaceUrl: true});
      }else{
        this.router.navigateByUrl('/restaurant-search', {replaceUrl: true});
      }
    } else {
      this.showAlert('Login failed', 'Please try again!');
    }
  }

  async showAlert(header:string, message:string) {
    const alert = await this.alertController.create({
      header,
      message,
      buttons: ['OK'],
    });
    await alert.present();
  }

  segmentChanged(event: any) {
    this.selectedSegment = event.target.value;
  }

  isChecked(event: any) {
    this.isARestaurantOwner = !this.isARestaurantOwner;
  }
}

