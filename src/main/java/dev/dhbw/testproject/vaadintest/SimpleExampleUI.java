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


/**
 * This is a more elaborated example of building a web-page with different
 * Vaadin components.
 */
@Theme("mytheme")
public class SimpleExampleUI extends UI
{

    private static final long serialVersionUID = 4161167027744554330L;

    @Override
    protected void init(VaadinRequest request)
    {

        /*
         * A Layout is a container, where different components can be placed. This is
         * comparable to an HTML div element.
         */
        VerticalLayout layout = new VerticalLayout();

        // A simple text field (like the input tag from HTML).
        TextField nameField = new TextField();
        nameField.setCaption("Type your name here:");

        // Simple button.
        Button button = new Button("Click Me");
        Label buttonLabel = new Label("");

        /*
         * Adds an action listener to the button and makes the button to an action when
         * it gets pressed.
         */
        button.addClickListener(e -> {
            buttonLabel.setValue("Hi " + nameField.getValue() + "!");
        });

        // Adds the components to our layout container.
        layout.addComponents(nameField, button, buttonLabel);

        /*
         * Sets the site content to our layout container. We can only set our site
         * content to one component so we need to use these layouts if we want more than
         * one component in our page.
         */
        setContent(layout);
    }

    /**
     * This is a basic Servlet which configures the URL where this page is available
     * and actually deploys it to there.
     */
    @WebServlet("/simple/*")
    @VaadinServletConfiguration(ui = SimpleExampleUI.class, productionMode = false)
    public static class SimpleExampleServlet extends VaadinServlet
    {
        private static final long serialVersionUID = 184279586288058600L;

    }

}
