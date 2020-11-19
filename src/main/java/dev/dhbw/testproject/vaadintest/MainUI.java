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


@Theme("mytheme")
public class MainUI extends UI
{

    private static final long serialVersionUID = 6494002448165828217L;
    
    private static final Faker FAKER = new Faker();

    private Grid<Person> grid;
    private List<Person> personList;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        VerticalLayout layout = new VerticalLayout();

        grid = new Grid<>();
        personList = generateRandomPersonList();
        ListDataProvider<Person> dataProvider = new ListDataProvider<>(personList);
        grid.setDataProvider(dataProvider);
        grid.addColumn(Person::getFirstName).setCaption("First Name");
        grid.addColumn(Person::getLastName).setCaption("Last Name");
        grid.addColumn(Person::getBirthDate).setCaption("Birth Date");
        grid.setSizeFull();

        VerticalLayout personAdditionLayout = new VerticalLayout();
        personAdditionLayout.setId("personForm");
        Page.getCurrent().getStyles().add("#personForm {text-align: center;}");
        DateField dateField = new DateField();
        dateField.setCaption("Birthdate");
        TextField nameField = new TextField();
        nameField.setCaption("First Name");
        TextField surnameField = new TextField();
        surnameField.setCaption("Last Name");
        Button formCompleteButton = new Button("Add Person");
        formCompleteButton.addClickListener(new ClickListener()
        {
            private static final long serialVersionUID = 3228734653695539908L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                String personName = nameField.getValue();
                String personSurname = surnameField.getValue();
                LocalDate personBirthDate = dateField.getValue();

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

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {

        private static final long serialVersionUID = -8557623511989821877L;
    }

    private List<Person> generateRandomPersonList()
    {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            personList.add(generateRandomPerson());
        }

        return personList;
    }

    private Person generateRandomPerson() 
    {
        Name personName = FAKER.name();
        String firstName = personName.firstName();
        String lastName = personName.lastName();
        Date birthDate = FAKER.date().birthday(19, 39);
        
        return new Person(firstName, lastName, birthDate);
    }
}
