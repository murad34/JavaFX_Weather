package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city_textfield;

    @FXML
    private Label davleniye_label;

    @FXML
    private TextField davleniye_textfield;

    @FXML
    private Label information_label;

    @FXML
    private Label maksimum_label;

    @FXML
    private TextField maksimum_textfield;

    @FXML
    private Label minimum_label;

    @FXML
    private TextField minimum_textfield;

    @FXML
    private Button ok_button;

    @FXML
    private Label osusaetsa_label;

    @FXML
    private TextField osusaetsa_textfield;

    @FXML
    private Label temperature_label;

    @FXML
    private TextField temperature_textfield;

    @FXML
    private Label weather_label;

    @FXML
    void ok_button_action(ActionEvent event) {

            String getUserCity = city_textfield.getText().trim();

            if (!getUserCity.equals("")) {

                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid=7452ca43b0d9fe4af566d6d54a42de3a");

                System.out.println(output);

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temperature_textfield.setText(String.valueOf(obj.getJSONObject("main").getDouble("temp")));
                    osusaetsa_textfield.setText(String.valueOf(obj.getJSONObject("main").getDouble("feels_like")));
                    maksimum_textfield.setText(String.valueOf(obj.getJSONObject("main").getDouble("temp_max")));
                    minimum_textfield.setText(String.valueOf(obj.getJSONObject("main").getDouble("temp_min")));
                    davleniye_textfield.setText(String.valueOf(obj.getJSONObject("main").getDouble("pressure")));


//                    temperature_textfield.setText("Температура : " + obj.getJSONObject("main").getDouble("temp"));
//                    osusaetsa_textfield.setText("Ощущается : " + obj.getJSONObject("main").getDouble("feels_like"));
//                    maksimum_textfield.setText("Максимум : " + obj.getJSONObject("main").getDouble("temp_max"));
//                    minimum_textfield.setText("Минимум : " + obj.getJSONObject("main").getDouble("temp_min"));
//                    davleniye_textfield.setText("Давление : " + obj.getJSONObject("main").getDouble("pressure"));

                }

            }

    }

    @FXML
    void initialize() {

//        ok_button.setOnAction(event -> {
//
//            String getUserCity = city_textfield.getText().trim();
//
//            String output = getUrlContent("https://history.openweathermap.org/data/2.5/aggregated/year?q="+getUserCity +"&appid=7452ca43b0d9fe4af566d6d54a42de3a");
//            System.out.println(output);
//
//        });

    }

    public static String getUrlContent(String urlAddress) {

        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            System.out.println("Такой город не найден !");
        }
        return content.toString();
    }

}
