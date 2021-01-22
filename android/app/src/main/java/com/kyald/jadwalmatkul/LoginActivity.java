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
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.utils.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isLoggedIn = mSettings.getBoolean("is_logged_in", false);

        if(isLoggedIn){
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

        class UpdateEmployee extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(BaseResponse s) {
                super.onPostExecute(s);
                loading.dismiss();

                if (s.code == 200){
                    showMatkulList();
                } else {

                    try {
                        JSONObject resultJsonObject = new JSONObject(s.s);
                        String message = (String) resultJsonObject.get("message");

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("password", password);

                RequestHandler rh = new RequestHandler();

                BaseResponse s = rh.sendPostRequest(Constants.URL_LOGIN, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }
}