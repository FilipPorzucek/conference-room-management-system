import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext'; 
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-login',
  imports: [CommonModule, 
    FormsModule,     
    InputTextModule, 
    ButtonModule, 
    CardModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string="";
  password: string="";
  error:string="";

  constructor(private authService:AuthService,private router:Router){}

  onLogin():void{
    this.authService.login(this.email, this.password).subscribe({
      next: ()=>{
        this.router.navigate(["/demo"])
      },
       error: (err) => {
      this.error = err.message;
      this.error = "Błąd logowania. Sprawdź dane.";
    }
    })
  }

}
