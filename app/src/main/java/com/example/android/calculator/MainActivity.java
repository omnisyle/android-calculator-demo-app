package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    int tempNumb;
    // initial state of the calculator
    boolean reset = true;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get result textview
        result = (TextView) findViewById(R.id.result_txtView);

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
    * operations = ['add_btn', 'minus_btn', 'div_btn', 'mult_btn']
    * */
    public void operationBtnPressed(View view ) {
        String operation_name = getResources().getResourceEntryName(view.getId());

        int currentNum = Integer.parseInt(result.getText().toString());
        if(tempNumb == 0) {
            tempNumb = currentNum;
        }
        switch (operation_name) {
            case "add_btn":

                break;
            case "minus_btn":
                break;
            case "div_btn":
                break;
            case "mult_btn":
                break;
            default:
                break;
        }
    }

    public int add(int a, int b) {
        return a+b;
    }

    public int mult(int a, int b) {
        return a*b;
    }

    public int div(int a, int b) {
        return a/b;
    }

    public int subtract(int a, int b) {

        return a-b;
    }

    /*
    * Append number to view when number button pressed
    * */
    public void addNum(String num) {
        String currentTxt = result.getText().toString();
        Log.d("Calculator current text", currentTxt);
        if (currentTxt.equals("0")) {
            result.setText(num);
        } else {
            currentTxt += num;
            result.setText(currentTxt);
        }
    }

}
