package dev.dhbw.testproject.vaadintest;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;


/**
 * This is a basic example of a "Hello World" page with Vaadin 8.
 */
@Theme("mytheme")
public class HelloWorldUI extends UI
{

    private static final long serialVersionUID = -8625583649960528114L;

    @Override
    protected void init(VaadinRequest request)
    {
        // A simple text label which displays "Hello World" to the page.
        Label helloWorldLabel = new Label("Hello World");

        // This sets the page content to the label component and thus displays it.
        setContent(helloWorldLabel);
    }

    /**
     * This is a basic Servlet which configures the URL where this page is available
     * and actually deploys it to there.
     */
    @WebServlet("/hello/*")
    @VaadinServletConfiguration(ui = HelloWorldUI.class, productionMode = false)
    public static class HelloWorldServlet extends VaadinServlet
    {

        private static final long serialVersionUID = 1050772903691228621L;

    }

}
