import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../services/student';
import { Role } from '../services/role';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  constructor(
    private us: Student,
    private rs: Role,
    private router: Router
  ) {}
abc(event: any) {

  event.preventDefault();

  let uname = event.target.elements.uname.value
  let pwd = event.target.elements.pwd.value
  let role = event.target.elements.role.value

  if (uname == pwd) {

    this.us.setName(uname)
    this.rs.setRole(role)

    if (role == "ADMIN") {
      this.router.navigate(['admin'])
    } 
    else {
      this.router.navigate(['staff'])
    }

  }

}
}