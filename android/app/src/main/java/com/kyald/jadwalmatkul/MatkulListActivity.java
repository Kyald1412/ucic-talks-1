package com.kyald.jadwalmatkul;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.kyald.jadwalmatkul.model.MatkulContent;
import com.kyald.jadwalmatkul.utils.HttpConnectionService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MatkulListActivity extends AppCompatActivity {

    private final String apiMatkulList = "http://api.yourwish.id/api/matkul.php";
    private ProgressDialog processDialog;
    private JSONArray restulJsonArray;
    private int success = 0;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = findViewById(R.id.matkul_list);
        assert recyclerView != null;

        new ServiceStubAsyncTask(this).execute();
    }

    private class ServiceStubAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        String response = "";
        HashMap<String, String> postDataParams;

        public ServiceStubAsyncTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(mContext);
            processDialog.setMessage("Please  Wait ...");
            processDialog.setCancelable(false);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            postDataParams = new HashMap<>();
            postDataParams.put("HTTP_ACCEPT", "application/json");

            HttpConnectionService service = new HttpConnectionService();
            response = service.sendRequest(apiMatkulList, postDataParams);
            try {
                success = 1;
                JSONObject resultJsonObject = new JSONObject(response);
                restulJsonArray = resultJsonObject.getJSONArray("body");
            } catch (JSONException e) {
                success = 0;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (processDialog.isShowing()) {
                processDialog.dismiss();
            }

            if (success == 1) {
                if (null != restulJsonArray) {

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
                }
            }
        }
    }

}