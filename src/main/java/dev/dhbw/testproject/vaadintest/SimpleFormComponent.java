package dev.dhbw.testproject.vaadintest;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;


/**
 * A simple example of a custom Vaadin component which contains a simple text
 * field a button and a label which displays a welcome message to the entered
 * person.
 */
public class SimpleFormComponent extends CustomComponent
{

    private static final long serialVersionUID = 7293194489813855678L;

    public SimpleFormComponent()
    {
        // Initializes a new layout which contains this form.
        FormLayout layout = new FormLayout();

        /*
         * Initializes the text field, button and label components which make up the
         * form component.
         */
        TextField nameField = new TextField();
        nameField.setPlaceholder("Enter your name");
        Button formSubmitButton = new Button("Submit");
        Label nameLabel = new Label();

        /*
         * Initializes the action listener which displays the welcome message to the
         * user.
         */
        formSubmitButton.addClickListener(new ClickListener()
        {
            private static final long serialVersionUID = 2694058729391669763L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                if (nameField.getValue() != "")
                {
                    /*
                     * Sets the text of the label to the welcome message with the name entered in
                     * the text field.
                     */
                    nameLabel.setValue("Hello " + nameField.getValue() + "!");
                }
            }
        });

        // Adds the components to the component layout.
        layout.addComponents(nameField, formSubmitButton, nameLabel);

        // Sets the content of this component to this layout container.
        setCompositionRoot(layout);
    }

}
