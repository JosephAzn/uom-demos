/**
 * Copyright (c) 2013-2014 Werner Keil and others.
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */

package org.unitsofmeasurement.demo.javafx.fxlib;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.measure.Unit;
import javax.measure.function.UnitConverter;
import org.unitsofmeasurement.impl.AbstractUnit;
import org.unitsofmeasurement.impl.system.SI;
import static org.unitsofmeasurement.impl.system.SI.*;
import static org.unitsofmeasurement.impl.system.SIPrefix.*;
import static org.unitsofmeasurement.impl.system.US.*;


/**
 *
 * @author Werner
 */
public class FXMLDocumentController implements Initializable {
    private static NumberFormat FORMAT = NumberFormat.getNumberInstance();
    
    @FXML
    private Label label;
    
    @FXML
    private ComboBox<Unit> fromCombo;
    
    @FXML
    private ComboBox<Unit> toCombo;
    
    @FXML
    private TextField fromFld;
    
    @FXML
    private TextField toFld;
    
    @FXML
    private void helloButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You selected " + event.getSource().toString());
        final String selection = ((Button)event.getSource()).getText();
        if (selection != null && !selection.equals(label.getText())) {
            label.setText(selection);
            clearAll();
            updateForSelection(selection);
        }
    }
    
    @FXML
    public void handleFromKey(KeyEvent evt) {
        /*
            String character = evt.getCharacter();
            if(!character.equals("1"))
            {
                //JOptionPane.showMessageDialog(null,evt.getCharacter());
                System.out.println("You typed " + evt.getCharacter());
                evt.consume();                
            }   
                */
            KeyCode code = evt.getCode();      
            System.out.println("Code " + code);
 /*           
            if (KeyCode.DELETE == code) System.out.println("Del");
            
            char ar[] = evt.getCharacter().toCharArray();
            char ch = ar[evt.getCharacter().toCharArray().length - 1];
            System.out.println("You entered " + String.valueOf(ar));
            if (!(ch >= '0' && ch <= '9') && 
         */
            if (!(code.isDigitKey()) &&
                    !(KeyCode.BACK_SPACE.equals(code) || KeyCode.DELETE == code)) {
               System.out.println("The char you entered is not a number");
               evt.consume();
            } else {
                final String text = ((TextField)evt.getSource()).getText();
                System.out.println("You entered " + text);
                /*
                Measurement from = AbstractMeasurement.of(new BigDecimal(text), fromCombo.getValue());
                System.out.println("From " + from);
                Measurement to = from.to(toCombo.getValue());
                System.out.println("To " + to);*/
                UnitConverter converter = fromCombo.getValue().getConverterTo(toCombo.getValue());
                Number convertedValue = converter.convert(Double.valueOf(text));
                System.out.println("Converted: " + convertedValue);
                toFld.setText(FORMAT.format(convertedValue));
            }
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAll();
        updateForSelection("length");
    }    
    
    private void clearAll() {
        fromCombo.getItems().clear();
        toCombo.getItems().clear();
        fromFld.clear();
        toFld.clear();
    }
    
    private void updateForSelection(String selection) {
        switch (selection) {
            case "length":
                fromCombo.getItems().addAll(
                    MILE,
                    KILO(METRE),
                    METRE,
                    CENTI(METRE),
                    FOOT,
                    INCH,
                    YARD
                );
                toCombo.getItems().addAll(
                    MILE,
                    KILO(METRE),
                    METRE,
                    CENTI(METRE),
                    FOOT,
                    INCH,
                    YARD
                );
                break;
            case "area":
                fromCombo.getItems().addAll(
                    SQUARE_METRE,
                    SQUARE_FOOT,
                    ARE,
                    HECTARE
                );
                toCombo.getItems().addAll(
                    SQUARE_METRE,
                    SQUARE_FOOT,                       
                    ARE,
                    HECTARE
                );
                break;
            case "power":
                fromCombo.getItems().addAll(
                    HORSEPOWER,
                    WATT
                );
                toCombo.getItems().addAll(
                    HORSEPOWER,
                    WATT
                );
                break;
            case "angle":
                fromCombo.getItems().addAll(
                    SI.DEGREE_ANGLE,
                    SI.MINUTE_ANGLE,
                    SI.SECOND_ANGLE,
                    CENTIRADIAN,
                    GRADE,
                    SI.REVOLUTION
                );
                toCombo.getItems().addAll(
                    SI.DEGREE_ANGLE,
                    SI.MINUTE_ANGLE,
                    SI.SECOND_ANGLE,
                    CENTIRADIAN,
                    GRADE,
                    SI.REVOLUTION
                );
                break;
            default:
                break;
        }
    }
    
    public Unit getFromUnit() {
            String symbol = fromCombo.getSelectionModel().getSelectedItem().getSymbol();
            if (symbol != null) {
                    return AbstractUnit.of(symbol);
            }
            return null;
    }

    public void setFromUnit(Unit unit) {
            if (unit != null) {
                    fromCombo.getSelectionModel().select(unit);
            } else {
                    fromCombo.getSelectionModel().clearSelection();
            }
    }
}
