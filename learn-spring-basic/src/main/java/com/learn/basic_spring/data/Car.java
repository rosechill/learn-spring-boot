package com.learn.basic_spring.data;

import com.learn.basic_spring.aware.IdAware;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Car implements IdAware {

    @Getter
    private String id;


    @Override
    public void setId(String id) {
        this.id = id;
    }
}


