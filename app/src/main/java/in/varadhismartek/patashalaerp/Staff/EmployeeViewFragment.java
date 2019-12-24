package in.varadhismartek.patashalaerp.Staff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeViewFragment extends Fragment {
    String FName,LName, AdhaarNo;
    APIService apiService;
    TextView tvname,tvEmail,tvDob,tv;
    public EmployeeViewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_view, container, false);

        initViews(view);
        getBundle();
        getEmpPersonalInfo();
        return view;

    }

    private void getEmpPersonalInfo() {
        apiService.getEmpPersonalData(Constant.SCHOOL_ID,FName,LName,AdhaarNo).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()){
                    Log.i("res***",""+response.body()+response.code());
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    private void getBundle() {
        Bundle b = getArguments();
        if (b!=null){

            FName = b.getString("FNAME");
            LName = b.getString("LNAME");
            AdhaarNo = b.getString("ADHAARNO");
        }
    }

    private void initViews(View view) {
        apiService = ApiUtils.getAPIService();

        /*  "email": "varadhifb@gmail.com",
            "present_city": "ORG",
            "present_locality_name": "ORG",

            "deleted_datetime": null,
            "passport_pic_front": "",
            "permanent_country": "ORG",
            "mothers_organisation": "ORG",
            "voter_id_no": "4475545",
            "fathers_mobile_no": "6565656566",
            "medical_issue": "Full",
            "dob": "1996-12-04",
            "mothers_email": "EMAIL",
            "spouse_middle_name": "MIDDLE",
            "updater_first_name": null,
            "permanent_door_no": "ORG",
            "driving_license_pic_back": "",
            "first_name": "Sharath",
            "delete_message": null,
            "spouse_email": "EMAIL",
            "weight": "100",
            "employee_uuid": "b9c3d430-f335-4025-aa5e-f448333d6e0c",
            "passport_no": "47785574",
            "height": "500.0",
            "permanent_landmark_name": "ORG",
            "employee_deleted": false,
            "adhaar_card_no": "8277616360",
            "child_status": "true",
            "permanent_contact_number": "ORG",
            "pan_card_pic_back": "",
            "voter_id_pic_front": "",
            "present_street": "ORG",
            "role": "Admin",
            "middle_name": "MIDDLE",
            "driving_license_pic_front": "",
            "subject_name": null,
            "birth_place": "BIRTH",
            "nationality": "indian",
            "spouse_designation": "DEG",
            "spouse_organisation": "ORG",
            "present_door_no": "ORG",
            "fathers_first_name": "FIRST",
            "pan_card_pic_front": "",
            "mothers_designation": "ORG",
            "division": null,
            "spouse_first_name": "FIRST",
            "present_landmark_name": "ORG",
            "mothers_mobile_no": "6565656566",
            "passport_pic_back": "",
            "voter_id_pic_back": "",
            "last_name": "Kumar",
            "approver_id": null,
            "mothers_last_name": "LAST",
            "spouse_last_name": "LAST",
            "fathers_last_name": "LAST",
            "sex": "male",
            "fathers_designation": "ORG",
            "short_eye_vision": "5",
            "driving_license_no": "477855",
            "submitter_first_name": "POCNAME",
            "updater_id": null,
            "blood_group": "A+",
            "permanent_state": "ORG",
            "marital_status": "mARRIED",
            "mothers_occupation": "ORG",
            "personal_doctor_mobile_number": "555858585",
            "employee_status": "Pending",
            "marriage_date": "2000-10-10",
            "adhaar_card_pic_back": "",
            "adhaar_card_pic_front": "",
            "submitter_last_name": "",
            "present_pincode": "ORG",
            "present_country": "ORG",
            "wing": "Admin",
            "approved_timestamp": null,
            "fathers_email": "EMAIL",
            "spouse_occupation": "BE",
            "fathers_organisation": "ORG",
            "updated_timestamp": null,
            "employee_photo": "",
            "mothers_first_name": "FIRST",
            "permanent_locality_name": "ORG",
            "fathers_middle_name": "MIDDLE",
            "fathers_occupation": "ORG",
            "updater_last_name": null,
            "custom_employee_id": null,
            "permanent_pincode": "ORG",
            "submitter_id": "1ae3cb0e-4dfd-4837-9fa3-a7d48dafc712",
            "mothers_middle_name": "MIDDLE",
            "permanent_street": "ORG",
            "caste": "maratha",
            "present_state": "ORG",
            "pan_card_no": "2345453",
            "date_of_joining": "2019-10-10",
            "approver_last_name": null,
            "permanent_city": "ORG",
            "submitted_timestamp": "2019-12-18T10:08:08.586Z",
            "number_of_childrens": "None",
            "school_id": "984d1648-af4b-4dad-946c-526effa5bc7e",
            "phone_number": "8277616360",
            "spouse_phone_no": "6565656566",
            "long_eye_vision": "5",
            "approver_first_name": null,
            "community": "acbf",
            "present_contact_number": "ORG",
            "department": "Admin"*/

    }
}
