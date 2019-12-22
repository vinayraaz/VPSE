package in.varadhismartek.patashalaerp.DashboardModule.FeesModule;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.ColorSpace.Model.XYZ;

public class FeeStructureAdapter extends RecyclerView.Adapter<FeeViewHolder> {
    private Context context;
    ArrayList<FeesModle> feesModles;
    String rvType;
    APIService apiService;
    public FeeStructureAdapter(ArrayList<FeesModle> feesModles, Context context, String rvType) {
        this.feesModles =feesModles;
        this.context =context;
        this.rvType = rvType;

        apiService = ApiUtils.getAPIService();

    }

    @NonNull
    @Override
    public FeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (rvType) {

            case Constant.RV_FEES_ROW:
                return new FeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fees_row,
                        parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FeeViewHolder holder, final int position) {
        switch (rvType) {
            case Constant.RV_FEES_ROW:
                holder.tvSerialNo.setText(feesModles.get(position).getStrSerialNo());
                holder.tvFeeType.setText(feesModles.get(position).getStrFeeType());
                holder.tvFeeCode.setText(feesModles.get(position).getSteFeeCode());
                holder.tvInstallment.setText(feesModles.get(position).getStrInstallment());
                holder.tvFeeDueDate.setText(feesModles.get(position).getStrDueDate());
                holder.linearLayoutFees.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strSerialNo,strFeesType,strFeeCode,strInstallment,strDueDate;

                        strSerialNo=feesModles.get(position).getStrSerialNo();
                        strFeesType= feesModles.get(position).getStrFeeType();
                        strFeeCode=feesModles.get(position).getSteFeeCode();
                        strInstallment=feesModles.get(position).getStrInstallment();
                        strDueDate=feesModles.get(position).getStrDueDate();
                        updateDialogPop(strSerialNo,strFeesType,strFeeCode,strInstallment,strDueDate);
                    }
                });
                break;
        }

    }

    private void updateDialogPop(final String strSerialNo, final String strFeesType, String strFeeCode, String strInstallment, String strDueDate) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_update_fees_structure);
        //noinspection ConstantConditions
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        final EditText edFeeType = dialog.findViewById(R.id.et_fee_type);
        final EditText edFeeCode = dialog.findViewById(R.id.et_fee_code);
        final EditText edInstallment = dialog.findViewById(R.id.et_installment);
        final TextView edFeeDueDate = dialog.findViewById(R.id.tv_due_date);
        final Button btnCancel = dialog.findViewById(R.id.bt_cancel);
        final Button btnUpdate = dialog.findViewById(R.id.bt_update);

        edFeeType.setText(strFeesType);
        edFeeCode.setText(strFeeCode);
        edInstallment.setText(strInstallment);
        edFeeDueDate.setText(strDueDate);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         /*       school_id:ee99d168-cd9d-4f22-9632-4fb36f8e5747
                added_employeeid:d299d357-291a-4466-aef4-3386f98f7ac4
                academic_start_date:2019-01-01
                new_status:false
                session_serial_no:2
                new_fee_type:FEE_TYPE
                new_installment:5
                new_due_date:2020-03-12
                new_fee_code:XYZ
                old_fee_type:Exam*/
                        apiService.updateFeeStructure(Constant.SCHOOL_ID,"2019-12-12",Constant.EMPLOYEE_BY_ID,
                                "true",strSerialNo,strFeesType,edFeeType.getText().toString(),
                                edInstallment.getText().toString(),edFeeDueDate.getText().toString(),
                               edFeeCode.getText().toString() ).enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                Log.i("UPDATE_FEES",""+response.body()+"****"+response.code());
                                if (response.isSuccessful()){
                                    Log.i("UPDATE_FEES1",""+response.body()+"****"+response.code());

                                    dialog.dismiss();
                                }

                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {

                            }
                        });

            }
        });
    }

    @Override
    public int getItemCount() {
        switch (rvType) {
            case Constant.RV_FEES_ROW:
                return feesModles.size();
        }

        return 0;
    }
}
