import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Student {
   private name: string = "Guest"

  constructor() { }

  public setName(uname: string): void {
    this.name = uname
  }

  public getName(): string {
    return this.name
  }
   students:any[] = [
    {regNo:101, rollNo:12, name:"Rahul", standard:10, school:"ABC School"},
    {regNo:102, rollNo:15, name:"Neha", standard:9, school:"XYZ School"}
  ];

  getStudents(){
    return this.students;
  }

  addStudent(s:any){
    this.students.push(s);
  }
 editStudent(updatedStudent:any){

  for(let i = 0; i < this.students.length; i++){

    if(this.students[i].regNo == updatedStudent.regNo){

      this.students[i].rollNo = updatedStudent.rollNo;
      this.students[i].name = updatedStudent.name;
      this.students[i].standard = updatedStudent.standard;
      this.students[i].school = updatedStudent.school;

    }

  }

}

  deleteStudent(regNo:any){
    this.students = this.students.filter(s => s.regNo != regNo);
  }
}
