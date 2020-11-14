package dev.dhbw.testproject.vaadintest;

import java.util.Date;


/**
 * A simple person class/bean which contains/represents data of a person's
 * first/last name and their birthdate.
 */
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
