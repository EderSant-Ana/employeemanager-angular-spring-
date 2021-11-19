import { HttpErrorResponse } from '@angular/common/http';
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
  public editEmployee: Employee;

  constructor(private employeeService: EmployeeService) { };

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees() {
    this.employeeService.getEmployeesList()
      .subscribe((data: Employee[]) => {
        this.employees = data;
      },
      (error: HttpErrorResponse) => {
        if(error.error.message == undefined){
          alert('backend is not up');
        }
      }
    );
  }

  public onAddEmployee(addForm: NgForm): void {
    document.getElementById('add-employee-form').click();
    this.employeeService.postEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        error.error.message != undefined? alert(error.error.message): alert('backend is not up');
        addForm.reset();
      }
    );
  }

  public onUpdateEmployee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    );
  }

  public onOpenModal(employee: Employee, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }

}

