package com.redcode.demo.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend implements Serializable {

    private static final long serialVersionUID = -7934280156780093548L;

    private String name;

    private String sex;

    private double height;

    private int facepower;

    private int nature;

    private double salary;
}
