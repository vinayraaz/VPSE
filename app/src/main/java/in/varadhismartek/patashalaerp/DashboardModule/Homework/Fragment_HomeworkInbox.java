package in.varadhismartek.patashalaerp.DashboardModule.Homework;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.DashboardModule.DashBoardMenuActivity;
import in.varadhismartek.patashalaerp.GeneralClass.CustomSpinnerAdapter;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_HomeworkInbox extends Fragment implements View.OnClickListener {


    String strDiv = "", str_class = "ukg", str_toDate = "", endYear = "", currentDate = "";

    HomeworkAdapter homeworkAdapter;
    APIService mApiService;
    LinearLayout linearLayoutDate;
    Calendar calendar;
    Date date1;

    private int year, month, date, rowNumber = 1;
    CustomSpinnerAdapter customSpinnerAdapter;
    TextView tvFromDate;
    ImageView iv_backBtn;
    Spinner spDivision, spnClass;
    RecyclerView recycler_view;
    FloatingActionButton fab;

    ArrayList<HomeworkModel> homeworkModelsInbox;
    ArrayList<HomeworkModel> homeworkModelsInboxDate;
    ArrayList<String> listDivision;
    ArrayList<String> listClass;
    ArrayList<String> listSection;
    String homeworkId, homeworkTitle, description, homeClass, section, subject, startDate, endDate;


    public Fragment_HomeworkInbox() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__homework_inbox, container, false);


        initViews(view);
        initListeners();
        getSchoolHomework();
        getDivisionApi();


        return view;
    }

    private void getSchoolHomework() {

        mApiService.getHomeWorkBySchool(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.isSuccessful()) {
                    try {
                        Log.i("homeinboxSChool**", "" + response.body() + "*****" + response.code());

                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");
                        if (status.equalsIgnoreCase("Success")) {
                            JSONArray jsonArray = object.getJSONArray("datadict");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objectData = jsonArray.getJSONObject(i);


                                homeworkId = objectData.getString("homework_uuid");
                                homeworkTitle = objectData.getString("homework_title");
                                description = objectData.getString("description");
                                homeClass = objectData.getString("class");
                                section = objectData.getString("section");
                                subject = objectData.getString("subject");
                                startDate = objectData.getString("start_date");
                                endDate = objectData.getString("end_date");

                                homeworkModelsInbox.add(new HomeworkModel(homeworkId, homeworkTitle, description,
                                        homeClass, section, subject, startDate, endDate));


                            }


                            setRecyclerView();
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


    private void initListeners() {
        fab.setOnClickListener(this);
        iv_backBtn.setOnClickListener(this);
        linearLayoutDate.setOnClickListener(this);
    }

    private void initViews(View view) {
        mApiService = ApiUtils.getAPIService();
        iv_backBtn = view.findViewById(R.id.iv_backBtn);
        spDivision = view.findViewById(R.id.sp_division);
        spnClass = view.findViewById(R.id.sp_class);
        linearLayoutDate = view.findViewById(R.id.ll_date);
        tvFromDate = view.findViewById(R.id.tv_fromDate);
        recycler_view = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);

        listDivision = new ArrayList<>();
        listClass = new ArrayList<>();
        homeworkModelsInbox = new ArrayList<>();
        homeworkModelsInboxDate = new ArrayList<>();



        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = simpleDate.format(calendar.getTime());

        tvFromDate.setText(currentDate);
    }


    private void getDivisionApi() {


        mApiService.getDivisions(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                listDivision.clear();
                listDivision.add(0, "Select Division");

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
                            customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listDivision);
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
                System.out.println("strDiv**"+strDiv);
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
        listClass.add(0, "Select Class");
        mApiService.getClassSections(Constant.SCHOOL_ID, jsonObject).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {


                if (response.isSuccessful()) {
                    Log.i("CLASS_SECTIONDDD", "" + response.body()+"***"+response.code());
                    try {

                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");

                        if (status.equalsIgnoreCase("success")) {
                            JSONObject jsonObject1 = object.getJSONObject("data");
                            if (object.getJSONObject("data").toString().equals("{}")) {
                                //modelArrayList.clear();
                                // customSpinnerAdapter.notifyDataSetChanged();
                                //   setSpinnerForClass();

                            } else {

                                JSONObject jsonObject2 = jsonObject1.getJSONObject(strDiv);
                                Iterator<String> keys = jsonObject2.keys();

                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    JSONObject jsonObjectValue = jsonObject2.getJSONObject(key);
                                    String className = jsonObjectValue.getString("class_name");
                                    JSONArray jsonArray = jsonObjectValue.getJSONArray("sections");

                                    listSection = new ArrayList<>();
                                    String Section = "";

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Section = jsonArray.getString(i);
                                        listSection.add(Section);
                                    }

                                    listClass.add(className);
                                    customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listClass);
                                    spnClass.setAdapter(customSpinnerAdapter);

                                }
                            }


                        }


                        //  setRecyclerView();

                    } catch (JSONException je) {

                    }

                } else {

                        listClass.clear();
                        listClass.add("Select Class");
                        customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listClass);
                        spnClass.setAdapter(customSpinnerAdapter);
                        customSpinnerAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_class = parent.getItemAtPosition(position).toString();
                if (str_class.equalsIgnoreCase("Select Class")) {
                    //Toast.makeText(getActivity(), "Please select all fields", Toast.LENGTH_SHORT).show();

                } else {

                    homeworkModelsInbox.clear();
                    homeworkModelsInboxDate.clear();

                    getHomeWorkList(str_class);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getHomeWorkList(String str_class) {
        homeworkModelsInbox.clear();

        mApiService.getHomeWorkByClass(Constant.SCHOOL_ID, str_class).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.isSuccessful()) {
                    Log.i("homeinbox**", "" + response.body() + "*****" + response.code());
                    try {

                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");
                        if (status.equalsIgnoreCase("Success")) {
                            JSONArray jsonArray = object.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objectData = jsonArray.getJSONObject(i);


                                homeworkId = objectData.getString("homework_id");
                                homeworkTitle = objectData.getString("homework_title");
                                description = objectData.getString("description");
                                homeClass = objectData.getString("class");
                                section = objectData.getString("section");
                                subject = objectData.getString("subject");
                                startDate = objectData.getString("start_date");
                                endDate = objectData.getString("end_date");

                                homeworkModelsInbox.add(new HomeworkModel(homeworkId, homeworkTitle, description,
                                        homeClass, section, subject, startDate, endDate));


                            }


                            setRecyclerView();
                        }

                    } catch (JSONException je) {

                    }

                } else {
                    Log.i("homeinbox**A", "" + response.body() + "*****" + response.code());
                    homeworkModelsInboxDate.clear();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }


    private void setRecyclerView() {

        homeworkAdapter = new HomeworkAdapter(homeworkModelsInbox, getActivity(), tvFromDate.getText().toString(),
                Constant.RV_HOMEWORK_INBOX_ROW);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setAdapter(homeworkAdapter);
        homeworkAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {

        DashBoardMenuActivity homeWorkActivity = (DashBoardMenuActivity) getActivity();

        switch (view.getId()) {

            case R.id.ll_date:
                openDateDialog();
                break;
            case R.id.fab:

                homeWorkActivity.loadFragment(Constant.HOMEWORK_CREATE_FRAGMENT, null);
                break;

            case R.id.iv_backBtn:
                getActivity().onBackPressed();
                break;
        }
    }

    private void openDateDialog() {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());


                endYear = yearFormat.format(calendar.getTime());
                str_toDate = simpleDateFormat.format(calendar.getTime());
                Log.d("CHECK_DATE", str_toDate);
                date1 = new Date();
                try {
                    date1 = simpleDateFormat.parse(str_toDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvFromDate.setText(str_toDate);
                getHomeWorkListNew(str_class);


            }
        }, year, month, date);

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();

    }

    private void getHomeWorkListNew(String str_class) {

        homeworkModelsInbox.clear();
        homeworkModelsInboxDate.clear();

        mApiService.getHomeWorkByClass(Constant.SCHOOL_ID, str_class).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("homeinbox**", "" + response.body() + "*****" + response.code());
                if (response.isSuccessful()) {
                    try {

                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");
                        if (status.equalsIgnoreCase("Success")) {
                            JSONArray jsonArray = object.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objectData = jsonArray.getJSONObject(i);


                                homeworkId = objectData.getString("homework_id");
                                homeworkTitle = objectData.getString("homework_title");
                                description = objectData.getString("description");
                                homeClass = objectData.getString("class");
                                section = objectData.getString("section");
                                subject = objectData.getString("subject");
                                startDate = objectData.getString("start_date");
                                endDate = objectData.getString("end_date");
                                if (startDate.equals(tvFromDate.getText().toString())) {

                                    homeworkModelsInboxDate.add(new HomeworkModel(homeworkId, homeworkTitle, description,
                                            homeClass, section, subject, startDate, endDate));
                                }


                            }

                            homeworkAdapter = new HomeworkAdapter(homeworkModelsInboxDate, getActivity(),
                                    tvFromDate.getText().toString(), Constant.RV_HOMEWORK_INBOX_ROW);
                            recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycler_view.setAdapter(homeworkAdapter);
                            homeworkAdapter.notifyDataSetChanged();

                            //  setRecyclerView();
                        }

                    } catch (JSONException je) {

                    }

                } else {
                    Log.i("homeinbox**B", "" + response.body() + "*****" + response.code());
                    homeworkModelsInboxDate.clear();
                    homeworkAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }


}
