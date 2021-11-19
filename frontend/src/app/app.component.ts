import { EmployeeService } from './employee.service';
import { Employee } from './employee';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  //title = 'employeemanagerapp';

  public employees: Employee[];

  constructor(private employeeService: EmployeeService) { };

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees() {
    this.employeeService.getEmployeesList()
      .subscribe((data: Employee[]) => {
        this.employees = data;
      });
  }

  public onOpenModal(employee: Employee, mode: string): void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'add'){
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if(mode === 'edit'){
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if(mode === 'delete'){
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }

}

