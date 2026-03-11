import { Component } from '@angular/core';
import { Student } from '../services/student';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dash',
  imports: [CommonModule],
  templateUrl: './admin-dash.html',
  styleUrl: './admin-dash.css',
})
export class AdminDash {

  students:any[]=[];
  editing:boolean=false;

  constructor(private ss:Student){
    this.students=this.ss.getStudents();
  }

  saveStudent(event:any){

    event.preventDefault();

    let regNo=event.target.elements[0].value;
    let rollNo=event.target.elements[1].value;
    let name=event.target.elements[2].value;
    let standard=event.target.elements[3].value;
    let school=event.target.elements[4].value;

    let student={
      regNo:regNo,
      rollNo:rollNo,
      name:name,
      standard:standard,
      school:school
    }

    if(this.editing){
      this.ss.editStudent(student);
      this.editing=false;
    }
    else{
      this.ss.addStudent(student);
    }

    event.target.reset();
  }

  editStudent(s:any){

    this.editing=true;

    let form = document.querySelector("form") as any;

    form.elements[0].value = s.regNo;
    form.elements[1].value = s.rollNo;
    form.elements[2].value = s.name;
    form.elements[3].value = s.standard;
    form.elements[4].value = s.school;

  }

  deleteStudent(regNo:any){
    this.ss.deleteStudent(regNo);
    this.students=this.ss.getStudents();
  }

}