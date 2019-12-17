package in.varadhismartek.patashalaerp.DashboardModule.Assessment;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.ClassAndSection.ClassSectionModel;
import in.varadhismartek.patashalaerp.Gallery.GalleryAdapter;
import in.varadhismartek.patashalaerp.GeneralClass.CustomSpinnerAdapter;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtilsPatashala;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_QuestionBank extends Fragment implements View.OnClickListener {

    String strDiv = "", str_class = "ukg", strSection = "", str_subject = "", str_toDate = "", endYear = "", currentDate = "";

    APIService mApiService,apiService;
    ArrayList<String> listDivision;
    ArrayList<String> listClass;
    ArrayList<String> listSection;
    ArrayList<String> listSectionNew;
    ArrayList<String> listSubject;
    ArrayList<ClassSectionModel> modelArrayList;
    CustomSpinnerAdapter customSpinnerAdapter;
    TextView tvFromDate;
    ImageView iv_backBtn;
    Spinner spDivision, spnClass, spn_section, spn_Subject;
    RecyclerView recycler_view;
    Button btnSave;
    EditText edQuestionTitle, edQuestionDesc;
    ImageView ivAttachmentImage;
    int PICK_IMAGE_MULTIPLE = 1;

    String imageEncoded;
    ArrayList<File> imagesEncodedList;
    RecyclerView recyclerView;
    ArrayList<Uri> mArrayUri;
    public Fragment_QuestionBank() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__question_bank, container, false);


        initViews(view);
        //   initListeners();
        //   getSchoolHomework();
        getDivisionApi();


        return view;
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

                                String Id = object1.getString("id");
                                statusDivision = object1.getBoolean("status");
                                if (statusDivision) {
                                    String division = object1.getString("division");

                                    listDivision.add(division);
                                }

                                Log.i("Division Object***", "" + listDivision + "***" + statusDivision);
                            }

                            customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listDivision);
                            spDivision.setAdapter(customSpinnerAdapter);

                            /*JSONArray array = new JSONArray();
                            JSONObject jsonObject = new JSONObject();
                            for (int i = 0; i < listDivision.size(); i++) {


                                try {
                                    array.put(listDivision.get(i));
                                    jsonObject.put("divisions", array);
                                    Constant.DIVISION_NAME = strDiv;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                               // getClassSectionList(jsonObject);
                            }
                            getClassSectionList(jsonObject);
                            System.out.println("jsonObject***"+jsonObject);*/

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
        listClass.add(0, "Select Class");
//        customSpinnerAdapter.notifyDataSetChanged();
        mApiService.getClassSections(Constant.SCHOOL_ID, jsonObject).enqueue(new Callback<Object>() {
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

                                    modelArrayList.add(new ClassSectionModel(className, listSection));
                                    Gson gson = new Gson();

                                }
                            }


                        }

                    } catch (JSONException je) {

                    }

                } else {
                    if (String.valueOf(response.code()).equals("400")) {
                        Toast.makeText(getActivity(), "No Record", Toast.LENGTH_SHORT).show();
                    } else if (String.valueOf(response.code()).equals("409")) {
                        Toast.makeText(getActivity(), "No Record", Toast.LENGTH_SHORT).show();
                    } else {
                        listClass.clear();
                        listClass.add("Select Class");
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listClass);
        spnClass.setAdapter(customSpinnerAdapter);


        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_class = spnClass.getSelectedItem().toString();
                listSectionNew = new ArrayList<>();
                listSectionNew.clear();
                listSectionNew.add("Select Section");

                boolean b = true;

                for (int j = 0; j < modelArrayList.size(); j++) {
                    if (modelArrayList.get(j).getClassName().contains(str_class)) {
                        listSectionNew.clear();

                        for (int k = 0; k < modelArrayList.get(j).getListSection().size(); k++) {

                            listSectionNew.add(modelArrayList.get(j).getListSection().get(k));

                        }
                        getSubject(strDiv, str_class);
                    }
                }


                customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listSectionNew);
                spn_section.setAdapter(customSpinnerAdapter);

                getSubject(strDiv, str_class);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strSection = spn_section.getSelectedItem().toString();
                getSubject(strDiv, str_class);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void getSubject(final String strDiv, final String str_class) {

        mApiService.getSubject(Constant.SCHOOL_ID, strDiv, str_class).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                listSubject = new ArrayList<>();

                if (response.isSuccessful()) {
                    try {

                        listSubject.clear();
                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");
                        Log.i("SubjectList***D", "" + response.code() + "**" + status);
                        if (status.equalsIgnoreCase("Success")) {


                            JSONObject jsonObject1 = object.getJSONObject("Section");

                            Iterator<String> keys = jsonObject1.keys();

                            while (keys.hasNext()) {
                                String sectionkey = keys.next();
                                JSONObject jsonSection = jsonObject1.getJSONObject(sectionkey);
                                Iterator<String> keys2 = jsonSection.keys();

                                while (keys2.hasNext()) {
                                    String subjectkey = keys2.next();
                                    JSONObject jsonSubject = jsonSection.getJSONObject(subjectkey);

                                    String strType = jsonSubject.getString("subject_type");
                                    String strCode = jsonSubject.getString("subject_code");
                                    boolean strStatus = jsonSubject.getBoolean("status");

                                    Log.i("SubjectList***E", "" + response.code() + "**" + status + "***" + subjectkey);
                                    listSubject.add(subjectkey);


                                }
                                customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listSubject);
                                spn_Subject.setAdapter(customSpinnerAdapter);
                                // setRecyclerView();
                            }
                        } else {
                            listSubject.clear();
                            listSubject.add(0, "Select Subject");
                            customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listSubject);
                            spn_Subject.setAdapter(customSpinnerAdapter);
                        }

                    } catch (JSONException je) {

                    }
                } else {

                    Log.i("SubjectList***F", "" + response.code());
                    listSubject.clear();
                    listSubject.add(0, "Select Subject");
                    customSpinnerAdapter = new CustomSpinnerAdapter(getActivity(), listSubject);
                    spn_Subject.setAdapter(customSpinnerAdapter);
                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("SubjectList***B", "" + t.getMessage());

            }
        });
        spn_Subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_subject = spn_Subject.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initViews(View view) {
        mApiService = ApiUtils.getAPIService();
        apiService = ApiUtilsPatashala.getService();
        iv_backBtn = view.findViewById(R.id.iv_backBtn);
        spDivision = view.findViewById(R.id.spn_division);
        spnClass = view.findViewById(R.id.spn_class);
        spn_section = view.findViewById(R.id.spn_section);
        spn_Subject = view.findViewById(R.id.spn_Subject);
        btnSave = view.findViewById(R.id.btn_submit);

        edQuestionTitle = view.findViewById(R.id.ed_question_title);
        edQuestionDesc = view.findViewById(R.id.ed_description);
        ivAttachmentImage = view.findViewById(R.id.imageView_attach);
        recyclerView = view.findViewById(R.id.recycler_view);

        //  linearLayoutDate = view.findViewById(R.id.ll_date);
        tvFromDate = view.findViewById(R.id.tv_fromDate);
        recycler_view = view.findViewById(R.id.recycler_view);

        listDivision = new ArrayList<>();
        listClass = new ArrayList<>();

        modelArrayList = new ArrayList<>();
        listClass.add("Select Class");

        btnSave.setOnClickListener(this);
        ivAttachmentImage.setOnClickListener(this);
        // listSubject.add("Select Subject");
        // listSubject.clear();


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_submit:

                if (strDiv.equalsIgnoreCase("Select Division")) {
                    Toast.makeText(getActivity(), "Select Division ", Toast.LENGTH_SHORT).show();
                } else if (str_class.equalsIgnoreCase("Select Division")) {
                    Toast.makeText(getActivity(), "Select Class ", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getActivity(), "Select Submit ", Toast.LENGTH_SHORT).show();

                    if (edQuestionTitle.getText().toString().isEmpty() || edQuestionDesc.getText().toString().isEmpty()) {

                    } else {
                        String strQuesTitle = "", strQuesDesc = "";
                        strQuesTitle = edQuestionTitle.getText().toString();
                        strQuesDesc = edQuestionDesc.getText().toString();

                        addQuestionBankMethod();

                    }
                }
                break;

            case R.id.imageView_attach:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

                break;
        }
    }

    private void addQuestionBankMethod() {

   /* @
    http://13.233.109.165:8000/patashala/add_question_bank
    school_id:e4b9f0b8-78e6-4cc3-9194-defd7663a213
    class:lkg
    sections:B
    subject:English
    question_bank_title:Python question
    description:question bank
    added_employeeid:73dcb877-7dc1-4d2f-ab1e-1ca68a630f45
    question_bank_attachement:{"Question_Banks" : {"1":{"attachment_image":"image"}}}*/


        RequestBody SchoolId = RequestBody.create(MediaType.parse("multipart/form-data"), "e4b9f0b8-78e6-4cc3-9194-defd7663a213");
        RequestBody className =RequestBody.create(MediaType.parse("multipart/form-data"),"lkg");
        RequestBody sectionsName =RequestBody.create(MediaType.parse("multipart/form-data"),"B");
        RequestBody subjectName =RequestBody.create(MediaType.parse("multipart/form-data"),"English");
        RequestBody quesTitle =RequestBody.create(MediaType.parse("multipart/form-data"),"English Question");
        RequestBody quesBank =RequestBody.create(MediaType.parse("multipart/form-data"),"English Bank");
        RequestBody EmpID =RequestBody.create(MediaType.parse("multipart/form-data"),"73dcb877-7dc1-4d2f-ab1e-1ca68a630f45");


            JSONObject jsonMain = new JSONObject();
            JSONObject jsonCount = new JSONObject();




        try {
                for (int i = 0; i < imagesEncodedList.size(); i++) {
                    JSONObject jsonImage = new JSONObject();

                     File file = new File(String.valueOf(imagesEncodedList.get(i)));

                    Log.i("file**100", "" + mArrayUri.get(i).getPath() + "***" + file.getName()+"***"+file.getPath());

                    final RequestBody imageRequestBody = RequestBody.create(MediaType.parse("*/*"), file);

                    final MultipartBody.Part imageMultipart = MultipartBody.Part.
                            createFormData("image"+i, file.getName(), imageRequestBody);

                    jsonImage.put("attachment_image", imageMultipart);

                    jsonCount.put(i + "", jsonImage);


                }


        } catch (JSONException je) {
                je.printStackTrace();
            }

            try {
                Log.i("jsonCount**", "" + jsonCount);

                jsonMain.put("Question_Banks", jsonCount);
            } catch (JSONException je) {
                je.printStackTrace();

            }

            Log.i("JSONMAIN**", "" + jsonMain);

        RequestBody imageJSonRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), jsonMain.toString());




        apiService.addQuestionBank(SchoolId,className,sectionsName,subjectName,quesTitle,quesBank,
                EmpID,imageJSonRequestBody).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("ADDQUESTIONBANK**", "" + response.body()+"***"+response.code()+"****"+response.message());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });


        }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<File>();

                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    Cursor cursor = getActivity().getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    imagesEncodedList.add(new File(imageEncoded));
                    cursor.close();

                     mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);

                    setRecyclerview(mArrayUri);



                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                         mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            Cursor cursor = getActivity().getContentResolver().query(uri,
                                    filePathColumn, null, null, null);

                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(new File(imageEncoded));
                            cursor.close();

                            setRecyclerview(mArrayUri);


                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    private void setRecyclerview(ArrayList<Uri> mArrayUri) {

        GalleryAdapter adapter = new GalleryAdapter(getActivity(), mArrayUri, Constant.RV_DIALOG_IMAGE_GALLERY);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
