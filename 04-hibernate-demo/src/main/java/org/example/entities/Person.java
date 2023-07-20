package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
}
