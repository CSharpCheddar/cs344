package com.example.party_maps.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.party_maps.R;
import com.example.party_maps.ui.login.LoginActivity;
import com.example.party_maps.ui.login.MyDBContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Context mContext;

    public static PlaceholderFragment newInstance(int index, Context context) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        mContext = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final LinearLayout ll = root.findViewById(R.id.ll);
        int index = getArguments() == null ? 2 : getArguments().getInt(ARG_SECTION_NUMBER);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isChecked = settings.getBoolean("hoster_mode", false);
        String myaddress = settings.getString("myaddress", "N/A");
        final MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(mContext);
        final SQLiteDatabase rdb = mdbh.getReadableDatabase();
        Cursor c = rdb.rawQuery("SELECT * FROM " + MyDBContract.DBEntry.TABLE_NAME, null);
        // Log the user in with default or given creds.
        // If the user's creds aren't saved, save them
        c.moveToFirst();
        final String username = c.getString(c.getColumnIndexOrThrow(MyDBContract.DBEntry.COLUMN_NAME_USERNAME));
        if (index == 1) {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            String url ="http://52.70.125.49//getfriends.php?user=" + username;
            url = url.replaceAll("\\s", "zzzz");
            JsonArrayRequest jsonArrayRequest =
                    new JsonArrayRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    // Do something with response
                                    //mTextView.setText(response.toString());
                                    // Process the JSON
                                    try {
                                        // Loop through the array elements
                                        for (int i = 0; i < response.length(); i++) {
                                            // Get current json object
                                            JSONObject object = response.getJSONObject(i);
                                            // Get the current student (json object) data
                                            String user1 = object.getString("user1");
                                            String user2 = object.getString("user2");
                                            TextView view = new TextView(mContext);
                                            view.setText(user1.equals(username) ? user2 : user1);
                                            view.setTextSize(20);
                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                            params.gravity = Gravity.CENTER;
                                            view.setLayoutParams(params);
                                            ll.addView(view);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
                    );
            queue.add(jsonArrayRequest);
        } else if (index == 2) {
            if (mContext.getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE && isChecked) {
                TextView view = new TextView(mContext);
                view.setText("Currently Hosting:\n\n" + myaddress);
                view.setTextSize(20);
                ll.addView(view);
            } else {
                RequestQueue queue = Volley.newRequestQueue(mContext);
                String url ="http://52.70.125.49//getaddresses.php";
                url = url.replaceAll("\\s", "zzzz");
                JsonArrayRequest jsonArrayRequest =
                        new JsonArrayRequest(Request.Method.GET, url, null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        // Process the JSON
                                        try {
                                            // Loop through the array elements
                                            for (int i = 0; i < response.length(); i++) {
                                                // Get current json object
                                                JSONObject object = response.getJSONObject(i);
                                                // Get the current student (json object) data
                                                String handle = object.getString("handle");
                                                String address = object.getString("address");
                                                TextView view = new TextView(mContext);
                                                TextView view2 = new TextView(mContext);
                                                view.setText("Handle: " + handle);
                                                view2.setText("Address: " + address);
                                                view.setTextSize(20);
                                                view2.setTextSize(20);
                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                params.gravity = Gravity.CENTER;
                                                view.setLayoutParams(params);
                                                view2.setLayoutParams(params);
                                                ll.addView(view);
                                                ll.addView(view2);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Oops, an error occurred", Toast.LENGTH_LONG).show();
                            }
                        }
                        );
                queue.add(jsonArrayRequest);
            }
        } else if (index == 3) {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            String url ="http://52.70.125.49//getmessages.php?user=" + username;
            url = url.replaceAll("\\s", "zzzz");
            JsonArrayRequest jsonArrayRequest =
                    new JsonArrayRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    // Process the JSON
                                    try {
                                        // Loop through the array elements
                                        HashMap<String, List<String>> messages = new HashMap<>();
                                        for (int i = 0; i < response.length(); i++) {
                                            // Get current json object
                                            JSONObject object = response.getJSONObject(i);
                                            String sid = object.getString("sid");
                                            String rid = object.getString("rid");
                                            String msg = object.getString("msg");
                                            if (messages.containsKey(sid)) {
                                                if (sid.equals(username)) {
                                                    messages.get(rid).add("Me: " + msg);
                                                } else {
                                                    messages.get(sid).add(msg);
                                                }
                                            } else {
                                                List<String> list = new ArrayList<>();
                                                if (sid.equals(username)) {
                                                    list.add("Me: " + msg);
                                                    messages.put(rid, list);
                                                } else {
                                                    list.add(msg);
                                                    messages.put(sid, list);
                                                }
                                            }
                                        }
                                        for (Map.Entry<String, List<String>> entry : messages.entrySet()) {
                                            String sender = entry.getKey();
                                            StringBuilder msgs = new StringBuilder();
                                            for (String s : entry.getValue()) {
                                                msgs.append(s + "\n\n");
                                            }
                                            TextView view = new TextView(mContext);
                                            view.setText(sender + ":\n\n" + msgs.toString());
                                            view.setTextSize(20);
                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                            params.gravity = Gravity.CENTER;
                                            view.setLayoutParams(params);
                                            ll.addView(view);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
                    );
            queue.add(jsonArrayRequest);
            RequestQueue queue2 = Volley.newRequestQueue(mContext);
            String loginurl ="http://52.70.125.49//readmessages.php?username=" + username;
            loginurl = loginurl.replaceAll("\\s", "zzzz");
            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET, loginurl, new Response.Listener<String>() {
                public void onResponse(String response){

                }
            },
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError er){
                            Toast.makeText(mContext,
                                    "Oops, an error occurred", Toast.LENGTH_LONG).show();
                        }
                    }
            );
            queue2.add(stringRequest);
        }
        return root;
    }
}