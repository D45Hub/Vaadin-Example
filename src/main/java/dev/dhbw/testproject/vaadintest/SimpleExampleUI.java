package dev.dhbw.testproject.vaadintest;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class SimpleExampleUI extends UI
{

    private static final long serialVersionUID = 4161167027744554330L;

    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout layout = new VerticalLayout();
        
        TextField nameField = new TextField();
        nameField.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        Label buttonLabel = new Label("");

        button.addClickListener(e -> {
            buttonLabel.setValue("Hi " + nameField.getValue() + "!");
        });
        
        layout.addComponent(nameField);
        layout.addComponent(button);
        layout.addComponent(buttonLabel);

        setContent(layout);
    }

    @WebServlet("/simple/*")
    @VaadinServletConfiguration(ui = SimpleExampleUI.class, productionMode = false)
    public static class SimpleExampleServlet extends VaadinServlet
    {
        private static final long serialVersionUID = 184279586288058600L;

    }

}
