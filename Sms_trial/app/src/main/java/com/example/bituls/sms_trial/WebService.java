package com.example.bituls.sms_trial;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WebService implements Values {

    private Context context;
    private String url;
    int times = 0;

    public WebService(Context context) {
        this.context = context;
    }

    public WebService() {
    }

    public void QueryPost(final String user, final String password) {
//        final RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(context.getApplicationContext(),
                            "Response Post :" + jsonObject, Toast.LENGTH_LONG).show();
                    QueryGet();
//                    post_data(cust_name, cust_email);
                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                    Toast.makeText(context.getApplicationContext(),
                            "Exception in Post :" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() { //Error response
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage() + "");
                Toast.makeText(context.getApplicationContext(),
                        "Error of Post :" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usr", user);
                params.put("pwd", password);
                return params;
            }
        };
        Erpconnect.getInstance(context).addToRequestQueue(stringRequest);

    }

    // Details data
    public void post_data(final String customer_name, final String customer_email) {
//        final RequestQueue queue = Volley.newRequestQueue(context);
        final String url = "http://beta.bituls.com/api/resource/Issue";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    JSONObject jsonObject = new JSONObject(response.toString());
                    Toast.makeText(context.getApplicationContext(),
                            "Booked", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                    Toast.makeText(context.getApplicationContext(),
                            "Exception in Post :" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() { //Error response
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage() + "");
                Toast.makeText(context.getApplicationContext(),
                        "Error of Post :" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("subject", customer_name);
                params.put("description", customer_email);
                return params;
            }
        };
        Erpconnect.getInstance(context).addToRequestQueue(stringRequest);

    }

    //
    public void QueryGet() {
        Toast.makeText(context.getApplicationContext(),
                "hello steve:" , Toast.LENGTH_LONG).show();
        final JsonFormater format = new JsonFormater();
        url = Urlformat();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            List<String> AllNumber = format.FormatGet(response);// Arrays.asList("0790713103","0792650939", "0726903425");; // "0790713103",("0790713103","0792650939");
                            Toast.makeText(context.getApplicationContext(),
                                    "Response Get :" + AllNumber, Toast.LENGTH_LONG).show();
                            for(String no : AllNumber){
                                String message = "Hello:" + no;
                                Toast.makeText(context.getApplicationContext(),
                                        "Response Get :" + message, Toast.LENGTH_LONG).show();
//                                sendSMS(no, message, no);
                            }
                        } catch (Exception e) {
                            Log.e("Error: ", e.getMessage());
                            Toast.makeText(context.getApplicationContext(),
                                    "Exception in Post :" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(),
                        "Error Get :" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
            }
        });

        Erpconnect.getInstance(context).addToRequestQueue(stringRequest);
    }
    private String Urlformat() {

        String myUrl;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("beta.bituls.com")
                .appendPath("api")
                .appendPath("resource")
                .appendPath("Issue")
                .appendQueryParameter("limit_page_length", "*")
                .appendQueryParameter("fields", "[\"name\",\"subject\",\"status\"]");
        myUrl = builder.build().toString();
        return myUrl;

    }
    // posting URL
    private String post_issues(){

        String myUrl;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("beta.bituls.com")
                .appendPath("api")
                .appendPath("resource")
                .appendPath("Issue");
        myUrl = builder.build().toString();
        return myUrl;
    }

    // POsting query for the issues
    public void QueryPost_issues(final String subject, final String issue_type) {
//        final RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, post_issues(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
//                    Toast.makeText(context.getApplicationContext(),
//                            "Response Post :" + jsonObject, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                    Toast.makeText(context.getApplicationContext(),
                            "Exception in Post :" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() { //Error response
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage() + "");
                Toast.makeText(context.getApplicationContext(),
                        "Error of Post :" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("subject", subject);
                params.put("issue_type", issue_type);
                return params;
            }
        };
        Erpconnect.getInstance(context).addToRequestQueue(stringRequest);

    }

public void sendSMS(final String phoneNumber, String message, final String Number)
{
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED" + Number;

    PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
            new Intent(SENT), 0);
    Intent deliveryIntent = new Intent(DELIVERED);
    deliveryIntent.putExtra("PhoneNum", Number);

    PendingIntent deliveredPI = PendingIntent.getBroadcast(context, Integer.parseInt(Number),
            deliveryIntent, PendingIntent.FLAG_ONE_SHOT);
    SmsManager sms = SmsManager.getDefault();
    sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    //---when the SMS has been sent---

    context.registerReceiver(new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            switch (getResultCode())
            {
                case Activity.RESULT_OK:
                    Toast.makeText(context.getApplicationContext(), "SMS sent:" + Number,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context.getApplicationContext(), "Generic failure",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(context.getApplicationContext(), "No service",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(context.getApplicationContext(), "Null PDU",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context.getApplicationContext(), "Radio off",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            context.unregisterReceiver(this);
        }
    }, new IntentFilter(SENT));
    //---when the SMS has been delivered---

    BroadcastReceiver br = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            SmsManager sambaza = SmsManager.getDefault();
            switch (getResultCode())
            {
                case Activity.RESULT_OK:
                    // CALL THE API WHEN RECEIVE
                    Toast.makeText(context.getApplicationContext(), "SMS delivered" + arg1.getStringExtra("PhoneNum"),
                            Toast.LENGTH_SHORT).show();
                    String CodeNumber = "140";
                    String no = "steve#" + arg1.getStringExtra("PhoneNum");
                    PendingIntent sentPI = null;
                    PendingIntent deliveredPI = null;
                    sambaza.sendTextMessage(CodeNumber, null, no, sentPI, deliveredPI);
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context.getApplicationContext(), "SMS not delivered",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            context.unregisterReceiver(this);
        }
    };
    context.registerReceiver(br, new IntentFilter(DELIVERED));
}
}

