package com.leverx.dealerstat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class DealerStatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealerStatApplication.class, args);

    }

}


// check optional in service
// check baseDto
// can i use pathvariable in not get requests
// slf4j
// cascade type
// why can't i extend my dtos from baseDTO
// check modelmapper
// how to understand fields in the dto's
// what's the difference between repository getById and findById