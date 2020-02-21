package com.gcart.employee.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcart.employee.entity.Employee;
import com.gcart.employee.service.EmployeeService;
 

@RestController("/api/v1")
public class EmployeeRestController {
  
 @Autowired
 private EmployeeService employeeService;
  
 public void setEmployeeService(EmployeeService employeeService) {
  this.employeeService = employeeService;
 }
 
 @GetMapping("/employees")
 public List<Employee> getEmployees() {
  List<Employee> employees = employeeService.retrieveEmployees();
  return employees;
 }
  
 @GetMapping("/employees/{employeeId}")
 public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
  return employeeService.getEmployee(employeeId);
 }

 @ApiResponses(
         value = {
                 @ApiResponse(
                         responseCode = "404",
                         description = "Missing description",
                         content = @Content(mediaType = "application/json")),
                 @ApiResponse(
                         responseCode = "200",
                         description = "Employee created successfully",
                         content = @Content(mediaType = "application/json",
                                 schema = @Schema(implementation = Employee.class))) })
 @Operation(summary = "Create Employee",
         description = "Create Employee")
 @PostMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE )
 public void saveEmployee(@RequestBody Employee employee){
  employeeService.saveEmployee(employee);
  System.out.println("Employee Saved Successfully");
 }
  
 @DeleteMapping("/employees/{employeeId}")
 public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
  employeeService.deleteEmployee(employeeId);
  System.out.println("Employee Deleted Successfully");
 }
  
 @PutMapping("/employees/{employeeId}")
 public void updateEmployee(@RequestBody Employee employee,
   @PathVariable(name="employeeId")Long employeeId){
  Employee emp = employeeService.getEmployee(employeeId);
  if(emp != null){
   employeeService.updateEmployee(employee);
  }
   
 }
 
}