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


@Theme("mytheme")
public class GridUI extends UI
{
    private static final long serialVersionUID = 5946786476996830802L;
    
    private static final Faker FAKER = new Faker();

    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout layout = new VerticalLayout();

        Grid<Person> grid = new Grid<>();
        ListDataProvider<Person> dataProvider = new ListDataProvider<>(generateRandomPersonList());
        grid.setDataProvider(dataProvider);
        grid.addColumn(Person::getFirstName).setCaption("First Name");
        grid.addColumn(Person::getLastName).setCaption("Last Name");
        grid.addColumn(Person::getBirthDate).setCaption("Birth Date");
        grid.setSizeFull();
        
        layout.addComponent(grid);
        
        setContent(layout);
    }

    @WebServlet("/grid/*")
    @VaadinServletConfiguration(ui = GridUI.class, productionMode = false)
    public static class GridServlet extends VaadinServlet
    {
        private static final long serialVersionUID = 3097237961278710535L;

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
