import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Role {
   private role: string = ""

  constructor() { }

  public setRole(uni: string): void {
    this.role = uni
  }

  public getRole(): string {
    return this.role
  }
}
