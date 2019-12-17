package in.varadhismartek.patashalaerp.DashboardModule.Assessment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.GeneralClass.CustomSpinnerAdapter;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class Fragment_Exam_Barrier extends Fragment implements View.OnClickListener {
    Spinner spExamType, spDivision, spClass, spSubject;
    EditText edMinMarks, edMaxMark, edExamDuration;
    TextView tvAdd;
    APIService apiService;
    private ArrayList<String> listExamType;
    private ArrayList<AssesmentModel> assesmentModels;
    private ArrayList<String> listDivision;
    private ArrayList<String> listClass;
    private ArrayList<String> listSubject;
    String strExamType = "", strDiv = "", str_class = "", strSubject = "";
    private ArrayList<String> subjectList = new ArrayList<>();
    RecyclerView recyclerView;
    String StrExamType, StrDiv, StrClass, StrSubject, StrDuration;
    long longMin, longmax;

    public Fragment_Exam_Barrier() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_barrier, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initView(view);
        initListener();
        getExamType();
        getDivisionApi();
        getSubject();
        // getExamBarrierApi();

        return view;
    }


    private void initListener() {
        tvAdd.setOnClickListener(this);
    }


    private void initView(View view) {
        apiService = ApiUtils.getAPIService();
        spExamType = view.findViewById(R.id.spExamType);
        spDivision = view.findViewById(R.id.spDivision);
        spClass = view.findViewById(R.id.spClass);
        spSubject = view.findViewById(R.id.spSubject);
        tvAdd = view.findViewById(R.id.tvAdd);

        edMinMarks = view.findViewById(R.id.ed_minmarks);
        edMaxMark = view.findViewById(R.id.ed_maxmarks);
        edExamDuration = view.findViewById(R.id.ed_examDuration);

        recyclerView = view.findViewById(R.id.recyclerView);

        listExamType = new ArrayList<>();
        listDivision = new ArrayList<>();
        listClass = new ArrayList<>();
        listSubject = new ArrayList<>();
        assesmentModels = new ArrayList<>();


    }

    private void getExamType() {
        listExamType.clear();
        listExamType.add("Select Exam Type");
        Log.i("Exam_getlist *", "" + "DATA");
        apiService.getExamList(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("Exam_getlist *", "" + response.body());

                if (response.isSuccessful()) {
                    try {
                        JSONObject json1 = (new JSONObject(new Gson().toJson(response.body())));
                        String status = (String) json1.get("status");

                        if (status.equalsIgnoreCase("Sucess")) {
                            JSONObject jsonObject1 = json1.getJSONObject("data");
                            Iterator<String> keys = jsonObject1.keys();

                            Log.i("exam_name *", "" + jsonObject1);
                            while (keys.hasNext()) {
                                String key = keys.next();

                                JSONObject jsonObjectValue = jsonObject1.getJSONObject(key);
                                String exam_name = jsonObjectValue.getString("exam_type");
                                boolean exam_status = jsonObjectValue.getBoolean("status");
                                Log.i("exam_name *", "" + exam_name);

                                if (exam_status) {

                                    listExamType.add(exam_name);

                                }

                            }
                            CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listExamType);
                            spExamType.setAdapter(customSpinnerAdapter);

                        }
                    } catch (JSONException je) {

                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        spExamType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strExamType = spExamType.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void getDivisionApi() {
        listDivision.clear();
        listDivision.add("Select Division");
        apiService.getDivisions(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                boolean statusDivision = false;
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");

                        if (status.equalsIgnoreCase("success")) {
                            JSONArray jsonArray = object.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object1 = jsonArray.getJSONObject(i);

                                String added_datetime = object1.getString("added_datetime");
                                String Id = object1.getString("id");
                                statusDivision = object1.getBoolean("status");
                                if (statusDivision) {
                                    String division = object1.getString("division");
                                    String school_id = object1.getString("school_id");
                                    String added_by_id = object1.getString("added_by_id");

                                    listDivision.add(division);
                                }


                            }
                            CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listDivision);
                            spDivision.setAdapter(customSpinnerAdapter);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("GET_DIVISION_EXCEPTION", t.getMessage());

            }
        });

        spDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDiv = parent.getSelectedItem().toString();
                //  setSpinnerForClass();

                JSONArray array = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                try {
                    array.put(strDiv);
                    jsonObject.put("divisions", array);
                    Constant.DIVISION_NAME = strDiv;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getClassSectionList(jsonObject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getClassSectionList(JSONObject jsonObject) {
        listClass.clear();
        listClass.add("Select Class");
        apiService.getClassSections(Constant.SCHOOL_ID, jsonObject).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("CLASS_SECTIONDDD", "" + response.body());

                if (response.isSuccessful()) {
                    try {

                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");

                        if (status.equalsIgnoreCase("success")) {
                            JSONObject jsonObject1 = object.getJSONObject("data");
                            if (object.getJSONObject("data").toString().equals("{}")) {
                                //  modelArrayList.clear();
                                // customSpinnerAdapter.notifyDataSetChanged();
                                setSpinnerForClass();

                            } else {

                                JSONObject jsonObject2 = jsonObject1.getJSONObject(strDiv);
                                Iterator<String> keys = jsonObject2.keys();

                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    JSONObject jsonObjectValue = jsonObject2.getJSONObject(key);
                                    String className = jsonObjectValue.getString("class_name");

                                    listClass.add(className);

                                }
                                CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listClass);
                                spClass.setAdapter(customSpinnerAdapter);
                            }


                        }


                        //setRecyclerView();

                    } catch (JSONException je) {

                    }

                } else {
                    if (String.valueOf(response.code()).equals("400")) {
                        Toast.makeText(getActivity(), "No Record", Toast.LENGTH_SHORT).show();
                    } else if (String.valueOf(response.code()).equals("409")) {
                        Toast.makeText(getActivity(), "No Record", Toast.LENGTH_SHORT).show();
                    } else {
                        //setSpinnerForClass();// no data in getsectionAOI
                    }

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_class = spClass.getSelectedItem().toString();
                getExamBarrierApi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setSpinnerForClass() {
        listClass.clear();
        listClass.add("Select Class");

        SharedPreferences preferences = getActivity().getSharedPreferences("DIV_CLASS", Context.MODE_PRIVATE);
        String strSecDivClass = preferences.getString("DIV_CLASS", "");
        try {
            JSONArray jsonArray = new JSONArray(strSecDivClass);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String DivName = jsonArray.getJSONObject(i).getString("divisionName");

                if (strDiv.isEmpty() || strDiv.equals("")) {
                } else if (strDiv.equals(DivName)) {
                    JSONArray jsonArray1 = jsonObject.getJSONArray("classList");
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        String ClassName = jsonArray1.getJSONObject(j).getString("name");
                        listClass.add(ClassName);
                    }
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listClass);
        spClass.setAdapter(customSpinnerAdapter);

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_class = adapterView.getSelectedItem().toString();
                /*if (i == 0) {
                    str_class = "";

                } else {
                    str_class = adapterView.getSelectedItem().toString();
                    asciNo = 95;
                    System.out.println("str_class****" + str_class);
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getSubject() {
        listSubject.clear();
        listSubject.add("Select Subject");
        String arr[] = getActivity().getResources().getStringArray(R.array.sub_array);
        for (String anArr : arr) {
            listSubject.add(anArr);
        }
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listSubject);
        spSubject.setAdapter(customSpinnerAdapter);

        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSubject = spSubject.getItemAtPosition(position).toString();
                System.out.println("strSubject***" + strSubject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAdd:
                String value = "", editvalue = "";
                // editvalue = edAddExam.getText().toString();

                if (edMinMarks.getText().toString().isEmpty() || edMaxMark.getText().toString().isEmpty() ||
                        edExamDuration.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill  the min marks max marks and exam duration",
                            Toast.LENGTH_SHORT).show();
                } else {
                    long strMin = Long.parseLong(edMinMarks.getText().toString());
                    String strDuration = edExamDuration.getText().toString();
                    long strMax = Long.parseLong(edMaxMark.getText().toString());
                    Log.i("STR value***", "" + strMin + "**" + strMax + "***" + strDuration + "**");

                    if (assesmentModels.size() > 0) {

                        boolean b = true;
                        for (int i = 0; i < assesmentModels.size(); i++) {
                            if ((assesmentModels.get(i).getSubjectName()).contains(strSubject)) {
                                b = false;
                                Toast.makeText(getActivity(), "Already added", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (b) {


                            assesmentModels.add(new AssesmentModel(strMin, strMax, strExamType, strDiv,
                                    str_class, strSubject, strDuration));


                        }

                    } else {
                        assesmentModels.add(new AssesmentModel(strMin, strMax, strExamType, strDiv,
                                str_class, strSubject, strDuration));

                    }

                    setRecyclerView();
                    addExamBarriers(strMin, strMax, strExamType, strDiv, str_class, strSubject, strDuration);

                }

                Log.i("STR_assesmentModels***", "" + assesmentModels);

        }
    }

    private void setRecyclerView() {
        AssessmentAdapter adapter = new AssessmentAdapter(getActivity(), assesmentModels, Constant.RV_EXAMS_BARRIER_ROW);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addExamBarriers(long strMin, long strMax, String strExamType, String strDiv,
                                 String str_class, String strSubject, String strDuration) {
        apiService.addExamBarrier(Constant.SCHOOL_ID, strExamType, strDiv, str_class, strSubject, strMin,
                strMax, strDuration, Constant.EMPLOYEE_BY_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("ADDEXAMBARRIER**", "" + response.body());
                Log.i("ADDEXAMBARRIER**", "" + response.code());
                getExamBarrierApi();

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    private void getExamBarrierApi() {
        assesmentModels.clear();
        apiService.getExamBarriers(Constant.SCHOOL_ID, strExamType, strDiv, str_class).
                enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.i("Get_examBarrier**", "" + response.body());
                        Log.i("Get_examBarrier**", "" + response.code());

                        if (response.isSuccessful()) {
                            try {

                                JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                                String status = (String) object.get("status");

                                if (status.equalsIgnoreCase("Sucess")) {
                                    JSONObject jsonObject1 = object.getJSONObject("data");
                                    if (object.getJSONObject("data").toString().equals("{}")) {
                                        Toast.makeText(getActivity(), "Data not exist", LENGTH_SHORT).show();

                                    } else {

                                        Iterator<String> keys = jsonObject1.keys();

                                        while (keys.hasNext()) {
                                            String key = keys.next();
                                            JSONObject jsonObjectValue = jsonObject1.getJSONObject(key);
                                            Log.d("jsonSliderValue", jsonObjectValue.toString());

                                            boolean gradeStatus = jsonObjectValue.getBoolean("status");

                                            if (gradeStatus) {

                                                StrSubject = key;
                                                StrDuration = jsonObjectValue.getString("exam_duration");
                                                longMin = jsonObjectValue.getLong("min_marks");
                                                longmax = jsonObjectValue.getLong("max_marks");

                                                assesmentModels.add(new AssesmentModel(longMin, longmax, strExamType,
                                                        strDiv, str_class, strSubject, StrDuration));

                                            }

                                        }
                                        setRecyclerView();


                                    }
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


}
