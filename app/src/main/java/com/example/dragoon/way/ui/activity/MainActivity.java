package com.example.dragoon.way.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragoon.way.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button save;
    EditText name, email, description;
    TextView kiir;

    public static class SingletonSession {

        private static SingletonSession instance;
        private String username;
        //no outer class can initialize this class's object
        private SingletonSession() {}

        public static SingletonSession Instance()
        {
            //if no instance is initialized yet then create new instance
            //else return stored instance
            if (instance == null)
            {
                instance = new SingletonSession();
            }
            return instance;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


    public static class SingletonSession1 {

        private static SingletonSession1 instance1;
        private String password;
        //no outer class can initialize this class's object
        private SingletonSession1() {}

        public static SingletonSession1 Instance1()
        {
            //if no instance is initialized yet then create new instance
            //else return stored instance
            if (instance1 == null)
            {
                instance1 = new SingletonSession1();
            }
            return instance1;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String password) {
            this.password = password;
        }
    }

    public static class SingletonSession2 {

        private static SingletonSession2 instance2;
        private String description;
        //no outer class can initialize this class's object
        private SingletonSession2() {}

        public static SingletonSession2 Instance2()
        {
            //if no instance is initialized yet then create new instance
            //else return stored instance
            if (instance2 == null)
            {
                instance2 = new SingletonSession2();
            }
            return instance2;
        }

        public String getDescription() {
            return description;
        }

        public void setUsername(String description) {
            this.description = description;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kiir=(TextView)findViewById(R.id.textView4);
        save = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);
        description = (EditText) findViewById(R.id.editText3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {
                  //  ProgressDialog dialog = ProgressDialog.show(MainActivity.this, null, "Loading...");
                    try {
                        String rawname1 = name.getText().toString();
                        String rawemail = email.getText().toString();
                        String rawdescription = email.getText().toString();
                        SharedPreferences memory = getSharedPreferences("memory", 0);
                        JSONObject json = new JSONObject();
                        SharedPreferences.Editor edit = memory.edit();
                        edit.clear();
                        edit.putString("username", rawname1);
                        edit.putString("email", rawemail);
                        edit.putString("megjegyzes", rawdescription);
                        edit.commit();
                        Toast.makeText(getApplicationContext(), "Login details are saved..", Toast.LENGTH_SHORT).show();
                        json.put("nev", rawname1);
                        json.put("email", rawemail);
                        json.put("megjegyzes", rawdescription);
                        SingletonSession.Instance().setUsername(name.getText().toString());
                        SingletonSession1.Instance1().setUsername(email.getText().toString());
                        SingletonSession2.Instance2().setUsername(description.getText().toString());
                        kiir.setText(SingletonSession.instance.username);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private boolean isFormValid() {
        boolean valid = true;
        String name1 = name.getText().toString();
        if (name1.length() <= 2) {
            name.setError("Rövid a név");
            valid = false;
        } else {
            name.setError(null);
        }
        String rawemail = email.getText().toString();
        if (!TextUtils.isEmpty(rawemail) && android.util.Patterns.EMAIL_ADDRESS.matcher(rawemail).matches()) {
            email.setError(null);
        } else {
            email.setError("Nem valós email cím");
            valid = false;
        }
        return valid;
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
