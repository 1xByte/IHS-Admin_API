package com.adminapi.service;

import com.adminapi.dto.UserUnlockDto;
import com.adminapi.dto.UserDto;
import com.adminapi.dto.PlanDto;
import com.adminapi.model.User;
import com.adminapi.model.Plan;

import java.util.List;

public interface UserService {

    User showAdminDashboard(Integer adminId);

    // Employee Functionality..

    String addEmployee(UserDto userDto);

    Boolean unlockEmployeeAccount(UserUnlockDto userUnlockDto);

    List<PlanDto> showEmployeeDashboard(Integer employeeId) throws Throwable;

    List<UserDto> getAllEmployee();

    void deteleEmployee(Integer employeeId);

    void DeActiveEmployee(Integer employeeId) throws Throwable;

    UserDto getUserByEmployeeId(Integer employeeId) throws Throwable;

    String updateEmployee(UserDto userDto, Integer employeeId) throws  Throwable;

    String forgotEmployeePassword(String employeeEmail);

    User searchEmployee(String str);
    

   // Plan Functionality..

    String addPlan(PlanDto planDto);

    List<PlanDto> getAllPlans();

    void deletePlan(Integer planId);

    void DeActivePlan(Integer planId);

    PlanDto getPlanByPlanId(Integer planId);

    String updatePlan(PlanDto planDto,Integer planId);

    Plan searchPlan(String str);
}
