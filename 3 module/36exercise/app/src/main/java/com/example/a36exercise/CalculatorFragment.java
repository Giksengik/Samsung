package com.example.a36exercise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonSubtraction;
    private Button buttonMultiplication;
    private Button buttonAddition;
    private Button buttonDivision;
    private Button buttonResult;
    private TextView textShow;
    private TextView textResult;
    private StringBuilder firstElement;
    private StringBuilder secondElement;
    private StringBuilder operation;
    @Override
    // Переопределяем метод onCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator_fragment, container, false);
        firstElement = new StringBuilder();
        secondElement = new StringBuilder();
        operation = new StringBuilder();
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        button0 = (Button) view.findViewById(R.id.button0);
        buttonDivision = (Button) view.findViewById(R.id.buttonDivision);
        buttonMultiplication = (Button) view.findViewById(R.id.buttonMultiplication);
        buttonAddition = (Button) view.findViewById(R.id.buttonAddition);
        buttonSubtraction = (Button) view.findViewById(R.id.buttonSubtraction);
        buttonResult = (Button) view.findViewById(R.id.buttonResult);
        textShow = (TextView) view.findViewById(R.id.textShow);
        textResult = (TextView) view.findViewById(R.id.textResult);
        View.OnClickListener digitListener = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Button toRead = view.findViewById(id);
                if (!operation.toString().equals("")) {
                    if (secondElement.length() < 10 && !(secondElement.length() == 0
                            && toRead.getText().toString().equals("0"))) {
                        secondElement.append(toRead.getText().toString());
                        String toAdd = textShow.getText().toString();
                        textShow.setText(toAdd + toRead.getText().toString());
                    }
                } else {
                    if (firstElement.length() < 10 && !(firstElement.length() == 0
                            && toRead.getText().toString().equals("0"))) {
                        firstElement.append(toRead.getText().toString());
                        String toAdd = textShow.getText().toString();
                        textShow.setText(toAdd + toRead.getText().toString());
                    }
                }
            }
        };
        View.OnClickListener operationListener = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Button toRead = view.findViewById(id);
                String text;
                if (!firstElement.toString().equals("")) {
                    if (toRead.getText().toString().equals("=") && !operation.toString().equals("") && !(secondElement.length() == 0)) {
                        switch (operation.toString()) {
                            case "+":
                                text =(Integer.parseInt(firstElement.toString()) + Integer.parseInt(secondElement.toString())) + "";
                                textResult.setText(text);
                                break;
                            case "-":
                                text =Integer.parseInt(firstElement.toString()) - Integer.parseInt(secondElement.toString()) + "";
                                textResult.setText(text);
                                                                break;
                            case "*":
                                text = (Integer.parseInt(firstElement.toString()) * Integer.parseInt(secondElement.toString())) + "";
                                textResult.setText(text);
                                break;
                            case "/":
                                text =(Integer.parseInt(firstElement.toString()) / Integer.parseInt(secondElement.toString())) + "";
                                textResult.setText(text);
                                break;
                        }
                        clear();
                    }
                    else if (operation.length() == 0) {
                        operation.append(toRead.getText().toString());
                        String toAdd = textShow.getText().toString();
                        textShow.setText(toAdd + toRead.getText().toString());
                    }
                }
            }
        };

        button1.setOnClickListener(digitListener);
        button0.setOnClickListener(digitListener);
        button2.setOnClickListener(digitListener);
        button3.setOnClickListener(digitListener);
        button4.setOnClickListener(digitListener);
        button5.setOnClickListener(digitListener);
        button6.setOnClickListener(digitListener);
        button7.setOnClickListener(digitListener);
        button8.setOnClickListener(digitListener);
        button9.setOnClickListener(digitListener);
        buttonResult.setOnClickListener(operationListener);
        buttonMultiplication.setOnClickListener(operationListener);
        buttonSubtraction.setOnClickListener(operationListener);
        buttonAddition.setOnClickListener(operationListener);
        buttonDivision.setOnClickListener(operationListener);
        return view;

    }
    private void clear() {
        firstElement.setLength(0);
        secondElement.setLength(0);
        operation.setLength(0);
        textShow.setText("");
    }

}
