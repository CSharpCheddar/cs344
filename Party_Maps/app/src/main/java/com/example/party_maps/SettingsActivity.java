package com.example.party_maps;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.party_maps.ui.login.LoginActivity;
import com.example.party_maps.ui.login.MyDBContract;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getActivity().getApplicationContext());
            SQLiteDatabase rdb = mdbh.getReadableDatabase();
            final SQLiteDatabase wdb = mdbh.getWritableDatabase();
            Cursor c = rdb.rawQuery("SELECT * FROM " + MyDBContract.DBEntry.TABLE_NAME, null);
            c.moveToFirst();
            final String username = c.getString(c.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_USERNAME));
            final String password = c.getString(c.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD));
            findPreference("change_password").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    final String newpass = ((EditTextPreference)preference).getText();
                    String loginurl ="http://52.70.125.49//changepass.php?user=" + username +
                            "&pass=" + password + "&newpass=" + newpass;
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Password changed")) {
                                wdb.delete(MyDBContract.DBEntry.TABLE_NAME, null, null);
                                ContentValues values = new ContentValues();
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_USERNAME, username);
                                values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, newpass);
                                long rowId = wdb.insert(
                                        MyDBContract.DBEntry.TABLE_NAME,
                                        null,
                                        values);
                                ((EditTextPreference)findPreference("change_password")).setText("");
                            }
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("add_friend").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    final String newpass = ((EditTextPreference)preference).getText();
                    String loginurl ="http://52.70.125.49//addfriend.php?user1=" + username +
                            "&user2=" + ((EditTextPreference)preference).getText();
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("remove_friend").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    final String newpass = ((EditTextPreference)preference).getText();
                    String loginurl ="http://52.70.125.49//delfriend.php?user1=" + username +
                            "&user2=" + ((EditTextPreference)preference).getText();
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Friendship deleted")) {
                                ((EditTextPreference)preference).setText("");

                            }
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("conversation").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    String loginurl ="http://52.70.125.49//message.php?sender=" + username + "&receiver=" +
                            ((EditTextPreference)findPreference("start_conversation")).getText() +
                            "&message=" + ((EditTextPreference)preference).getText();
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("delete_conversation").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(final Preference preference, Object newValue) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    String loginurl ="http://52.70.125.49//delmessages.php?user1=" +
                            ((EditTextPreference)preference).getText() + "&user2=" + username;
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("delete_account").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    String loginurl ="http://52.70.125.49//deregister.php?user=" + username +
                            "&pass=" + password;
                    loginurl = loginurl.replaceAll("\\s", "zzzz");
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Account deleted")) {
                                wdb.delete(MyDBContract.DBEntry.TABLE_NAME, null, null);
                                startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                            }
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                    return true;
                }
            });
            findPreference("log_out").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    wdb.delete(MyDBContract.DBEntry.TABLE_NAME, null, null);
                    startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                    return true;
                }
            });
            findPreference("hoster_mode").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (((SwitchPreferenceCompat)preference).isChecked()) {
                        preference.setEnabled(false);
                        findPreference("handle_title").setEnabled(false);
                        findPreference("change_address").setEnabled(false);
                        final String handle = ((EditTextPreference)findPreference("handle_title")).getText();
                        final String address = ((EditTextPreference)findPreference("change_address")).getText();
                        if (handle == null || address == null || handle.equals("") || address.equals("")) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "No handle or address set", Toast.LENGTH_LONG).show();
                            ((SwitchPreferenceCompat)preference).setChecked(false);
                            findPreference("handle_title").setEnabled(true);
                            findPreference("change_address").setEnabled(true);
                        } else {
                            RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                            String loginurl ="http://52.70.125.49//addaddress.php?hoster=" + username +
                                    "&handle=" + handle + "&address=" + address;
                            loginurl = loginurl.replaceAll("\\s", "zzzz");
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, loginurl,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(getActivity().getApplicationContext(),
                                                    response, Toast.LENGTH_LONG).show();
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    ((SwitchPreferenceCompat)findPreference("hoster_mode")).setChecked(false);
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Oops, an error occurred", Toast.LENGTH_LONG).show();
                                    findPreference("handle_title").setEnabled(true);
                                    findPreference("change_address").setEnabled(true);
                                }
                            });
                            queue.add(stringRequest);
                        }
                        preference.setEnabled(true);
                    } else {
                        preference.setEnabled(false);
                        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        String loginurl ="http://52.70.125.49//deladdress.php?hoster=" + username;
                        loginurl = loginurl.replaceAll("\\s", "zzzz");
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginurl,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                response, Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ((SwitchPreferenceCompat)findPreference("hoster_mode")).setChecked(true);
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Oops, an error occurred", Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(stringRequest);
                        findPreference("handle_title").setEnabled(true);
                        findPreference("change_address").setEnabled(true);
                        preference.setEnabled(true);
                    }
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    SharedPreferences.Editor e = settings.edit();
                    e.putBoolean("hoster_mode", ((SwitchPreferenceCompat)findPreference("hoster_mode")).isChecked());
                    e.putString("myaddress", ((EditTextPreference)findPreference("change_address")).getText());
                    e.commit();
                    return true;
                }
            });
        }
    }
}