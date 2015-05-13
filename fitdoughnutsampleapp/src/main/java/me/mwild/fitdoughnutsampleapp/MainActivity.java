package me.mwild.fitdoughnutsampleapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import me.mwild.fitdoughnut.FitDoughnut;


public class MainActivity extends ActionBarActivity {

    public FitDoughnut doughnut;
    public Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        doughnut = (FitDoughnut) findViewById(R.id.doughnut);

        myButton = (Button) findViewById(R.id.my_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                doughnut.animateSetPercent((float) Math.random() * 100);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
