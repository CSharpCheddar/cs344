package com.example.party_maps.ui.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.party_maps.MainActivity;
import com.example.party_maps.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final CheckBox registering = findViewById(R.id.checkBox);

        // check if user is logged in already
        final MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getApplicationContext());
        final SQLiteDatabase rdb = mdbh.getReadableDatabase();
        Cursor c = rdb.rawQuery("SELECT * FROM " + MyDBContract.DBEntry.TABLE_NAME, null);
        // Log the user in with default or given creds.
        // If the user's creds aren't saved, save them
        c.moveToFirst();
        if (c.getCount() == 1) {
            final String username = c.getString(c.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_USERNAME));
            final String password = c.getString(c.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD));
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String loginurl ="http://52.70.125.49//login.php?user=" + username +
                    "&pass=" + password;
            loginurl = loginurl.replaceAll("\\s", "zzzz");
            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET, loginurl, new Response.Listener<String>() {
                        public void onResponse(String response){
                            if (response.equals("Logged in")) {
                                Toast.makeText(getApplicationContext(), "Welcome back " + username, Toast.LENGTH_LONG).show();
                                // Show main screen
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                        },
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError er){
                            Toast.makeText(getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
            );
            queue.add(stringRequest);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SQLiteDatabase wdb = mdbh.getWritableDatabase();
                wdb.delete(MyDBContract.DBEntry.TABLE_NAME, null, null);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                if (registering.isChecked()) {
                    // try to register user, error otherwise
                    RequestQueue queue2 = Volley.newRequestQueue(getApplicationContext());
                    String registerurl ="http://52.70.125.49//register.php?user=" + usernameEditText.getText().toString() +
                            "&pass=" + passwordEditText.getText().toString();
                    registerurl = registerurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest2 = new StringRequest(
                            Request.Method.GET, registerurl, new Response.Listener<String>() {
                        public void onResponse(String response){
                            if (response.equals("Registered")) {
                                Toast.makeText(getApplicationContext(),
                                        response, Toast.LENGTH_LONG).show();
                                // insert info into db
                                ContentValues values = new ContentValues();
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_USERNAME, usernameEditText.getText().toString());
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, passwordEditText.getText().toString());
                                long rowId = wdb.insert(
                                        MyDBContract.DBEntry.TABLE_NAME,
                                        null,
                                        values);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Username taken", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError er){
                            Toast.makeText(getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
                    );
                    queue2.add(stringRequest2);

                } else {
                    String loginurl ="http://52.70.125.49//login.php?user=" + usernameEditText.getText().toString() +
                            "&pass=" + passwordEditText.getText().toString();
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        public void onResponse(String response){
                            if (response.equals("Logged in")) {
                                // Display welcome message
                                Toast.makeText(getApplicationContext(), "Welcome back " +
                                        usernameEditText.getText().toString(), Toast.LENGTH_LONG).show();
                                // insert info into db
                                ContentValues values = new ContentValues();
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_USERNAME, usernameEditText.getText().toString());
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, passwordEditText.getText().toString());
                                long rowId = wdb.insert(
                                        MyDBContract.DBEntry.TABLE_NAME,
                                        null,
                                        values);
                                // Show main screen
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        response, Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError er){
                            Toast.makeText(getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
                    );
                    queue.add(stringRequest);
                }
            }
        });
    }
}
