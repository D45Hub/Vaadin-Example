package dev.dhbw.testproject.vaadintest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * More complete example of an application. In here we have a grid with some
 * example persons in there and a form "component" which inserts new persons to
 * the grid.
 */
@Theme("mytheme")
@Title("Grid Application Example")
public class GridAppUI extends UI
{

    private static final long serialVersionUID = 6494002448165828217L;

    private static final Faker FAKER = new Faker();

    private Grid<Person> grid;
    private List<Person> personList;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        VerticalLayout layout = new VerticalLayout();

        // Initializes a grid with some random person data.
        grid = new Grid<>();

        /*
         * If we can access this list object, we can add new data to this list and the
         * grid automatically updates its person values.
         */
        personList = generateRandomPersonList(10);
        ListDataProvider<Person> dataProvider = new ListDataProvider<>(personList);

        grid.setDataProvider(dataProvider);
        grid.addColumn(Person::getFirstName).setCaption("First Name");
        grid.addColumn(Person::getLastName).setCaption("Last Name");
        grid.addColumn(Person::getBirthDate).setCaption("Birth Date");
        grid.setSizeFull();

        /*
         * Initializes the form for adding persons. This contains 2 text fields and a
         * data picker.
         */
        VerticalLayout personAdditionLayout = new VerticalLayout();
        personAdditionLayout.setId("personForm");

        /*
         * We can add custom CSS inside a CSS file but you can also inject it like this.
         * (Very useful if you cannot access the CSS files or want to make small
         * changes) I did this so it is more visible which changes I did to the CSS...
         * You need to set the ID/Class of the object you want to reference beforehand.
         */
        Page.getCurrent().getStyles().add("#personForm {text-align: center;}");
        DateField dateField = new DateField();
        dateField.setCaption("Birthdate");
        TextField nameField = new TextField();
        nameField.setCaption("First Name");
        TextField surnameField = new TextField();
        surnameField.setCaption("Last Name");
        Button formCompleteButton = new Button("Add Person");

        // Initializes the action listener which adds the person data.
        formCompleteButton.addClickListener(new ClickListener()
        {
            private static final long serialVersionUID = 3228734653695539908L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                /*
                 * Gets the data from each of the fields.
                 */
                String personName = nameField.getValue();
                String personSurname = surnameField.getValue();
                LocalDate personBirthDate = dateField.getValue();

                /*
                 * Checks if the fields aren't empty when the button gets pressed and if they
                 * aren't a new person gets added to the grid.
                 */
                if (personName != "" && personSurname != "" && personBirthDate != null)
                {
                    personList.add(new Person(personName, personSurname, Date.from(
                            personBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant())));
                    grid.getDataProvider().refreshAll();
                }
            }
        });

        personAdditionLayout.addComponents(nameField, surnameField, dateField, formCompleteButton);

        layout.addComponents(grid, personAdditionLayout);
        layout.setSizeFull();
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "GridAppServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GridAppUI.class, productionMode = false)
    public static class GridAppServlet extends VaadinServlet
    {

        private static final long serialVersionUID = -8557623511989821877L;
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
