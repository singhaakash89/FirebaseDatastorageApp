package com.app.firebase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.firebase.model.Contacts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        AsyncTask<Void, String, ArrayList<Contacts>> mUserImageURLgeneration = new AsyncTask<Void, String, ArrayList<Contacts>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Fetching JSON data");
                progressDialog.show();
            }

            @Override
            protected ArrayList<Contacts> doInBackground(Void... params) {
                ObjectMapper objectMapper = new ObjectMapper();
                Contacts contacts = null;
                ArrayList<Contacts> contactsArrayList = new ArrayList<>();
                try {
//                    //URL url = new URL("http://private-b08d8d-nikitest.apiary-mock.com/contacts");
//                    String jsonString = null;
//                    InputStream is = getAssets().open("contacts.json");
//                    int size = is.available();
//                    byte[] buffer = new byte[size];
//                    is.read(buffer);
//                    is.close();
//                    jsonString = new String(buffer, "UTF-8");

//                    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//                    //JSONObject obj = new JSONObject(jsonString);
//                    JSONArray jsonArray = new JSONArray(jsonString);
//                    System.out.println("jsonArray.length :" + jsonArray.length() + " null");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        contacts = new Contacts();
//                        //System.out.println("value getString: "+jsonObject.getString("name")+ " -  null");
//                        System.out.println("value optString: " + jsonObject.optString("name") + " -  null");
//
//                        //storing
//                        contacts.setName(jsonObject.getString("name"));
//                        contacts.setEmail(jsonObject.getString("email"));
//                        contacts.setPhone(jsonObject.getInt("phone"));
//                        contacts.setOfficePhone(jsonObject.getInt("officePhone"));
//                        contacts.setLatitude(jsonObject.getDouble("latitude"));
//                        contacts.setLongitude(jsonObject.getDouble("longitude"));
//
//                        //printing
//                        // System.out.println("contacts.getName" + contacts.getName() + "null");
//                        System.out.println("contacts.getEmail" + contacts.getEmail() + "null");
//                        System.out.println("contacts.getPhone" + contacts.getPhone() + "null");
//                        System.out.println("contacts.getOfficePhone" + contacts.getOfficePhone() + "null");
//                        System.out.println("contacts.getLatitude" + contacts.getLatitude() + "null");
//                        System.out.println("contacts.getLatitude" + contacts.getLongitude() + "null");
//
//                        //Add your values in your `ArrayList` as below:
//                        contactsArrayList.add(contacts);
//                    }
//                    //contactsArrayList = objectMapper.readValue(url, new TypeReference<ArrayList<Contacts>>(){});
//                    publishProgress("Json list fetched...");


                    HttpHandler httpHandler = new HttpHandler();
                    String url = "http://api.androidhive.info/contacts/";
                    String url_n1 = "http://private-b08d8d-nikitest.apiary-mock.com/contacts";
                    String jsonString = httpHandler.makeServiceCall(url_n1);

                    Log.d("Response from url: ", "" + jsonString);
                    if (jsonString != null) {
                        try {
                            //first approach
//                            JSONObject jsonObj = new JSONObject(jsonString);
//                            // Getting JSON Array node
//                            JSONArray jsonArray = jsonObj.getJSONArray("contacts");

                            //second approch
                            JSONArray jsonArray = new JSONArray(jsonString);
                            JSONArray jsonArray_n1 = jsonArray.getJSONArray(0);

                            // looping through All Contacts
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //JSONObject c = jsonArray.getJSONObject(i);
                                JSONObject c = jsonArray_n1.getJSONObject(i);
                                //JSONArray jsonArray_n1 = jsonObject.getJSONArray("contacts");
                                String id = c.getString("id");
                                String name = c.getString("name");
                                String email = c.getString("email");
                                String address = c.getString("address");
                                String gender = c.getString("gender");

                                System.out.println(c.getString("id"));
                                System.out.println(c.getString("name"));
                                System.out.println(c.getString("email"));
                                System.out.println(c.getString("address"));
                                System.out.println(c.getString("gender"));

                                // Phone node is JSON Object
                                JSONObject phone = c.getJSONObject("phone");
                                String mobile = phone.getString("mobile");
                                String home = phone.getString("home");
                                String office = phone.getString("office");

                                // tmp hash map for single contact
                                HashMap<String, String> contact = new HashMap<>();

                                // adding each child node to HashMap key => value
                                contact.put("id", id);
                                contact.put("name", name);
                                contact.put("email", email);
                                contact.put("mobile", mobile);

                                // adding contact to contact list
                                //contactList.add(contact);
                            }
                            publishProgress("Json list fetched...");
                            return contactsArrayList;


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return contactsArrayList;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                progressDialog.setMessage(values[0]);
            }

            @Override
            protected void onPostExecute(ArrayList<Contacts> contactsArrayList) {
                progressDialog.dismiss();
            }
        };

        mUserImageURLgeneration.execute();
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

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        mDatabase.child("users").child(userId).setValue(user);
    }
}
