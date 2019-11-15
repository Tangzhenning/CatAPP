package com.demo.lucar.bean;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class WeightBean implements Serializable {
    @Id
    public long tid;

    public String imperial;
    public String metric;

}
