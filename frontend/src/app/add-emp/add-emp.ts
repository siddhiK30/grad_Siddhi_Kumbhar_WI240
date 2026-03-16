import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Emp } from '../services/employee';
import { Employee } from '../employee.model';

@Component({
  selector: 'app-add-emp',
  standalone : false,
  templateUrl: './add-emp.html',
  styleUrls: ['./add-emp.css'],
})
export class AddEmp {

  employee: Employee = new Employee();

  constructor(public es: Emp) {}

  onSubmit(empForm: NgForm) {

    console.log(this.employee);
   

    this.es.addEmployee(this.employee);

  }
}