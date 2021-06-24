package com.example.ownerlocksrental.config.services;

import com.example.ownerlocksrental.dao.IOwnerDao;
import com.example.ownerlocksrental.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IOwnerDao ownerDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Owner user = ownerDao.findByEmail(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + id));

        return UserDetailsImpl.build(user);
    }

}