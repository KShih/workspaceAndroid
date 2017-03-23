package com.example.kshih.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*
    txvshow is for showing input and result
    txvcalcu is for calculate
    isInput is to check if user input before pressing operator
 */
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnPlus, btnMinus, btnEqual, btnMult, btnDiv, btnDot;
    Button btnMc, btnMr, btnMp, btnMm, btnDel;
    Double num1,num2, result, mem1 = 0.0;
    TextView txvcalcu, txvshow;
    boolean isInput = false;
    char op, op_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInit();
        txvcalcu = (TextView)findViewById(R.id.txvcalcu);
        txvshow = (TextView)findViewById(R.id.txvshow);
    }

    private Button.OnClickListener Mylistener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnDel:
                    if(!isInput) break;
                    txvcalcu.setText("0");
                    txvshow.setText("0");
                    break;

                case R.id.btnPlus:
                    if(!isInput) break;
                    num1 = Double.parseDouble(txvcalcu.getText().toString()); // parse number1
                    txvshow.setText("+");
                    txvcalcu.setText("0");
                    op = '+';
                    break;

                case R.id.btnMinus:
                    if(!isInput) break;
                    num1 = Double.parseDouble(txvcalcu.getText().toString());
                    txvshow.setText("-");
                    txvcalcu.setText("0");
                    op = '-';
                    break;

                case R.id.btnMult:
                    if(!isInput) break;
                    num1 = Double.parseDouble(txvcalcu.getText().toString());
                    txvshow.setText("*");
                    txvcalcu.setText("0");
                    op = '*';
                    break;

                case R.id.btnDiv:
                    if(!isInput) break;
                    num1 = Double.parseDouble(txvcalcu.getText().toString());
                    txvshow.setText("/");
                    txvcalcu.setText("0");
                    op = '/';
                    break;

                case R.id.btnEqu:
                    if(!isInput) break;
                    num2 = Double.parseDouble(txvcalcu.getText().toString()); // parse number2
                    switch (op){
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            result = num1 / num2;
                            break;
                        default:
                            break;
                    }
                    if(result % 2 == 1 || result % 2 == 0)
                        txvshow.setText(num1.toString() + op + num2.toString() + "= " + String.valueOf(Math.round(result)));
                    else
                        txvshow.setText(num1.toString() + op + num2.toString() + "= " + String.valueOf(result));
                    txvcalcu.setText("0");
                    break;

                case R.id.btnMc:
                    if(!isInput) break;
                    mem1 = 0.0;
                    break;

                case R.id.btnMp:
                    if(!isInput) break;
                    mem1 = mem1 + result;
                    op_m = '+';
                    break;

                case R.id.btnMm:
                    if(!isInput) break;
                    mem1 = mem1 - result;
                    op_m = '-';
                    break;

                case R.id.btnMr:
                    if(!isInput) break;
                    if(mem1 % 2 == 1 || mem1 % 2 == 0)
                        txvshow.setText("Ｍemory " + op_m + result.toString() + "= " + String.valueOf(Math.round(mem1)));
                    else
                        txvshow.setText("Memory " + op_m + result.toString() + "= " + String.valueOf(mem1));
                    txvcalcu.setText("0");
                    mem1 = 0.0;
                    break;

                default:
                    if(txvcalcu.getText().toString().equals("0")) {
                        txvshow.setText(((Button) v).getText().toString());
                        txvcalcu.setText(((Button) v).getText().toString());
                        isInput = true;
                    }
                    else{
                        txvshow.setText(txvcalcu.getText().toString()+ ((Button)v).getText().toString());
                        txvcalcu.setText(txvcalcu.getText().toString()+ ((Button)v).getText().toString());
                        isInput = true;
                    }

            }
        }
    };
    void btnInit(){
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnEqual = (Button)findViewById(R.id.btnEqu);
        btnMult = (Button)findViewById(R.id.btnMult);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnDot = (Button)findViewById(R.id.btnDot);
        btnMc = (Button)findViewById(R.id.btnMc);
        btnMr = (Button)findViewById(R.id.btnMr);
        btnMp = (Button)findViewById(R.id.btnMp);
        btnMm = (Button)findViewById(R.id.btnMm);
        btnDel = (Button)findViewById(R.id.btnDel);

        btn0.setOnClickListener(Mylistener);
        btn1.setOnClickListener(Mylistener);
        btn2.setOnClickListener(Mylistener);
        btn3.setOnClickListener(Mylistener);
        btn4.setOnClickListener(Mylistener);
        btn5.setOnClickListener(Mylistener);
        btn6.setOnClickListener(Mylistener);
        btn7.setOnClickListener(Mylistener);
        btn8.setOnClickListener(Mylistener);
        btn9.setOnClickListener(Mylistener);
        btnPlus.setOnClickListener(Mylistener);
        btnMinus.setOnClickListener(Mylistener);
        btnEqual.setOnClickListener(Mylistener);
        btnMult.setOnClickListener(Mylistener);
        btnDiv.setOnClickListener(Mylistener);
        btnDot.setOnClickListener(Mylistener);
        btnMc.setOnClickListener(Mylistener);
        btnMr.setOnClickListener(Mylistener);
        btnMp.setOnClickListener(Mylistener);
        btnMm.setOnClickListener(Mylistener);
        btnDel.setOnClickListener(Mylistener);

    }


}
