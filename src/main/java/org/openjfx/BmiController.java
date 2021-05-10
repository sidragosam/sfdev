package org.openjfx;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
import java.text.DecimalFormat;
//import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.Iterator;

public class BmiController {
    /*URL url = getClass().getResource("adatok.json");
    private final File fajl = new File(url.toURI());*/

    @FXML
    private TextField magassag;

    @FXML
    private TextField nev;

    @FXML
    private Text hasonlonev;

    @FXML
    private Text hasonlobmi;

    @FXML
    private TextField tomeg;

    @FXML
    private TextField bmiertek;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle11;

    @FXML
    private Rectangle rectangle111;

    @FXML
    private Rectangle rectangle1111;

    /*@FXML
    private Button gomb;*/

    public BmiController(){
    }

    @FXML
    private void handleButtonAction() {
        if(nev.getText().trim().isEmpty()){
            nev.setPromptText("Írd be a neved!");
        }
        if(tomeg.getText().trim().isEmpty() || !isInt(tomeg, tomeg.getText())){
            tomeg.setPromptText("Írd be a súlyod!");
            tomeg.setText("");
        }
        if(magassag.getText().trim().isEmpty() || !isInt(magassag, magassag.getText())){
            magassag.setPromptText("Írd be a magasságod!");
            magassag.setText("");
        }
        if(!magassag.getText().trim().isEmpty() && !tomeg.getText().trim().isEmpty() && !nev.getText().trim().isEmpty()) {
            if (isInt(tomeg, tomeg.getText()) && isInt(magassag, magassag.getText())) {
                szamitas();
            }
        }
    }
    double bmi;
    private void szamitas() {
        double suly = Double.parseDouble(tomeg.getText());
        double meret = Double.parseDouble(magassag.getText());
        bmi = suly / ((meret / 100) * (meret / 100));
        DecimalFormat df = new DecimalFormat("#.000");
        bmiertek.setText(String.valueOf(df.format(bmi)));
        if (bmi < 18.5) {
            rectangle1.setVisible(true);
            rectangle11.setVisible(false);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(false);
        } else if (bmi >= 18.5 && bmi < 25) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(true);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(false);
        } else if (bmi >= 25 && bmi < 30) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(false);
            rectangle111.setVisible(true);
            rectangle1111.setVisible(false);
        } else if (bmi >= 30) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(false);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(true);
        }
        getInfoFromJSON();
        saveInfoToJSON(nev.getText(), bmi);
    }

    @FXML
    private void handleLinkAction(){
        openWebpage();
    }

    private static void openWebpage(){
        try{
            new ProcessBuilder("x-www-browser", "http://a.igneczitibor.hu/skyLine/adatvedelem/adatvedelem.html").start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isInt(TextField input, String message){
        try{
            Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("Hiba " + message + " nem egy szám");
            return false;
        }
    }
    /*private String sor;
    private ArrayList<String> lista = new ArrayList<String>();
    public void loadData(){
        try {
            Scanner s = new Scanner(fajl);
            while(s.hasNextLine()){
                sor = s.nextLine();
                System.out.println(sor);
            }
            System.out.println(lista);
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("Nincs ilyen fájl");
            e.printStackTrace();
        }
    }*/
    private void getInfoFromJSON() {
        //loadData();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/home/skyline/Asztal/BMI Final/src/main/resources/org/openjfx/adatok.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lista = (JSONArray) jsonObject.get("adatok");
            JSONArray sortedlista = new JSONArray();
            String[] parts;
            String part2;
            String[] parts2;
            String part3;
            String part4;
            String finalpart;
            double bmiSTAR;
            double eredmeny = 0;
            for (JSONObject object : (Iterable<JSONObject>) lista) {
                System.out.println(object);
            }
            ArrayList<Double> numbers = new ArrayList<Double>();
            for (int i = 0; i < lista.toArray().length; i++) {
                parts = lista.get(i).toString().split(",",-2);
                part2 = parts[1];
                parts2 = part2.split(":",2);
                part3 = parts2[1];
                part4 = part3.replaceAll("}","");
                finalpart = part4.substring(1, part4.length()-1);
                bmiSTAR = Double.parseDouble(finalpart);
                eredmeny = bmiSTAR;// - bmi;
                System.out.println("Eredmény: "+ eredmeny);
                numbers.add(eredmeny);
            }
            double distance = Math.abs(numbers.get(0) - bmi);
            int idx = 0;
            for (int i = 1 ; i < lista.toArray().length; i++) {
                double idistance = Math.abs(numbers.get(i) - bmi);
                if(idistance < distance){
                    idx = i;
                    distance = idistance;
                }
            }
            double sor = numbers.get(idx);
            System.out.println(sor);
            Object nyerosor = lista.get(idx);
            String[] spliteles = nyerosor.toString().split(",",-2);
            String nyeronev = (spliteles[0].split(":",-2)[1]);
            nyeronev = nyeronev.substring(1, nyeronev.length()-1);
            String nyerobmi = (spliteles[1].split(":",2)[1]).replaceAll("}","");
            nyerobmi = nyerobmi.substring(1, nyerobmi.length()-1);
            System.out.println(nyeronev);
            System.out.println(nyerobmi);
            hasonlonev.setText(nyeronev);
            hasonlobmi.setText(nyerobmi);

            /*lista.sort(new Comparator<JShasonlobmiONObject>() {
                private static final String kulcs = "BMI";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = "";
                    String valB = "";
                    DecimalFormat df = new DecimalFormat("#.000");
                    try {
                        valA = (String) a.get(kulcs);
                        /*double valAd = Double.parseDouble(valA);
                        valAd = valAd - bmi;
                        valA = String.valueOf(df.format(valAd));
                        valB = (String) b.get(kulcs);
                        /*double valBd = Double.parseDouble(valA);
                        valBd = valBd - bmi;
                        valA = String.valueOf(df.format(valBd));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return valA.compareTo(valB);
                }
            });
            for (int i = 0; i < lista.toArray().length; i++) {
                sortedlista.add(lista.get(i));
            }*/
            System.out.println(sortedlista);
            //hasonlonev.setText(sortedlista.get(1).toString());

        } catch (FileNotFoundException e) {
            System.err.println("A fájl nem található!");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
    private void saveInfoToJSON(String name, double bmivalue){

    }
}
