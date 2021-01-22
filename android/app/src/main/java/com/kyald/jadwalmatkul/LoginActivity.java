package com.kyald.jadwalmatkul;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.network.GetDataService;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.network.RetrofitClientInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isLoggedIn = mSettings.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            showMatkulList();
            finish();
        }

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }


    private void showMatkulList() {
        mSettings.edit().putBoolean("is_logged_in", true).apply();

        Intent intent = new Intent(LoginActivity.this, MatkulListActivity.class);
        startActivity(intent);
    }

    private void login() {
        final String username = ((EditText) findViewById(R.id.edtUsername)).getText().toString().trim();
        final String password = ((EditText) findViewById(R.id.edtPassword)).getText().toString().trim();
        ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(LoginActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BaseResponse> call = service.postLogin(username, password);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progressDoalog.dismiss();

                if (response.code() == 200) {
                    showMatkulList();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(LoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}