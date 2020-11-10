package dev.dhbw.testproject.vaadintest;

import java.util.Date;

public class Person
{
    private String firstName;
    private String lastName;
    private Date birthDate;
    
    public Person(String firstName, String lastName, Date birthDate) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

}
