import { EmployeeService } from './employee.service';
import { Employee } from './employee';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'employeemanagerapp';

  public employees: Employee[];

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees() {
    this.employeeService.getEmployeesList()
      .subscribe((data : Employee[]) =>{
        this.employees = data;
      });
    }

}

