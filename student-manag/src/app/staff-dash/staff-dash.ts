import { Component } from '@angular/core';
import { Student } from '../services/student';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-staff-dash',
  standalone: true,
  imports: [CommonModule],
  
  templateUrl: './staff-dash.html',
  styleUrl: './staff-dash.css',
})
export class StaffDash {
  students:any[]=[];

  constructor(private ss:Student){
    this.students=this.ss.getStudents();
  }
}
