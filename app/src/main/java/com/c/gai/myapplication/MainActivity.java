package com.c.gai.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.math.*;

import java.math.*;

public class MainActivity extends AppCompatActivity {


    private EditText Scr;                      //textbox screen
    private float NumbeBefore;                     //save screen before presing button operation;
    private String Operation = null;          //will represent the operation
    private ButtonClickListener buttonClick;   // new class i created
    private int Status = 0;     /*check the status to avoid errors.
                                 will recive 0 at the begining,1 when we  recive numbers ,
                                 2 for,after we recive operation,3 for results function.
                                  */
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("gc", "on create");

        Scr = (EditText) findViewById(R.id.editText); // will represent the value on the screen

        //int idList[] will the buttons
        int idList[] = {R.id.Button0, R.id.Button1, R.id.Button2, R.id.Button3,
                R.id.Button4, R.id.Button5, R.id.Button6, R.id.Button7,
                R.id.Button8, R.id.Button9, R.id.ButtonAdd, R.id.ButtonC, R.id.ButtonSub,
                R.id.ButtonCE, R.id.ButtonDiv, R.id.ButtonEqual, R.id.ButtonDot,
                R.id.ButtonMult, R.id.ButtonSqrt, R.id.ButtonSquare, R.id.ButtonSub};
        Log.d("gc", "array initialized");

        for (int id : idList) {
            Button button = (Button) findViewById(id);
            button.setOnClickListener(new ButtonClickListener());
        }
        Log.d("gc", "array finish initialized");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Calculate(String str) {
        Log.d("gc", "Calculate was in function");
        if (Status == 0) {
            return;
        } else if (Status == 2) {
            return;
        }
        NumbeBefore = Float.parseFloat(Scr.getText().toString());
        Operation = str; //save operation
        Scr.setText(""); // clear screen
        Status = 2;
        Log.d("gc", "status is 2");
    }


    private void getKeyboard(String numb) {
        Status = 1;
        Log.d("gc", "status is 1");
        String ScrCurrent = Scr.getText().toString();
        ScrCurrent += numb;
        Scr.setText(ScrCurrent);
    }

    //this func make checks and prints the proper value to the screen
    private void myReuslt() {
        Log.d("gc", "my result was in function");
        if ((Status == 0) || (Status == 3) || (Status == 2)) {
            return;
        }

        if ((Status == 1) && (Operation == null)) {
            return;
        }
        float NumAf = Float.parseFloat(Scr.getText().toString()); //convert to float the input string from the EditText
        float result = 0;
        if (Operation.equals("+")) {
            result = NumAf + NumbeBefore;
        }
        if (Operation.equals("-")) {
            result = NumbeBefore - NumAf;
        }
        if (Operation.equals("*")) {
            result = NumAf * NumbeBefore;
        }
        if (Operation.equals("/")) {
            result = NumbeBefore / NumAf;
        }
        if (Operation.equals("sq")) {
            Math.sqrt(NumAf);
            ;
        }
        if (Operation.equals("x^2")) {
            Log.d("gc", "x^2 was in function");
            result = (float) Math.pow((float) NumAf, 2.0);
            Log.d("gc", "result of x^2 is " + result);
        }
        if ((result == 0) && (Operation.equals("null"))) {
            result = NumbeBefore;
        }
        Status = 3;
        Log.d("gc", "status is 3");
        //check if the output is natural number,to print it clearly

        if ((int) result == result) {
            int temp = (int) result;
            Scr.setText((String.valueOf(temp)));
            Log.d("gc", "try to conert" + "temp = " + temp + "result = " + result);
        } else {
            Scr.setText((String.valueOf(result)));

        }


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.c.gai.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.c.gai.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class ButtonClickListener implements View.OnClickListener {

        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.ButtonC:
                    Log.d("gc", "button c was clicked");
                    String ScrCurrent = Scr.getText().toString();
                    int i = ScrCurrent.length();
                    if (i >= 1) {
                        ScrCurrent = ScrCurrent.substring(0, i - 1);
                    }
                    Scr.setText(ScrCurrent);
                    break;
                case R.id.ButtonCE:
                    Log.d("gc", "button ce was clicked");
                    Scr.setText("");
                    NumbeBefore = 0;
                    Operation = null;
                    Status = 0;
                    Log.d("gc", "status is 0");
                    break;
                case R.id.ButtonAdd:
                    Log.d("gc", "button add was clicked");
                    Calculate("+");
                    break;
                case R.id.ButtonSub:
                    Log.d("gc", "button sub was clicked");
                    Calculate("-");
                    break;
                case R.id.ButtonDiv:
                    Log.d("gc", "button div was clicked");
                    Calculate("/");
                    break;
                case R.id.ButtonMult:
                    Log.d("gc", "button mult was clicked");
                    Calculate("*");
                    break;
                case R.id.ButtonEqual:
                    Log.d("gc", "button equal was clicked");
                    myReuslt();
                    break;
                case R.id.ButtonSqrt:
                    break;
                case R.id.ButtonSquare:;
                    break;
                default:
                    String numb = ((Button) v).getText().toString();
                    Log.d("gc", "button  " + numb + "  was clicked");
                    getKeyboard(numb);
                    break;


            }

        }

    }

    public void mailtome(View v) {
        Log.d("gc", "email send func");
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        String[] to = {"ggcarmi@gmail.com", "ggcarmi@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL, to);
        i.putExtra(Intent.EXTRA_SUBJECT, "hi this is email from my app");
        i.putExtra(Intent.EXTRA_TEXT, "this is the content of my mail");
        i.setType("message/rfc822");
        Intent chooser = Intent.createChooser(i, "Send Email");
        startActivity(chooser);

    }


}
