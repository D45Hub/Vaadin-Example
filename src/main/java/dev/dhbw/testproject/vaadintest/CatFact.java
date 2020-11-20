package dev.dhbw.testproject.vaadintest;

/**
 * Simple class which represents a fact about cats, used by the REST example.
 */
public class CatFact
{
    private String factString;

    public CatFact(String fact)
    {
        this.factString = fact;
    }

    public String getFactString()
    {
        return factString;
    }

}
