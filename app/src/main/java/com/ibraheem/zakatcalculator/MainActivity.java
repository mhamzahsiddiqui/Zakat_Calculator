package com.ibraheem.zakatcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    double nisab =345958.14;
    double gval=0.0,gquan=0.0,sval=0.0,squan=0.0,amt=0.0,tamt=0.0,zpaid=0.0;
    EditText gvalInput;
    EditText gquanInput;
    EditText svalInput;
    EditText squanInput;
    EditText amtInput;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View view) {*/

                @Override
            public void onClick(View view) {
               Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{"raza.ibraheem1997@gmail.com"});  //developer 's email
            Email.putExtra(Intent.EXTRA_SUBJECT,
                    "Contact"); // Email 's Subject
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Ibraheem Raza," + "");  //Email 's Greeting text
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            //}
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gvalInput = (EditText)findViewById(R.id.gvalInput);
        gquanInput = (EditText)findViewById(R.id.gquanInput);
        svalInput = (EditText)findViewById(R.id.svalInput);
        squanInput = (EditText)findViewById(R.id.squanInput);
        amtInput = (EditText)findViewById(R.id.amtInput);

        TextView nisabd = (TextView) findViewById(R.id.nisab);
        nisabd.setText(Double.toString(nisab));

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gval = Double.parseDouble(gvalInput.getText().toString());
                gquan = Double.parseDouble(gquanInput.getText().toString());
                sval = Double.parseDouble(svalInput.getText().toString());
                squan = Double.parseDouble(squanInput.getText().toString());
                amt = Double.parseDouble(amtInput.getText().toString());

                tamt=((gval/10)*gquan)+((sval/10)*squan)+amt;
                if(tamt>nisab) {
                    zpaid = (0.025 * (tamt - nisab));
                    TextView amtpaid = (TextView) findViewById(R.id.amtpd);
                    //TextView tamt = (TextView) findViewById(R.id.tam);
                    //tamt.setText(Double.toString(amt));

                    amtpaid.setText(Double.toString(zpaid));
                }
                else if(tamt<nisab){
                    TextView amtpaid = (TextView) findViewById(R.id.amtpd);
                    String display = "You are not entitled to pay zakat";
                    amtpaid.setText(display);
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            String url = "https://www.google.com/search?q=what+is+zakat";

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);

            //pass the url to intent data
            intent.setData(Uri.parse(url));

            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            String url = "https://www.islamicfinder.org/zakat-calculator/";

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);

            //pass the url to intent data
            intent.setData(Uri.parse(url));

            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            String url = "https://www.muis.gov.sg/Zakat/Calculation-and-Payment/Calculate-Your-Zakat/Past-Nisab-Values";

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);

            //pass the url to intent data
            intent.setData(Uri.parse(url));

            startActivity(intent);



        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToUrl(View view){

        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }
}

