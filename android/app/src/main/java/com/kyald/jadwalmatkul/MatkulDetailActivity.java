package com.kyald.jadwalmatkul;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.model.MatkulDataResponse;
import com.kyald.jadwalmatkul.model.MatkulHariResponse;
import com.kyald.jadwalmatkul.network.GetDataService;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.network.RetrofitClientInstance;

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

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        btnDelete.setOnClickListener(new View.OnClickListener() {
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
        getHari();
    }

    public void setupView() {
        EditText edtMatkul = findViewById(R.id.edtMatkul);
        edtMatkul.setText(matkul);
    }

    public void setupSpinner(ArrayList<String> daftarHari) {
        Spinner spinnerHari = (Spinner) findViewById(R.id.spinnerHari);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, daftarHari);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerHari.setAdapter(adapter);

        spinnerHari.setSelection(Integer.parseInt(id_hari) - 1);

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(TWOHLayoutSpinner.this, "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                id_hari = String.valueOf(i + 1);
                hari = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getHari() {

        ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MatkulDetailActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MatkulHariResponse> call = service.getHari();
        call.enqueue(new Callback<MatkulHariResponse>() {
            @Override
            public void onResponse(Call<MatkulHariResponse> call, Response<MatkulHariResponse> response) {
                progressDoalog.dismiss();

                if (response.code() == 200) {
                    ArrayList<String> daftarHari = new ArrayList<String>();

                    for (int i = 0; i < response.body().getBody().size(); i++) {
                        daftarHari.add(response.body().getBody().get(i).getHari());
                    }

                    setupSpinner(daftarHari);
                }

            }

            @Override
            public void onFailure(Call<MatkulHariResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MatkulDetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateMatkul() {
        final String matkul = ((EditText) findViewById(R.id.edtMatkul)).getText().toString().trim();

        ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MatkulDetailActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BaseResponse> call = service.postUpdateMatkul(id, id_hari, matkul);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progressDoalog.dismiss();

                Toast.makeText(MatkulDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MatkulDetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void deleteMatkul() {


        ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MatkulDetailActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BaseResponse> call = service.postDeleteMatkul(id);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progressDoalog.dismiss();

                Toast.makeText(MatkulDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MatkulDetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
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