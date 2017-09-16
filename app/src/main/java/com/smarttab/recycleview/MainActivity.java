package com.smarttab.recycleview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;


    public String Url="http://192.168.2.104/calamity/userDEtail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

       /* layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
*/
       gridLayoutManager=new GridLayoutManager(MainActivity.this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        new uerdeatils().execute();

    }


    private class uerdeatils extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ArrayList arrayList=new ArrayList();
                    try {
                        JSONObject object=new JSONObject(response);

                        if(object.getString("Status").equalsIgnoreCase("True"))
                        {
                            JSONArray jsonArray =object.optJSONArray("response");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                getUserDetails getUserDetails=new getUserDetails();
                                getUserDetails.setName(jsonObject.getString("f_name"));
                                getUserDetails.setEmail(jsonObject.getString("u_email_id"));
                                getUserDetails.setImage(jsonObject.getString("profile_photo"));
                                arrayList.add(getUserDetails);
                           }

                        }
                        else
                        {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter=new cardAdpter(MainActivity.this,arrayList);
                    recyclerView.setAdapter(adapter);

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

            return null;
        }
    }
}
