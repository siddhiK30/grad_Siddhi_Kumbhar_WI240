import { Routes } from '@angular/router';
import { Login } from './login/login';
import { AdminDash } from './admin-dash/admin-dash';
import { StaffDash } from './staff-dash/staff-dash';

export const routes: Routes = [
     {
    path: '',
    component: Login
  },

  {
    path: 'admin',
    component: AdminDash
  },

  {
    path: 'staff',
    component: StaffDash
  }
];
