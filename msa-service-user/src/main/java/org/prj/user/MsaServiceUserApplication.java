package org.prj.user;

import org.prj.core.entity.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsaServiceUserApplication {

    public static void main(String[] args) {

        Test test = new Test();
        SpringApplication.run(MsaServiceUserApplication.class, args);
    }

}
