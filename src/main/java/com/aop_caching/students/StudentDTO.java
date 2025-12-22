package com.aop_caching.students;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    private String name;
    private String rollNo;
    private LocalDate localDate;
    private Boolean active;
    private String roles;
    private String email;

}
