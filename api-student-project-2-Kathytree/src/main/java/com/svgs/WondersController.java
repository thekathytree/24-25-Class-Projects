package com.svgs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WondersController {

    @FXML
    private ImageView africa;

    @FXML
    private ImageView antarctica;

    @FXML
    private ImageView asia;

    @FXML
    private ImageView australia;

    @FXML
    private ImageView europe;

    @FXML
    private Label taxonomyLbl;

    @FXML
    private ToggleGroup answers;

    @FXML
    private RadioButton buttonA;

    @FXML
    private RadioButton buttonB;

    @FXML
    private RadioButton buttonC;

    @FXML
    private RadioButton buttonD;

    @FXML
    private Label locationsLbl;

    @FXML
    private ImageView northamerica;

    @FXML
    private Label dietLbl;

    @FXML
    private Button gssButt;

    @FXML
    private ImageView southamerica;

    private String diet;

    private String correctDiet;

    private File nameFile;

    private ArrayList<String> aniNames = new ArrayList<>();

    private String selected;

    private ArrayList<Animal> aSet = new ArrayList<>();

    private Animal current;

    private ArrayList<String> loc = new ArrayList<>();

    private ArrayList<String> guessStrings = new ArrayList<>();

    @FXML
    void initialize() {
        africa.setVisible(false);
        europe.setVisible(false);
        asia.setVisible(false);
        northamerica.setVisible(false);
        southamerica.setVisible(false);
        antarctica.setVisible(false);
        australia.setVisible(false);
        dietLbl.setVisible(false);

        nameFile = new File("animalNames.txt");
        try {
            BufferedReader read = new BufferedReader(new FileReader(nameFile));
            String line = read.readLine();
            while (line != null) {
                aniNames.add(line);
                guessStrings.add(line);
                //counter++;
                line = read.readLine();
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        setUpAnimal();

    }
    @FXML
    void setUpAnimal()
    {
        grabName();
        makeRequest();
        selectAnimal();
        setAnimalInfo();
    }

    void grabName()// working
    {
        int r = (int) (Math.random() * aniNames.size());
        selected = aniNames.get(r);
        aniNames.remove(r);
    }

    void selectAnimal() {
        current = aSet.get(0);
        System.out.println(current);
    }
    int randomAnimal(){
        int r = (int) (Math.random() * aSet.size());
        return r;
    }
    @FXML
    void guess() {
        System.out.println("made a guess");
        RadioButton selectedAnswer = (RadioButton) answers.getSelectedToggle();
        String gss = selectedAnswer.getText();
        if(gss.toLowerCase().equals(current.name.toLowerCase()))
        {
        africa.setVisible(false);
        europe.setVisible(false);
        asia.setVisible(false);
        northamerica.setVisible(false);
        southamerica.setVisible(false);
        antarctica.setVisible(false);
        australia.setVisible(false);
        dietLbl.setVisible(false);
        guessStrings.add(buttonA.getText());
        guessStrings.add(buttonB.getText());
        guessStrings.add(buttonC.getText());
        guessStrings.add(buttonD.getText());
        loc.clear();
        setUpAnimal();
        }
    }

    void makeRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.api-ninjas.com/v1/animals?name=" + selected)
                .addHeader("X-Api-Key", "PQuJPbdGviKunbnvuRN1lJtl4wHnHzctHKjsr8cf").build();
        try {
            aSet.clear();
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            Animal[] an = gson.fromJson(response.body().string(), Animal[].class);
            for (Animal r : an) {
                aSet.add(r);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void setAnimalInfo() {
       System.out.println("setting animal");
       correctDiet = current.characteristics.get("diet").toLowerCase();
       System.out.println(correctDiet);
       setLocations(current);
       taxonomyLbl.setText(current.taxonomy.kingdom + " " + current.taxonomy.scientific_name);
       ArrayList<String> selection = new ArrayList<>();
       selection.add(current.name);
       guessStrings.remove(current.name);
       for(int i = 0; i < 3; i++)
       {
        int e = (int) (Math.random() * guessStrings.size());
        selection.add(guessStrings.get(e));
        guessStrings.remove(e);
       }
       Collections.shuffle(selection);
       buttonA.setText(selection.get(0));
       buttonB.setText(selection.get(1));
       buttonC.setText(selection.get(2));
       buttonD.setText(selection.get(3));

    }

    @FXML
    void setLocations(Animal e) {
        System.out.println("setting locations");
        for(String l : current.locations)
        {
            loc.add(l);

            if(l.equals("Africa"))
            {
                africa.setVisible(true);
            }
            else if(l.equals("Antarctica"))
            {
                antarctica.setVisible(true);
            } 
            else if(l.equals("Asia"))
            {
                asia.setVisible(true);
            } 
            else if(l.equals("Europe"))
            {
                europe.setVisible(true);
            } 
            else if(l.equals("North America"))
            {
                northamerica.setVisible(true);
            } 
            else if(l.equals("South America"))
            {
                southamerica.setVisible(true);
            } 
            else if(l.equals("Australia"))
            {
                australia.setVisible(true);
            }
            System.out.println(loc);
        }
        String locationString = "";

        for(String iL : loc)
        {
            locationString+= iL + " ";
        }
        locationsLbl.setText(locationString);
    }
    @FXML 
    void randomCharac()
    {
        ArrayList<String> options = new ArrayList<>();
        for(String s : current.characteristics.keySet()) {
            options.add(s);
        }
        int randomOne = (int)(Math.random() * options.size());
        String randomKey = options.get(randomOne);
        if(randomKey.equals("common_name")){
            randomCharac();
        }
        dietLbl.setText(randomKey.replace("_", " ") + " : " + current.characteristics.get(randomKey).replace("_", " "));
        dietLbl.setVisible(true);
    }
    @FXML
    void setCarnivore(ActionEvent event) {
        diet = "carnivore";
        if (diet.equals(correctDiet)) {
            randomCharac();
        }
    }

    @FXML
    void setHerbivore(ActionEvent event) {
        diet = "herbivore";
        if (diet.equals(correctDiet)) {
            randomCharac();
        }
    }

    @FXML
    void setOmnivore(ActionEvent event) {
        diet = "omnivore";
        if (diet.equals(correctDiet)) {
            randomCharac();
        }
    }
}