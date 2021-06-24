package com.example.ownerlocksrental;

import com.example.ownerlocksrental.service.Implement.EnrollAdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwnerLocksRentalApplication {

    public static void main(String[] args)throws Exception {
        EnrollAdminService.admin();
        SpringApplication.run(OwnerLocksRentalApplication.class, args);
    }

}
