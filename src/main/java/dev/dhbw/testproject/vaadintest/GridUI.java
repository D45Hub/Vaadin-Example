package dev.dhbw.testproject.vaadintest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * A basic example for a Vaadin table with some example data.
 */
@Theme("mytheme")
public class GridUI extends UI
{
    private static final long serialVersionUID = 5946786476996830802L;

    private static final Faker FAKER = new Faker();

    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout layout = new VerticalLayout();

        // Initializes a basic grid formatted for person data defined by the "Person"
        // class/bean.
        Grid<Person> grid = new Grid<>();

        /*
         * Initializes a data provider for the grid with provides the grid with some
         * basic pre-initialized data.
         */
        ListDataProvider<Person> dataProvider = new ListDataProvider<>(
                generateRandomPersonList(10));

        // Sets the initial data provider to the defined data provider.
        grid.setDataProvider(dataProvider);

        // Initializes the grid columns to the different parameters of the person data.
        grid.addColumn(Person::getFirstName).setCaption("First Name");
        grid.addColumn(Person::getLastName).setCaption("Last Name");
        grid.addColumn(Person::getBirthDate).setCaption("Birth Date");

        // Expands the grid size to fully expand it.
        grid.setSizeFull();

        layout.addComponent(grid);

        setContent(layout);
    }

    /**
     * This is a basic Servlet which configures the URL where this page is available
     * and actually deploys it to there.
     */
    @WebServlet("/grid/*")
    @VaadinServletConfiguration(ui = GridUI.class, productionMode = false)
    public static class GridServlet extends VaadinServlet
    {
        private static final long serialVersionUID = 3097237961278710535L;

    }

    /**
     * This method generates a list of persons with random names and birthdates.
     * 
     * @param listSize
     *            The size of the list you want to generate.
     * @return Returns a list of random persons.
     */
    private List<Person> generateRandomPersonList(int listSize)
    {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < listSize; i++)
        {
            personList.add(generateRandomPerson());
        }

        return personList;
    }

    /**
     * This method generates a person with a random name and birthdate. For the
     * generation of the random person data the Java Faker API is used which
     * provides this random data.
     * 
     * @return Returns a random person.
     */
    private Person generateRandomPerson()
    {
        Name personName = FAKER.name();
        String firstName = personName.firstName();
        String lastName = personName.lastName();
        Date birthDate = FAKER.date().birthday(19, 39);

        return new Person(firstName, lastName, birthDate);
    }

}
