package dev.dhbw.testproject.vaadintest;

import java.util.List;


/**
 * Simple model bean/class which represents the cat fact data from the REST API.
 */
public class CatFactModel
{
    private List<String> data;

    public CatFactModel()
    {
    }

    public List<String> getData()
    {
        return data;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }

}
