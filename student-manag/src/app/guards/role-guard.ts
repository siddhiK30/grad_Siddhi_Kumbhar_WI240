import { CanActivateFn } from '@angular/router';
import { Role } from '../services/role';
import { inject } from '@angular/core';
import { Student } from '../services/student';

export const roleGuard: CanActivateFn = (route, state) => {
   let us: Student = inject(Student)
  let rs: Role = inject(Role)

  if (us.getName() == "Guest")
    return false;
  else if (route.data[0] == rs.getRole() || route.data[1] == rs.getRole())
    return true;
  else
    return false;

};

