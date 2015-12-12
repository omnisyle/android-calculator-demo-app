package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int prevNum;
    int total;
    String prevOp = "";
    boolean initState = true;

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
        prevNum = Integer.parseInt(result.getText().toString());

    }

    /*
    * handle event when number buttons are pressed
    * */
    public void numBtnPressed(View view) {
        Button number = (Button) view;
        String num = number.getText().toString();
        addNum(num);
    }

    /*
    * handle math operations
    * */
    public void operationBtnPressed(View view ) {

        //get text of this button
        Button operator_btn = (Button) view;
        String operation_name = operator_btn.getText().toString();

        //indicate operation being performed
        operation_indicator.setText(operation_name);

        //get current number
        int currentNumber = Integer.parseInt(result.getText().toString());

        //reset text edit back to initial state to override old value
        initState = true;

        if(operation_name.equals("=")) {
            Log.d("operation prevOp", prevOp);
            total = handleOperations(prevOp, prevNum, currentNumber);
            result.setText(String.valueOf(total));
        }
        prevNum = currentNumber;
        prevOp = operation_name;
    }

    public int handleOperations(String opName, int numb1, int numb2) {

        switch (opName) {
            case "+":
                return numb1 + numb2;
            case "-":
                return numb1 - numb2;
            case "/":
                return numb1/numb2;
            case "*":
                return numb1*numb2;
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


    }

}
