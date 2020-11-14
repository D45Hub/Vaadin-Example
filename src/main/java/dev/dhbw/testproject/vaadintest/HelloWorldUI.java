package dev.dhbw.testproject.vaadintest;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class HelloWorldUI extends UI
{

    private static final long serialVersionUID = -8625583649960528114L;

    @Override
    protected void init(VaadinRequest request)
    {
        Label helloWorldLabel = new Label("Hello World");
        setContent(helloWorldLabel);
    }
    
    @WebServlet("/hello/*")
    @VaadinServletConfiguration(ui = HelloWorldUI.class, productionMode = false)
    public static class HelloWorldServlet extends VaadinServlet
    {

        private static final long serialVersionUID = 1050772903691228621L;

    }

}


