import { Injectable } from '@angular/core';
import { Employee } from '../employee.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class Emp {

  emp: Employee[] = [];

  constructor(public ht: HttpClient) {}

  addEmployee(e: Employee) {
    this.ht.post("http://localhost:8181/employees", e)
      .subscribe((res)=>{
        console.log(res);
      });
  }

  getEmployee(): void {
    this.ht.get<Employee[]>("http://localhost:8181/employees")
      .subscribe((res)=>{
        this.emp = res;
        console.log(this.emp);
        
      });
  }
}