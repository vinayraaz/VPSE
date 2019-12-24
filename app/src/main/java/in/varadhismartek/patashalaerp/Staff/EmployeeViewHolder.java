package in.varadhismartek.patashalaerp.Staff;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.varadhismartek.patashalaerp.R;

class EmployeeViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llEmployee;
    public TextView tvEmpName,tvRole,tvDept,tvStatus,tvGender;
    public EmployeeViewHolder(View itemView) {
        super(itemView);
        tvEmpName =itemView.findViewById(R.id.tv_name);
        tvGender =itemView.findViewById(R.id.tv_gender);
        tvRole =itemView.findViewById(R.id.tv_roles);
        tvDept =itemView.findViewById(R.id.tv_dept);
        tvStatus =itemView.findViewById(R.id.tv_status);
        llEmployee =itemView.findViewById(R.id.ll_emp_row);
    }
}
