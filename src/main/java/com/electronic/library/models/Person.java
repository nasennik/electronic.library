package com.electronic.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "Заполните ФИО")
    @Size(min = 2, message = "Слишком короткое имя")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть от 1900")
    @Max(value = 2014, message = "Можно регистрировать людей старше 9 лет" )
    private int yearOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){

    }
}
