package com.adminapi.service;

import com.adminapi.model.User;
import com.adminapi.repo.UserRepo;
import com.adminapi.utils.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

  User user= (User) userRepo.findByUserEmail(username).orElseThrow(()-> new UsernameNotFoundException(StringConstants.EMAIL_RES));

  if(StringConstants.LOCKED.equals(user.getStatus())  && StringConstants.ACTIVE.equals(user.getUserState())){

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(), user.getUserPwd(), getAuthorities(user.getRole().toString())
        );}
  return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
