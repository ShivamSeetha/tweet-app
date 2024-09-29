import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../interfaces/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  isEmailUnique: boolean = true;
  isUsernameUnique: boolean = true;
  firstName: any;
  lastName: any;
  contactNumber: any;
  username: any;
  password: any;
  email: any;
  registerForm: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private snack: MatSnackBar,
    private formBuilder: FormBuilder
  ) {
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required, Validators.pattern('[A-ZA-z]{1,}')]),
      lastName: new FormControl('', [Validators.required, Validators.pattern('[A-ZA-z]{1,}')]),
      username: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(10)]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      confirmPassword: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
      contactNumber: new FormControl('', [Validators.required, Validators.pattern('[7-9]{1}[0-9]{9}')]),
    });
  }
  get f() {
    return this.form.controls;
  }
  ngOnInit(): void { }
  
  register() {

    let user: User = {
      id: null,
      username: this.f.username.value,
      password: this.f.password.value,
      email: this.f.email.value,
      firstName: this.f.firstName.value,
      lastName: this.f.lastName.value,
      contactNumber: this.f.contactNumber.value,
    };
    // this.authService.onRegister(
    //   this.form.value.firstName,
    //   this.form.value.lastName,
    //   this.form.value.username,
    //   this.form.value.password,
    //   this.form.value.email,
    //   this.form.value.contactNumber
    // )
    this.authService.onRegister(user).subscribe(
      (data) => {
        console.log(data);
        this.snack.open('User - ' + this.form.value.username +
          ' is registered succesfully!!', "OK", { duration: 5000 });
        this.router.navigate(['login']);
      },
      (error) => {
        alert('Some error occured, please try later')
      });
  }
}

