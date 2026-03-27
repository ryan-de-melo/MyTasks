import { Component, Signal, signal } from '@angular/core';

@Component({
  selector: 'app-login-component',
  imports: [],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {
  loginForm: Signal<LoginRequest> = signal({ email: '', password: '' });
}

interface LoginRequest {
  email: string;
  password: string;
}
