package com.kyald.jadwalmatkul;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.model.MatkulContent;
import com.kyald.jadwalmatkul.utils.Constants;
import com.kyald.jadwalmatkul.utils.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        mSettings  = PreferenceManager.getDefaultSharedPreferences(this);
        editor =  mSettings.edit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.matkul_list);
        assert recyclerView != null;

        getMatkulList();
    }

    private void getMatkulList() {
        class GetMatkul extends AsyncTask<Void, Void, BaseResponse> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MatkulListActivity.this, "Fetching...", "Wait...", false, false);
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

                    Toast.makeText(MatkulListActivity.this, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            private void showMatkul(String s) {
                try {
                    JSONObject resultJsonObject = new JSONObject(s);
                    JSONArray restulJsonArray = resultJsonObject.getJSONArray("body");

                    MatkulContent.ITEMS.clear();
                    
                    for (int i = 0; i < restulJsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = restulJsonArray.getJSONObject(i);

                            String id = (String) jsonObject.get("id");
                            String id_hari = (String) jsonObject.get("id_hari");
                            String matkul = (String) jsonObject.get("matkul");
                            String hari = (String) jsonObject.get("hari");

                            MatkulContent.ITEMS.add(new MatkulContent.MatkulItem(id, matkul, hari, id_hari));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    recyclerView.setAdapter(new MatkulListAdapter(MatkulContent.ITEMS));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected BaseResponse doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequest(Constants.URL_GET_MATKUL);
            }
        }
        GetMatkul ge = new GetMatkul();
        ge.execute();
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