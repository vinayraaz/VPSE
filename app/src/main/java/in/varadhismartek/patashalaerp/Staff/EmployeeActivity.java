package in.varadhismartek.patashalaerp.Staff;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Spinner;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AddSubject;
import in.varadhismartek.patashalaerp.DashboardModule.DashBoardMenuActivity;
import in.varadhismartek.patashalaerp.DashboardModule.DashboardActivity;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.Fragment_HomeworkCreate;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.Fragment_HomeworkInbox;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.HomeworkViewFragment;
import in.varadhismartek.patashalaerp.GeneralClass.CustomSpinnerAdapter;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {
    ArrayList<String> employeeNameList = new ArrayList<>();
    ArrayList<EmpLeaveModel> employeeList;
    Spinner spn_employee;
    APIService mApiService;
    RecyclerView recycler_view;
    String empFname = "", empLname = "", empUUId, empPhoneNo, empAdhaarNo, empDept;
    String  fragmentType="",employee_status,wing_name,employee_photo,role,sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        mApiService = ApiUtils.getAPIService();
        recycler_view = findViewById(R.id.recyclerview);
        getAllEmployeeAPI();

        employeeList = new ArrayList<>();



    }


    private void getAllEmployeeAPI() {
        mApiService.getAllEmployeeList(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    try {
                        employeeNameList.clear();
                        employeeList.clear();
                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = object.getString("status");

                        if (status.equalsIgnoreCase("Success")) {

                            JSONObject jsonData = object.getJSONObject("data");
                            Log.d("ADMIN_API_DATA", jsonData.toString());

                            Iterator<String> key = jsonData.keys();

                            while (key.hasNext()) {

                                String myKey = key.next();
                                Log.d("EMPLKEY", myKey);

                                JSONObject keyData = jsonData.getJSONObject(myKey);
                                Log.d("EMPKEYDATA", keyData.toString());

                                sex = keyData.getString("sex");
                                employee_status = keyData.getString("employee_status");
                                wing_name = keyData.getString("wing_name");
                                employee_photo = keyData.getString("employee_photo");
                                role = keyData.getString("role");

                                empFname = keyData.getString("first_name");
                                empLname = keyData.getString("last_name");
                                empPhoneNo = keyData.getString("phone_number");
                                empAdhaarNo = keyData.getString("adhaar_card_no");
                                empDept = keyData.getString("department_name");
                                String empName = empFname + " " + empLname;
                                employeeNameList.add(empName);

                                employeeList.add(new EmpLeaveModel(empFname, empLname, empUUId, empPhoneNo, empAdhaarNo, empDept
                                ,employee_status,wing_name,employee_photo,role,sex));
                            }

                            setRecylerView();

                        }

                    } catch (JSONException je) {

                    }
                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    private void setRecylerView() {
        EmployeeAdapter adapter = new EmployeeAdapter(this, employeeList, Constant.RV_EMPLOYEE_LIST);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(EmployeeActivity.this, DashboardActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        // finish();
    }
}
