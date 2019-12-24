package in.varadhismartek.patashalaerp.Staff;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeModule extends AppCompatActivity {
String fragmentType;
    String FName,LName, AdhaarNo;
    APIService apiService;
    TextView tvname,tvEmail,tvDob,tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_container);
        apiService = ApiUtils.getAPIService();
        Bundle b = getIntent().getExtras();
        if (b!= null){

            FName = b.getString("FNAME");
            LName = b.getString("LNAME");
            AdhaarNo = b.getString("ADHAARNO");
        }

     //   loadFragment(Constant.EMPLOYEE_VIEW_FRAGMENT, bundle);
        Log.i("fragmentType**",""+FName+"**"+LName+"*"+AdhaarNo);
        getEmpPersonalInfo();
    }

    private void getEmpPersonalInfo() {
        apiService.getEmpPersonalData(Constant.SCHOOL_ID,FName,LName,AdhaarNo).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("res***1",""+response.body()+response.code());
                if (response.isSuccessful()){
                    Log.i("res***",""+response.body()+response.code());
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }


}
