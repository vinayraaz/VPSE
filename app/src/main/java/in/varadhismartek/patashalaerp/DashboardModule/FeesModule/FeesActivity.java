package in.varadhismartek.patashalaerp.DashboardModule.FeesModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtilsPatashala;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesActivity extends AppCompatActivity {
    APIService apiService;
    ArrayList<FeesModle> feesModles = new ArrayList<>();
    RecyclerView recycler_view;
    String strFeeType="",steFeeCode="",strInstallment="",strDueDate="";
    String strSerialNo="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fees_activity);

        apiService = ApiUtils.getAPIService();
        recycler_view = findViewById(R.id.recycler_view);

        getFeeMethod();
        JSONObject object = new JSONObject();
        JSONObject objectOreder = new JSONObject();
        int i = 0;

        try {
            object.put("session_serial_no", "2");
            object.put("fee_type", "Exam");
            object.put("installment", "2");
            object.put("due_date", "2019-12-12");
            object.put("fee_code", "EX");
            object.put("status", "true");

            objectOreder.put(String.valueOf(i + 1), object);
        } catch (JSONException je) {

        }
        createFeeMthod(objectOreder);
        System.out.println("objectOreder**" + objectOreder);
    }

    private void getFeeMethod() {
        apiService.getFeeStructure(Constant.SCHOOL_ID, "2019-12-22")
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        if (response.isSuccessful()) {
                            feesModles.clear();
                            Log.i("GET_Fees**", "" + response.body() + "r**" + response.code());
                            try {
                                JSONObject json1 = (new JSONObject(new Gson().toJson(response.body())));
                                String status = (String) json1.get("status");
                                if (status.equalsIgnoreCase("Success")) {
                                    JSONObject jsonObject1 = json1.getJSONObject("data");
                                    Iterator<String> keys = jsonObject1.keys();
                                    while (keys.hasNext()) {
                                        String key = keys.next();
                                        JSONObject jsonObjectValue = jsonObject1.getJSONObject(key);
                                        Log.d("jsonSliderValue", jsonObjectValue.toString());

                                        strSerialNo =  jsonObjectValue.getString("session");
                                        strFeeType = jsonObjectValue.getString("fee_type");
                                        steFeeCode = jsonObjectValue.getString("fee_code");
                                        strInstallment = jsonObjectValue.getString("installment");
                                        strDueDate = jsonObjectValue.getString("due_date");

                                        feesModles.add(new FeesModle(strSerialNo,strFeeType,steFeeCode,strInstallment,strDueDate));

                                    }
                                }

                                setRecyclerView(feesModles);
                            } catch (JSONException je) {

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
    }

    private void setRecyclerView(ArrayList<FeesModle> feesModles) {
        FeeStructureAdapter adapter = new FeeStructureAdapter(feesModles, this, Constant.RV_FEES_ROW);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void createFeeMthod(JSONObject objectOreder) {
        apiService.createFeeStructure(Constant.SCHOOL_ID, Constant.EMPLOYEE_BY_ID, "2019-12-22",
                objectOreder).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.i("FEES RESP**", "" + response.body() + "***" + response.code());
                    Toast.makeText(FeesActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                } else {

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }


}
