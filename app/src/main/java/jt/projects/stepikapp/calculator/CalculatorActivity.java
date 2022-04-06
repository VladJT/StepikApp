package jt.projects.stepikapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import jt.projects.stepikapp.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <b>Состояния Activity</b>
 * начальное -> Created -> Started -> Resumed -> Paused -> Stopped -> Destroyed
 */

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber1, etNumber2, etOperation;
    TextView tvResult;
    Button btnCalculateResult;
    Toast toastError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initComponents();
    }

    private void initComponents() {
        etNumber1 = findViewById(R.id.editTextNumber1);
        etNumber2 = findViewById(R.id.editTextNumber2);
        etOperation = findViewById(R.id.editTextOperation);
        tvResult = findViewById(R.id.textViewResult);
        btnCalculateResult = findViewById(R.id.buttonResult);
        btnCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            float number1, number2;
            number1 = Float.parseFloat(etNumber1.getText().toString());
            number2 = Float.parseFloat(etNumber2.getText().toString());
            String resultText = "";

            String oper = etOperation.getText().toString();
            switch (oper) {
                case "+":
                    resultText = String.valueOf(number1 + number2);
                    break;
                case "-":
                    resultText = String.valueOf(number1 - number2);
                    break;
                case "/":
                    if (number2 == 0) {
                        toastError = Toast.makeText(this, R.string.divide_zero, Toast.LENGTH_SHORT);
                        toastError.show();
                        resultText = "!!!";
                    } else
                        resultText = String.valueOf(number1 / number2);
                    break;
                case "*":
                    resultText = String.valueOf(number1 * number2);
                    break;
                default:
                    resultText = "!!!";
                    toastError = Toast.makeText(this, "некорректные данные", Toast.LENGTH_SHORT);
                    toastError.show();
            }
            tvResult.setText(number1 + " " + oper + " " + number2 + " = " + resultText);
        } catch (Exception e) {
            toastError = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toastError.show();
        }
    }
}