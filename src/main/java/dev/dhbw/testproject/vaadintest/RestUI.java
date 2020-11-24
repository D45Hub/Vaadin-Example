package dev.dhbw.testproject.vaadintest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * Another more complete example of an application. This one fetches data from a
 * REST API (https://meowfacts.herokuapp.com) and displays it in a pretty way
 * with a button to get data from the API and then show it to the user. The
 * title annotation sets the name of the title of the page.
 */
@Theme("mytheme")
@Title("REST Example")
public class RestUI extends UI
{
    private static final long serialVersionUID = 2824052883319408801L;

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void init(VaadinRequest request)
    {
        // Initialization of layout components.
        VerticalLayout factLayout = new VerticalLayout();
        factLayout.setId("factLayout");

        CatFact initialCatFact = getCatFactFromService();

        /*
         * You can set the content mode of these label components to the HTML mode and
         * use HTML tags. They will be put in the HTML accordingly.
         */
        Label siteHeader = new Label("<h1>Get your dose of cat facts</h1>", ContentMode.HTML);

        Label catFactLabel = new Label("<p>" + initialCatFact.getFactString() + "</p>",
                ContentMode.HTML);
        catFactLabel.setId("factLabel");

        Button factRefreshButton = new Button("Get new cat fact.");

        // Sets CSS styling of some elements.
        Page.getCurrent().getStyles()
                .add("#factLayout{padding: 30px 20%;} #factLabel{padding-bottom: 20px;}");

        /*
         * Initializes the action listener which replaces the text of a label with a new
         * cat fact.
         */
        factRefreshButton.addClickListener(new ClickListener()
        {
            private static final long serialVersionUID = -6384557494236864977L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                CatFact randomCatFact = getCatFactFromService();
                catFactLabel.setValue("<p>" + randomCatFact.getFactString() + "</p>");
            }
        });

        factLayout.addComponents(siteHeader, catFactLabel, factRefreshButton);

        setContent(factLayout);
    }

    /**
     * This method gets a new random cat fact from the REST API.
     * 
     * @return Returns a random cat fact.
     */
    private CatFact getCatFactFromService()
    {
        String jsonOutput = getResponseFromService();

        CatFactModel model = parseCatModel(jsonOutput);

        CatFact output = convertToInternalModel(model);

        return output;
    }

    /**
     * This method gets the raw data in form of a String from the REST API with the
     * random cat fact.
     * 
     * @return Returns the String response of the REST API with the GET call.
     *         Ideally this will return a JSON object with the cat fact.
     */
    private String getResponseFromService()
    {
        String response = "";
        try
        {

            URL url = new URL("https://meowfacts.herokuapp.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);

            response = br.readLine();

            conn.disconnect();

        }
        catch (Exception e)
        {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        return response;
    }

    /**
     * This method parses the raw JSON string which comes from the REST API and
     * translates it to a model class.
     * 
     * @param jsonString
     *            The json String with the JSON cat fact object from the REST API.
     * @return Returns the model object which is related to the JSON object.
     */
    private CatFactModel parseCatModel(String jsonString)
    {
        CatFactModel model = null;

        try
        {
            model = MAPPER.readValue(jsonString, CatFactModel.class);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        return model;
    }

    /**
     * This method converts the model object to the real cat fact object we are
     * going to use internally.
     * 
     * @param model
     *            The model object we want to convert.
     * @return Returns the final object.
     */
    private CatFact convertToInternalModel(CatFactModel model)
    {
        return new CatFact(model.getData().get(0));
    }

    /**
     * This is a basic Servlet which configures the URL where this page is available
     * and actually deploys it to there.
     */
    @WebServlet("/rest/*")
    @VaadinServletConfiguration(ui = RestUI.class, productionMode = false)
    public static class RestServlet extends VaadinServlet
    {
        private static final long serialVersionUID = -294459246930641751L;

    }
}
