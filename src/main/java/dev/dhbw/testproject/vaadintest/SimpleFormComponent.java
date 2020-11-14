package dev.dhbw.testproject.vaadintest;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class SimpleFormComponent extends CustomComponent
{

    private static final long serialVersionUID = 7293194489813855678L;
    
    public SimpleFormComponent() 
    {
        FormLayout layout = new FormLayout();
        
        TextField nameField = new TextField();
        Button formSubmitButton = new Button("Submit"); 
        Label nameLabel = new Label();
        
        formSubmitButton.addClickListener(new ClickListener()
        {
            private static final long serialVersionUID = 2694058729391669763L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                if(nameField.getValue() != "") 
                {
                    nameLabel.setValue("Hello " + nameField.getValue() + "!");
                }
            }
        });
        
        layout.addComponents(nameField, formSubmitButton, nameLabel);
        
        setCompositionRoot(layout);
    }

}
