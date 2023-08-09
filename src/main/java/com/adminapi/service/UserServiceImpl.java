package com.adminapi.service;

import com.adminapi.customExe.NotFoundException;
import com.adminapi.dto.UserDto;
import com.adminapi.dto.PlanDto;
import com.adminapi.dto.UserUnlockDto;
import com.adminapi.model.User;
import com.adminapi.model.Plan;
import com.adminapi.repo.RoleRepo;
import com.adminapi.repo.UserRepo;
import com.adminapi.repo.PlanRepo;
import com.adminapi.utils.EmailUtils;
import com.adminapi.utils.PasswordUtils;
import com.adminapi.utils.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PlanRepo planRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private PasswordUtils passwordUtils;

    @Autowired
    private ModelMapper mapper;


    @Override
    public User showAdminDashboard(Integer adminId) {
        return null;
    }

    @Override
    public String addEmployee(UserDto userDto) {

        log.info("Adding Employee..");
            User user = User.builder()
                    .userId(Integer.valueOf(UUID.randomUUID().toString().substring(0, 4)))
                    .userName(userDto.getUserName())
                    .userEmail(userDto.getUserEmail())
                    .userPwd(passwordUtils.passwordGenerator())
                    .userPhoneNumber(userDto.getUserPhoneNumber())
                    .userState(StringConstants.ACTIVE)
                    .status(StringConstants.LOCKED)
                    .role(roleRepo.findById(2).get())
                    .build();

            userRepo.save(user);

        String to= userDto.getUserEmail();
        String sub="";

        StringBuffer buffer=new StringBuffer("");

        buffer.append("<h1>temp pass: " + user.getUserPwd() + "</h1>");
        buffer.append("<a href=\"http://localhost:7040/unlock?email=" + to + "\">Click Here To Unlock Account</a>");

        emailUtils.emailGenerator(to, sub, buffer.toString());

            return StringConstants.EMP_ADDED;
    }

    @Override
    public Boolean unlockEmployeeAccount(UserUnlockDto userUnlockDto) {

         User user = (User) userRepo.findByUserEmail(userUnlockDto.getEmpEmail()).orElseThrow(() -> new NotFoundException(StringConstants.EMAIL_RES));

         if (userUnlockDto.getTempPwd().equals(user.getUserPwd())){
             user.setUserPwd(userUnlockDto.getNewPass());
             user.setStatus(StringConstants.UNLOCKED);
             userRepo.save(user);
             return true;
         }

        return false;
    }

    @Override
    public List<PlanDto> showEmployeeDashboard(Integer employeeId) throws Throwable {

        User user = (User) userRepo.findByUserId(employeeId).orElseThrow(() -> new NotFoundException(StringConstants.EMAIL_RES));


        return null;
    }

    @Override
    public List<UserDto> getAllEmployee() {

        log.info("Getting all Employees");
        List<User> users = userRepo.findAll();

        if(users.isEmpty()){
            return Collections.emptyList() ;
        }

      List<UserDto> dtos=new ArrayList<>();

        for (User e: users) {

       UserDto userDto =  UserDto.builder()
                    .userName(e.getUserName())
                    .userEmail(e.getUserEmail())
                    .userPhoneNumber(e.getUserPhoneNumber())
                    .userState(e.getUserState())
                    .gender(e.getGender())
                    .ssn(e.getSsn())
                    .dob(e.getDob())
                    .build();

       dtos.add(userDto);

        }
        return dtos;
    }

    @Override
    public void deteleEmployee(Integer employeeId) {

        log.info("deleting EMPLOYEE BY id");
        userRepo.deleteById(String.valueOf(employeeId));

    }

    @Override
    public void DeActiveEmployee(Integer  employeeId) throws Throwable {

        log.info("Changing State of User by employeeId");

        User e= (User) userRepo.findByUserId(employeeId)
                            .orElseThrow(()-> new NotFoundException(StringConstants.EMP_NOT_FOUND));

        if(StringConstants.ACTIVE .equals(e.getUserState())){

            e.setUserState(StringConstants.INACTIVE);

        }
        else {
            e.setUserState(StringConstants.ACTIVE);
        }

        userRepo.save(e);
    }

    @Override
    public UserDto getUserByEmployeeId(Integer employeeId) throws Throwable {

      User user= (User) userRepo.findByUserId(employeeId).orElseThrow(()-> new NotFoundException(StringConstants.EMP_NOT_FOUND));

        return mapper.map(user,UserDto.class);
    }

    @Override
    public String updateEmployee(UserDto userDto, Integer empId) throws Throwable {

        log.info("Updating User based on employeeId");

       User user= (User) userRepo.findByUserId(Integer.valueOf(empId))
               .orElseThrow(()-> new NotFoundException(StringConstants.EMP_NOT_FOUND));

        userRepo.save(mapper.map(userDto,User.class));

        return StringConstants.UPDATED;
    }

    @Override
    public String forgotEmployeePassword(String employeeEmail) {
        userRepo.findByUserEmail(employeeEmail).orElseThrow(()-> new NotFoundException(StringConstants.EMAIL_RES));

        emailUtils.emailGenerator("","","");

        return "";
    }

    @Override
    public User searchEmployee(String str) {

        log.info("Sraching by emp name or email");
        return userRepo.getBySearch(str);

    }

    @Override
    public String addPlan(PlanDto planDto) {

        log.info("Adding New Plan");

        Plan plan = Plan.builder()
                .planId(Integer.valueOf(UUID.randomUUID().toString().substring(0, 3)))
                .planName(planDto.getPlanName())
                .planCatagory(planDto.getPlanCatagory())
                .planState(StringConstants.ACTIVE)
                .build();

        planRepo.save(plan);

        return StringConstants.PLAN_ADDED;

    }

    @Override
    public List<PlanDto> getAllPlans() {

        log.info("Getting all Plans");

        List<Plan> plans = planRepo.findAll();

        List<PlanDto> dtos= new ArrayList<>();

        for (Plan p: plans
             ) {
            PlanDto dto = PlanDto.builder()
                    .planName(p.getPlanName())
                    .startDate(p.getStartDate())
                    .endDate(p.getEndDate())
                    .planCatagory(p.getPlanCatagory())
                    .build();

            dtos.add(dto);
        }

        return  dtos;
    }

    @Override
    public void deletePlan(Integer planId) {

       Plan  plan= planRepo.findByPlanId(Integer.valueOf(planId))
                .orElseThrow(() -> new NotFoundException(StringConstants.PLAN_NOT_FOUND));

            planRepo.deleteById(String.valueOf(planId));
        }


    @Override
    public void DeActivePlan(Integer planId) {

        log.info("Plan state changing");

        Plan id = planRepo.findByPlanId(Integer.valueOf(planId))
                                        .orElseThrow(() -> new NotFoundException(StringConstants.PLAN_NOT_FOUND));

            if(StringConstants.ACTIVE .equals(id.getPlanState())){
                id.setPlanState(StringConstants.INACTIVE);
            }else {
                id.setPlanState(StringConstants.ACTIVE);
            }

            planRepo.save(id);
    }

    @Override
    public PlanDto getPlanByPlanId(Integer planId) {

     Plan plan=  planRepo.findByPlanId(planId).orElseThrow(()-> new NotFoundException(StringConstants.PLAN_NOT_FOUND));

        PlanDto dto = mapper.map(plan, PlanDto.class);
        return dto;
    }

    @Override
    public String updatePlan(PlanDto planDto, Integer planId) {
 Plan plan = planRepo.findByPlanId(planId)
                .orElseThrow(() -> new NotFoundException(StringConstants.PLAN_NOT_FOUND));

        plan.setPlanName(planDto.getPlanName());
        plan.setPlanCatagory(planDto.getPlanCatagory());
        planRepo.save(plan);

        return StringConstants.UPDATED;
    }

    @Override
    public Plan searchPlan(String str) {

       return planRepo.getBySearch(str);
    }


}
