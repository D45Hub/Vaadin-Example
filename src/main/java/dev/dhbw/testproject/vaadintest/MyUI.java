package dev.dhbw.testproject.vaadintest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class MyUI extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        Label buttonLabel = new Label("");

        button.addClickListener(e -> {
            buttonLabel.setValue("Thanks " + name.getValue() + ", it works!");
        });

        Grid<Person> grid = new Grid<>();
        ListDataProvider<Person> dataProvider = new ListDataProvider<>(generateRandomPersonList());
        grid.setDataProvider(dataProvider);
        grid.addColumn(Person::getFirstName).setCaption("First Name");
        grid.addColumn(Person::getLastName).setCaption("Last Name");
        grid.addColumn(Person::getBirthDate).setCaption("Birth Date");
        
        ComboBox<String> testComboBox = new ComboBox<>("My great Combobox");
        List<String> comboBoxItems = new ArrayList<>();
        comboBoxItems.add("Test 1");
        comboBoxItems.add("Hello");
        comboBoxItems.add("World");
        testComboBox.setItems(comboBoxItems);
        

        layout.addComponents(name, button, buttonLabel, grid, testComboBox);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {
    }

    private List<Person> generateRandomPersonList()
    {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            personList.add(new Person("Max" + i, "Mustermann",
                    generateRandomDate(new Date(90, 1, 1), new Date(105, 1, 1))));
        }

        return personList;
    }

    private Date generateRandomDate(Date startInclusive, Date endExclusive)
    {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
}
