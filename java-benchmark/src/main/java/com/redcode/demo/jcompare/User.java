package com.redcode.demo.jcompare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhiyu.zhou on 2017/8/17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private int age;

    private String address;
}
