package com.kyald.jadwalmatkul;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.model.MatkulContent;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.utils.RequestHandler;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * An activity representing a single Detail detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MatkulListActivity}.
 */
public class MatkulDetailActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "id";
    public static final String ARG_MATKUL = "matkul";
    public static final String ARG_HARI = "hari";
    public static final String ARG_HARI_ID = "id_hari";

    String id = "";
    String matkul = "";
    String id_hari = "";
    String hari = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMatkul();
            }
        });
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMatkul();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupData();
    }

    public void setupData() {
        Intent data = getIntent();

        id = data.getStringExtra(ARG_ITEM_ID);
        matkul = data.getStringExtra(ARG_MATKUL);
        id_hari = data.getStringExtra(ARG_HARI_ID);
        hari = data.getStringExtra(ARG_HARI);

        setupView();
    }

    public void setupView() {
        EditText edtMatkul = findViewById(R.id.edtMatkul);
        edtMatkul.setText(matkul);
    }

    public void setupSpinner(String[] daftarHari) {
        Spinner spinnerHari = (Spinner) findViewById(R.id.spinnerHari);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, daftarHari);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerHari.setAdapter(adapter);

        spinnerHari.setSelection(Integer.parseInt(id_hari));

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(TWOHLayoutSpinner.this, "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                id_hari = String.valueOf(i);
                hari = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateMatkul() {
        final String matkul = ((EditText) findViewById(R.id.edtMatkul)).getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MatkulDetailActivity.this, "Updating...", "Wait...", false, false);
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

                    Toast.makeText(MatkulDetailActivity.this, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(ARG_ITEM_ID, id);
                hashMap.put(ARG_HARI_ID, id_hari);
                hashMap.put(ARG_MATKUL, matkul);

                RequestHandler rh = new RequestHandler();

                BaseResponse s = rh.sendPostRequest(Constants.URL_POST_UPDATE_MATKUL, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }


    private void deleteMatkul() {

        class UpdateEmployee extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MatkulDetailActivity.this, "Updating...", "Wait...", false, false);
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

                    Toast.makeText(MatkulDetailActivity.this, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(ARG_ITEM_ID, id);

                RequestHandler rh = new RequestHandler();

                BaseResponse s = rh.sendPostRequest(Constants.URL_DELETE_MATKUL, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
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