package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    double prevNum;
    double total;
    String prevOp = "";
    boolean initState = true;
    String prevBtnType = "";

    TextView result;
    TextView operation_indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get views
        result = (TextView) findViewById(R.id.result_txtView);
        operation_indicator = (TextView) findViewById(R.id.operation_txtView);
        operation_indicator.setText("");
        //set initial number
        prevNum = Double.parseDouble(result.getText().toString());

    }

    /*
    * handle event when number buttons are pressed
    * */
    public void numBtnPressed(View view) {
        Button number = (Button) view;
        String num = number.getText().toString();
        addNum(num);
    }

    //all clear
    public void reset() {
        prevNum = 0;
        total= 0 ;
        prevOp = "";
        initState = true;
        prevBtnType = "";
        result.setText("0");
        operation_indicator.setText("");
    }

    // clear the number
    public void clear() {
        initState = true;
        result.setText("0");
    }

    /*
    * handle math operations
    * */
    public void operationBtnPressed(View view ) {

        //get text of this button
        Button operator_btn = (Button) view;
        String operation_name = operator_btn.getText().toString();
        if (operation_name.equals("AC")) {
            reset();
            return;
        }
        if(operation_name.equals("C")) {
            clear();
            return;
        }
        //indicate operation being performed
        operation_indicator.setText(operation_name);
        double currentNumber = 0;
        //get current number
        if (prevBtnType.equals("number")) {
             currentNumber = Double.parseDouble(result.getText().toString());
        }

        //Log.d("operation currentNumb", String.valueOf(currentNumber));
        //reset text edit back to initial state to override old value
        initState = true;
        //Log.d("total before op", String.valueOf(total));
        if(operation_name.equals("=")) {
            //Log.d("operation prevOp", prevOp);
            total = handleOperations(prevOp, prevNum, currentNumber);
            //Log.d("total after op=", String.valueOf(total));
            prevNum = currentNumber;
        } else {
            total = handleOperations(prevOp, total, currentNumber);
            //Log.d("total after op+", String.valueOf(total));
            prevNum = total;
        }

        //set result
        result.setText(Double.toString(total));
        prevOp = operation_name;
        prevBtnType = "operation";
    }

    public double handleOperations(String opName, double numb1, double numb2) {

        switch (opName) {
            case "+":
                return numb1 + numb2;
            case "-":
                return numb1 - numb2;
            case "/":
                if(numb2 != 0) {
                    return numb1/numb2;
                }
                return 0;
            case "*":
                return numb1*numb2;
            case "=":
                return total;
            default:
                return numb2;
        }
    }

    /*
    * Append number to view when number button pressed
    * @param num is the number being pressed
    * */
    public void addNum(String num) {
        String currentTxt = result.getText().toString();
        if (initState) {
            currentTxt = num;
            initState = false;
        } else {
            currentTxt += num;
        }
        result.setText(currentTxt);
        prevBtnType = "number";
    }

}
