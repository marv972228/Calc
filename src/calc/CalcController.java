/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package calc;

import statemachine.CalcSM;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author marvi
 */
public class CalcController implements Initializable {

    @FXML
    private TextField operationsTextField;

    @FXML
    private TextField resultTextField;

    @FXML
    private TextField memoryTextField;

    @FXML
    private Button ceBtn;

    @FXML
    private Button onBtn;

    @FXML
    private Button negateBtn;

    @FXML
    private Button exponentBtn;

    @FXML
    private Button offBtn;

    @FXML
    private Button divideBtn;

    @FXML
    private Button multiplyBtn;

    @FXML
    private Button subtractBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button mrcBtn;

    @FXML
    private Button mSubtractBtn;

    @FXML
    private Button mAddBtn;

    @FXML
    private Button percentBtn;

    @FXML
    private Button zeroBtn;

    @FXML
    private Button oneBtn;

    @FXML
    private Button twoBtn;

    @FXML
    private Button threeBtn;

    @FXML
    private Button fourBtn;

    @FXML
    private Button fiveBtn;

    @FXML
    private Button sixBtn;

    @FXML
    private Button sevenBtn;

    @FXML
    private Button eightBtn;

    @FXML
    private Button nineBtn;

    @FXML
    private Button fracBtn;

    @FXML
    private Button equalBtn;

    @FXML
    private void calcButtonAction(ActionEvent event) {

        if (event.getSource() == ceBtn) {
            CalcSM.buttonAction("ceInput");
        } else if (event.getSource() == onBtn) {
            CalcSM.buttonAction("onInput");
        } else if (event.getSource() == negateBtn) {
            CalcSM.buttonAction("(-)");
        } else if (event.getSource() == exponentBtn) {
            CalcSM.buttonAction("^");
        } else if (event.getSource() == offBtn) {
            CalcSM.buttonAction("offInput");
        } else if (event.getSource() == divideBtn) {
            CalcSM.buttonAction("/");
        } else if (event.getSource() == multiplyBtn) {
            CalcSM.buttonAction("*");
        } else if (event.getSource() == subtractBtn) {
            CalcSM.buttonAction("-");
        } else if (event.getSource() == addBtn) {
            CalcSM.buttonAction("+");
        } else if (event.getSource() == zeroBtn) {
            CalcSM.buttonAction("0");
        } else if (event.getSource() == oneBtn) {
            CalcSM.buttonAction("1");
        } else if (event.getSource() == twoBtn) {
            CalcSM.buttonAction("2");
        } else if (event.getSource() == threeBtn) {
            CalcSM.buttonAction("3");
        } else if (event.getSource() == fourBtn) {
            CalcSM.buttonAction("4");
        } else if (event.getSource() == fiveBtn) {
            CalcSM.buttonAction("5");
        } else if (event.getSource() == sixBtn) {
            CalcSM.buttonAction("6");
        } else if (event.getSource() == sevenBtn) {
            CalcSM.buttonAction("7");
        } else if (event.getSource() == eightBtn) {
            CalcSM.buttonAction("8");
        } else if (event.getSource() == nineBtn) {
            CalcSM.buttonAction("9");
        } else if (event.getSource() == fracBtn) {
            CalcSM.buttonAction(".");
        } else if (event.getSource() == equalBtn) {
            CalcSM.buttonAction("=");
        } else if (event.getSource() == mrcBtn) {
            CalcSM.buttonAction("MRC");
        } else if (event.getSource() == mAddBtn) {
            CalcSM.buttonAction("M+");
        } else if (event.getSource() == mSubtractBtn) {
            CalcSM.buttonAction("M-");
        } else if (event.getSource() == percentBtn) {
            CalcSM.buttonAction("%");
        }

        // set textFields
        setTextFields();
    }

    private void setTextFields() {
        operationsTextField.setText(CalcSM.operationOutput());
        memoryTextField.setText(CalcSM.hasMemory());
        resultTextField.setText(CalcSM.resultOutput());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CalcSM.initialize();

        setTextFields();
    }

}
