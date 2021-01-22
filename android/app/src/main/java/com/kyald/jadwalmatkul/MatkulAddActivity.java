package com.kyald.jadwalmatkul;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.utils.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An activity representing a single Detail detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MatkulListActivity}.
 */
public class MatkulAddActivity extends AppCompatActivity {

    public static final String ARG_MATKUL = "matkul";
    public static final String ARG_HARI_ID = "id_hari";

    String id_hari = "";
    String hari = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btnCreate = (Button) findViewById(R.id.btnAdd);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMatkul();
            }
        });

        getHari();
    }

    public void setupSpinner(ArrayList<String> daftarHari) {
        Spinner spinnerHari = (Spinner) findViewById(R.id.spinnerHari);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, daftarHari);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerHari.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(TWOHLayoutSpinner.this, "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                id_hari = String.valueOf(i+1);
                hari = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getHari() {
        class GetHari extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MatkulAddActivity.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(BaseResponse s) {
                super.onPostExecute(s);
                loading.dismiss();

                if(s.code == 200){
                    showMatkul(s.s);
                } else {
                    showMessage(s.s);
                }
            }


            private void showMessage(String s) {
                try {
                    JSONObject resultJsonObject = new JSONObject(s);
                    String message = (String) resultJsonObject.get("message");

                    Toast.makeText(MatkulAddActivity.this, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            private void showMatkul(String s) {
                try {
                    JSONObject resultJsonObject = new JSONObject(s);
                    JSONArray restulJsonArray = resultJsonObject.getJSONArray("body");

                    ArrayList<String> daftarHari = new ArrayList<String>();

                    for (int i = 0; i < restulJsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = restulJsonArray.getJSONObject(i);

                            String id = (String) jsonObject.get("id");
                            String hari = (String) jsonObject.get("hari");

                            daftarHari.add(hari);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    setupSpinner(daftarHari);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequest(Constants.URL_GET_HARI);
            }
        }
        GetHari ge = new GetHari();
        ge.execute();
    }


    private void createMatkul() {
        final String matkul = ((EditText) findViewById(R.id.edtMatkul)).getText().toString().trim();

        class CreateMatkul extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MatkulAddActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(BaseResponse s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMessage(s.s);
            }

            private void showMessage(String s) {
                try {
                    JSONObject resultJsonObject = new JSONObject(s);
                    String message = (String) resultJsonObject.get("message");

                    Toast.makeText(MatkulAddActivity.this, message, Toast.LENGTH_SHORT).show();

                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(ARG_HARI_ID, id_hari);
                hashMap.put(ARG_MATKUL, matkul);

                RequestHandler rh = new RequestHandler();

                BaseResponse s = rh.sendPostRequest(Constants.URL_POST_CREATE_MATKUL, hashMap);

                return s;
            }
        }

        CreateMatkul ue = new CreateMatkul();
        ue.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MatkulListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}