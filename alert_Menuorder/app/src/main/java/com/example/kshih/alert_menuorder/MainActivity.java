package com.example.kshih.alert_menuorder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String name = "";
    int count = 0;
    int total = 0;
    int g_ordertotal = 0;
    int pos = 0;
    Toast tos;
    String str_count;
    Spinner spin_drink;
    Spinner spin_count;
    Map<String,String> map_Menu = new HashMap<String, String>();
    Map<String,String> map_Total = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_drink = (Spinner)findViewById(R.id.spin_drink);
        spin_count = (Spinner)findViewById(R.id.spin_count);

        ArrayAdapter adapterDrink = ArrayAdapter.createFromResource(this,R.array.set_drink,R.layout.spinner_style);
        ArrayAdapter adapterCount = ArrayAdapter.createFromResource(this,R.array.set_count,R.layout.spinner_style);

        adapterDrink.setDropDownViewResource(R.layout.spinner_style);
        spin_drink.setAdapter(adapterDrink);
        spin_drink.setOnItemSelectedListener(spin_DrinkListener);

        adapterCount.setDropDownViewResource(R.layout.spinner_style);
        spin_count.setAdapter(adapterCount);
        spin_count.setOnItemSelectedListener(spin_CountListener);

        Button btn_ord = (Button)findViewById(R.id.btn_ord);
        Button btn_chk = (Button)findViewById(R.id.btn_chk);
        btn_ord.setOnClickListener(Mylistener);
        btn_chk.setOnClickListener(Mylistener);

        tos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
    }
    private Spinner.OnItemSelectedListener spin_DrinkListener = new Spinner.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            name = parent.getSelectedItem().toString();
            pos = position;
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private Spinner.OnItemSelectedListener spin_CountListener = new Spinner.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            str_count = parent.getSelectedItem().toString();
            count = Integer.parseInt(str_count);
            int[] set_price = {10,20,30,40,50,60,70,80,90,100};
            total = set_price[pos]*count;
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Button.OnClickListener Mylistener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_ord:
                    int[] set_price = {10,20,30,40,50,60,70,80,90,100};
                    str_count = spin_count.getSelectedItem().toString();
                    count = Integer.parseInt(str_count);
                    total = set_price[pos]*count;

                    String str = name + String.valueOf(count) + "杯, 共" + String.valueOf(total) + "元";
                    tos.setText(str);
                    tos.show();

                    map_Menu.put(name,str_count);
                    map_Total.put(name,String.valueOf(total));
                    break;
                case R.id.btn_chk:
                    if(map_Menu.size() == 0)
                        Toast.makeText(getApplicationContext(),"訂單不可為空", Toast.LENGTH_SHORT).show();
                    else{
                        //用一個arrayAdapter紀錄訂購結果，利用getData()方式回傳訂購結果
                        SimpleAdapter arrayAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.order,
                                new String[]{"Drink", "Count", "Price"}, new int[]{R.id.order_drink,R.id.order_count,R.id.order_price}); // SimpleAdper 接收ListView的型別

                        new AlertDialog.Builder(MainActivity.this).setTitle("帳單")
                                .setCancelable(false)
                                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final View pay = LayoutInflater.from(MainActivity.this).inflate(R.layout.pay,null);
                                        new AlertDialog.Builder(MainActivity.this).setTitle("輸入顧客支付的金額")
                                            .setCancelable(false)
                                            .setView(pay)
                                            .setPositiveButton("付款", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    EditText editText = (EditText)pay.findViewById(R.id.editText);
                                                    int pay = Integer.parseInt(editText.getText().toString()); // 讀入顧客輸入的金額

                                                    if(g_ordertotal > pay){
                                                        tos.setText("您所支付的金額不足，請重新輸入");
                                                        tos.show();
                                                    }
                                                    else{
                                                        pay = pay - g_ordertotal;
                                                        tos.setText("需找您"+String.valueOf(pay)+"元，謝謝惠顧");
                                                        tos.show();
                                                        map_Menu.clear();
                                                    }
                                                }
                                            }).show();
                                    }
                                })
                                .setNegativeButton("重新輸入", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        map_Menu.clear();

                                    }
                                })
                                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                    break;
            }
        }
    };

    private List getData(){
        List list = new ArrayList();
        Map maps = new HashMap();
        int counttotal = 0;
        int ordertotal = 0;
        maps.put("Drink","飲料");
        maps.put("Count","總數");
        maps.put("Price","價格");
        list.add(maps);
        for(Map.Entry<String,String> entry : map_Menu.entrySet()){
            Map map = new HashMap();
            map.put("Drink",entry.getKey());
            map.put("Count",entry.getValue());
            counttotal += Integer.parseInt(entry.getValue());
            // 填入單項飲品的總金額
            for(Map.Entry<String,String> temp : map_Total.entrySet()){
                if(entry.getKey() == temp.getKey()) {
                    map.put("Price", temp.getValue());
                    ordertotal += Integer.parseInt(temp.getValue());
                    break;
                }
            }
            maps.put("Drink","共計");
            maps.put("Count",Integer.toString(counttotal));
            maps.put("Price",Integer.toString(ordertotal));
            list.add(map);

        }
        g_ordertotal = ordertotal;
        return list;
    }





}
