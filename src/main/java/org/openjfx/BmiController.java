package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;

public class BmiController {

    @FXML
    private TextField magassag;

    @FXML
    private TextField nev;

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

    @FXML
    private Button gomb;

    /*@FXML
    private void handleNumberTyping(java.awt.event.KeyEvent event) {
        char c = event.getKeyChar();
        //char c = event.getCharacter();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
            event.consume();
        }
    }*/

    @FXML
    private void handleButtonAction(ActionEvent event){
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
        /*gomb.setOnAction(e -> isInt(magassag, magassag.getText()));
        gomb.setOnAction(e -> isInt(tomeg, tomeg.getText()));*/
        if(!magassag.getText().trim().isEmpty() && !tomeg.getText().trim().isEmpty() && !nev.getText().trim().isEmpty()) {
            //if(magassag.getText().getClass().equals(Double.class) && tomeg.getClass().equals(Double.class)){
            /*if(Integer.parseInt(magassag.getText()) > 0
            && Integer.parseInt(tomeg.getText()) > 0) {*/
            if (isInt(tomeg, tomeg.getText()) && isInt(magassag, magassag.getText())) {
                double suly = Double.parseDouble(tomeg.getText());
                double meret = Double.parseDouble(magassag.getText());
                double bmi = suly / ((meret / 100) * (meret / 100));
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
                //}
            }
        }
    }

    @FXML
    private void handleLinkAction(ActionEvent event){
        openWebpage("http://a.igneczitibor.hu/skyLine/adatvedelem/adatvedelem.html");
    }

    private static void openWebpage(String url){
        try{
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isInt(TextField input, String message){
        try{
            int nmbr = Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("Hiba " + message + " nem egy szám");
            return false;
        }
    }
}
