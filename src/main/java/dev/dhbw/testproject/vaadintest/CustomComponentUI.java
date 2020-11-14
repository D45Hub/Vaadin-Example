package dev.dhbw.testproject.vaadintest;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class CustomComponentUI extends UI
{

    private static final long serialVersionUID = -8486038573121795774L;

    @Override
    protected void init(VaadinRequest request)
    {
        SimpleFormComponent customForm = new SimpleFormComponent();
        setContent(customForm);
    }
    
    @WebServlet("/custom/*")
    @VaadinServletConfiguration(ui = CustomComponentUI.class, productionMode = false)
    public static class CustomComponentServlet extends VaadinServlet
    {
        private static final long serialVersionUID = -1088791369058292576L;

    }

}
