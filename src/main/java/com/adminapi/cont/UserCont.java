package com.adminapi.cont;

import com.adminapi.dto.UserLogin;
import com.adminapi.dto.UserDto;
import com.adminapi.dto.PlanDto;
import com.adminapi.model.User;
import com.adminapi.model.Plan;
import com.adminapi.service.UserDetailsServiceImpl;
import com.adminapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserCont {

    @Autowired
    private UserService service;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/emp/add")
    public ResponseEntity<String> addEmployee(@RequestBody UserDto userDto){

        log.info("adding employee");
        return ResponseEntity.ok(service.addEmployee(userDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/emps")
   public ResponseEntity<List<UserDto>> getAllEmp(){
    log.info("Getting all employees");
        return ResponseEntity.ok(service.getAllEmployee());
   }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/emp/delete/{id}")
   public void deleteEmp(@PathVariable Integer id){
        log.info("deleting employee by id");
        service.deteleEmployee(id);
   }

   @PutMapping("/emp/state/{id}")
   public void changeStateOfEmp(@PathVariable Integer id) throws Throwable {
        log.info("Changing state of employee");
       service.DeActiveEmployee(id);
   }

   @PreAuthorize("hasRole('ADMIN' || 'EMPLOYEE')")
   @PutMapping("/emp/update/{id}")
   public ResponseEntity<String> updateEmp(@RequestBody UserDto userDto, @PathVariable Integer id) throws Throwable {
       log.info("Update the User");
        return ResponseEntity.ok(service.updateEmployee(userDto,id));
   }
   @GetMapping("/emp/search")
   public ResponseEntity<User> searchEmp(@RequestParam("str") String str){
        log.info("Search employee");
        return  ResponseEntity.ok(service.searchEmployee(str));
   }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/plan/add")
    public ResponseEntity<String> addPlan(@RequestBody PlanDto planDto){

        log.info("adding new Plan");
        return ResponseEntity.ok(service.addPlan(planDto));
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanDto>> getAllPlans(){
        log.info("Getting all Plans");
        return ResponseEntity.ok(service.getAllPlans());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/plan/delete/{id}")
    public void deletePlan(@PathVariable Integer id){
        log.info("deleting Plan by id");
        service.deletePlan(id);
    }

    @PutMapping("/plan/state/{id}")
    public void changeStateOfPlan(@PathVariable Integer id) throws Throwable {
        log.info("Changing state of Plan");
        service.DeActivePlan(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/plan/update/{id}")
    public ResponseEntity<String> updatePlan(@RequestBody PlanDto planDto,@PathVariable Integer id) throws Throwable {
        log.info("Update the Plan");
        return ResponseEntity.ok(service.updatePlan(planDto,id));
    }

    @GetMapping("/plan/search")
    public ResponseEntity<Plan> searchPlan(@RequestParam("str") String str){
        log.info("Search employee");
        return  ResponseEntity.ok(service.searchPlan(str));
    }














}


