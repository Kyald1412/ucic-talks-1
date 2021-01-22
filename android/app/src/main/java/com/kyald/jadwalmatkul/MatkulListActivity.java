package com.kyald.jadwalmatkul;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.model.MatkulDataResponse;
import com.kyald.jadwalmatkul.network.GetDataService;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.network.RetrofitClientInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatkulListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mSettings.edit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MatkulListActivity.this, MatkulAddActivity.class));
            }
        });

        recyclerView = findViewById(R.id.matkul_list);
        assert recyclerView != null;

        getMatkulList();
    }

    private void getMatkulList() {

        ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MatkulListActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MatkulDataResponse> call = service.getAllMatkul();
        call.enqueue(new Callback<MatkulDataResponse>() {
            @Override
            public void onResponse(Call<MatkulDataResponse> call, Response<MatkulDataResponse> response) {
                progressDoalog.dismiss();

                if(response.code() == 200){
                    recyclerView.setAdapter(new MatkulListAdapter(response.body().getBody()));
                }

            }

            @Override
            public void onFailure(Call<MatkulDataResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MatkulListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {

            mSettings.edit().putBoolean("is_logged_in", false).apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}