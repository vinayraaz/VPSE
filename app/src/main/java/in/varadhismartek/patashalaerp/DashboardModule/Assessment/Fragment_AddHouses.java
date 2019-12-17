package in.varadhismartek.patashalaerp.DashboardModule.Assessment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.Adapters.RecyclerAdapter;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.MyModel;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_AddHouses extends Fragment implements View.OnClickListener {
    private RecyclerView rvAddHouses;
    private ImageView ivBack;
    private FloatingActionButton fab;
    private ArrayList<MyModel> arrayList = new ArrayList<>();
    private String colorName;
    APIService apiService;

    public Fragment_AddHouses() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_houses, container, false);

        initViews(view);
        initListeners();
        getHouseApi();
        setRecyclerView();

        return view;
    }

    private void getHouseApi() {
        apiService.getHouseList(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("House**", "" + response.body());
                Log.i("House**", "" + response.code());

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void initListeners() {
        ivBack.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    private void initViews(View v) {
        apiService = ApiUtils.getAPIService();
        rvAddHouses = v.findViewById(R.id.rvAddHouses);
        ivBack = v.findViewById(R.id.ivBack);
        fab = v.findViewById(R.id.fab);
    }

    private void setRecyclerView() {

        //if (arrayList.size()>0){
        Log.i("HOUSEDD**", "" + arrayList);
        RecyclerAdapter adapter = new RecyclerAdapter(arrayList, getActivity(), Constant.RV_ADD_HOUSES);
        rvAddHouses.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvAddHouses.setAdapter(adapter);
        // }

    }

    @Override
    public void onClick(View v) {

        assert getActivity() != null;
        switch (v.getId()) {
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;

            case R.id.fab:
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.add_houses_dialog);
                //noinspection ConstantConditions
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();

                setDialog(dialog);
                break;
        }
    }

    private void setDialog(final Dialog dialog) {


        assert getActivity() != null;

        LinearLayout llAdd = dialog.findViewById(R.id.llAdd);
        Spinner spinnerColorName = dialog.findViewById(R.id.spinnerColorName);

        final EditText etHouseName = dialog.findViewById(R.id.etHouseName);
        final EditText etTeacher = dialog.findViewById(R.id.etTeacher);
        final EditText etCaption = dialog.findViewById(R.id.etCaption);
        final EditText etViceCaption = dialog.findViewById(R.id.etViceCaption);
        final EditText edStudentNo = dialog.findViewById(R.id.ed_studentNo);

        List<String> categories = new ArrayList<>();
        categories.add("Green");
        categories.add("Blue");
        categories.add("Red");
        categories.add("Yellow");
        categories.add("Orange");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColorName.setAdapter(dataAdapter);

        spinnerColorName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        colorName = "#1CBE22";
                        break;

                    case 1:
                        colorName = "#023EF8";
                        break;

                    case 2:
                        colorName = "#F81200";
                        break;

                    case 3:
                        colorName = "#FF9800";
                        break;

                    case 4:
                        colorName = "#FF5722";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHouseName.getText().toString().isEmpty() || etTeacher.getText().toString().isEmpty() ||
                        etCaption.getText().toString().isEmpty() || etViceCaption.getText().toString().isEmpty() ||
                        edStudentNo.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill up att fileds", Toast.LENGTH_SHORT).show();
                } else {

                    final String houseName = etHouseName.getText().toString();
                    final String teacher = etTeacher.getText().toString();
                    final String caption = etCaption.getText().toString();
                    final String viceCaption = etViceCaption.getText().toString();
                    final String studentNo = edStudentNo.getText().toString();

                    //arrayList.add(new MyModel(houseName, teacher, caption, viceCaption, studentNo, colorName));
                    arrayList.add(new MyModel("Myhouse", "teacher", "Caption",
                            "ViceCaption", "25", colorName));

                    setRecyclerView();
                    dialog.dismiss();
                }
            }
        });

    }
}
