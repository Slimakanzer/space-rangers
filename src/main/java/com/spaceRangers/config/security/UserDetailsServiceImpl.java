package com.spaceRangers.config.security;

import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.service.RegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);


    @Resource
    private RegistrationService registrationService;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserAccountEntity userAccount = registrationService.getUserAccount(login);

        if(userAccount==null){
            log.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new User(userAccount.getLogin(), userAccount.getPassword(), getGrantedAuthorities(userAccount));
    }


    private List<GrantedAuthority> getGrantedAuthorities(UserAccountEntity userAccount){

        log.info("User was found " + userAccount.getLogin());

        List authorities = new ArrayList<GrantedAuthority>();


        registrationService.getUserGroupAuthority(userAccount)
                .forEach(
                        e->{
                            log.info(e.getName());
                            authorities.add(new SimpleGrantedAuthority(e.getName()));
                        }
                );

        log.info("Count of authorities of user:"+authorities.size());

        return authorities;

    }
}
