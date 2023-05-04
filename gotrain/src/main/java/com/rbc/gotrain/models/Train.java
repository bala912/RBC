package com.rbc.gotrain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Train {
    @Id
    int id;
    String line;
    int departure;
    int arrival;

    @Override
    public String toString() {
        return String.format("Id:%d Line:%s Departure:%d Apprival:%d",id,line,departure,arrival);
    }
}
