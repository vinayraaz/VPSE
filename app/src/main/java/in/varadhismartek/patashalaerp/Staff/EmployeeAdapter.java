package in.varadhismartek.patashalaerp.Staff;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    Context  context;
    ArrayList<EmpLeaveModel> allEmployeeList;
    String rvType;
    public EmployeeAdapter(Context context, ArrayList<EmpLeaveModel> employeeList, String rvType) {
        this.context =context;
        this.allEmployeeList =employeeList;
        this.rvType =rvType;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (rvType){
            case Constant.RV_EMPLOYEE_LIST:
                return new EmployeeViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.employees_row, viewGroup, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {
        holder.tvEmpName.setText("Name : "+allEmployeeList.get(position).getEmpFname() +" "+allEmployeeList.get(position).getEmpLname());
        holder.tvGender.setText(allEmployeeList.get(position).getSex());
        holder.tvRole.setText(allEmployeeList.get(position).getRole());
        holder.tvDept.setText(allEmployeeList.get(position).getEmpDept());
        holder.tvStatus.setText(allEmployeeList.get(position).getEmployee_status());
        holder.llEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*  EmployeeModule employeeActivity =(EmployeeModule)context;

                Bundle bundle = new Bundle();

                bundle.putString("FNAME", allEmployeeList.get(position).getEmpFname());
                bundle.putString("LNAME", allEmployeeList.get(position).getEmpLname());
                bundle.putString("ADHAARNO", allEmployeeList.get(position).getEmpAdhaarNo());
              //  bundle.putString("Fragment_Type", "EMPLOYEE_DETAILS");
                employeeActivity.loadFragment(Constant.EMPLOYEE_VIEW_FRAGMENT, bundle);*/
                Intent intent = new Intent(context,EmployeeModule.class);
                intent.putExtra("FNAME", allEmployeeList.get(position).getEmpFname());
                intent.putExtra("LNAME", allEmployeeList.get(position).getEmpLname());
                intent.putExtra("ADHAARNO", allEmployeeList.get(position).getEmpAdhaarNo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        switch (rvType){
            case Constant.RV_EMPLOYEE_LIST:
                return allEmployeeList.size();
        }
        return 0;
    }
}
