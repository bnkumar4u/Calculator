package com.example.android.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String s="";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.result);
        Thread t=new Thread(){


            @Override
            public void run(){

                while(!isInterrupted())
                {

                    try {
                        Thread.sleep(10);  //1000ms = 1 sec
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                textView.setText(s);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();
    }

    public void zero(View v) {   s=s+"0"; }
    public void one(View v) {   s=s+"1"; }
    public void two(View v) {   s=s+"2"; }
    public void three(View v) {   s=s+"3"; }
    public void four(View v) {   s=s+"4"; }
    public void five(View v) {   s=s+"5"; }
    public void six(View v) {   s=s+"6"; }
    public void seven(View v) {   s=s+"7"; }
    public void eight(View v) {   s=s+"8"; }
    public void nine(View v) {   s=s+"9"; }
    public void plus(View v) {   s=s+"+"; }
    public void minus(View v) {   s=s+"-"; }
    public void multiply(View v) {   s=s+"*"; }
    public void divide(View v) {   s=s+"/"; }
    public void modulus(View v) {   s=s+"%"; }
    public void dot(View v) {   s=s+"."; }
    public void clear(View v){s="";}
    public void open(View v){s=s+"(";}
    public void close(View v){s=s+")";}
    public void delete(View v)
    {
        if(s.equals("Answer will be displayed here!"))
            s="";
        else if(s.length()>0)
        s=s.substring(0,s.length()-1);
    }

    public void postfix(View v)
    {
        InfixToPostfix infixToPostfix=new InfixToPostfix();
        String[] pf=infixToPostfix.toPostfix(s);
        String temp="";
        for (int i=0;i<pf.length;i++)
        {
            temp=temp+pf[i]+" ";
        }
        s=temp;

    }
    public void prefix(View v)
    {
        InfixToPrefix infixToPrefix=new InfixToPrefix();
        String[] pf=infixToPrefix.toPrefix(s);
        String temp="";
        for (int i=(pf.length-1);i>=0;i++)
        {
            temp=temp+pf[i]+" ";
        }
        s=temp;
    }


    public void equals(View v)
    {
        InfixToPostfix infixToPostfix=new InfixToPostfix();
        double ans=infixToPostfix.evalute(s);
        s=""+ans;
    }



}
