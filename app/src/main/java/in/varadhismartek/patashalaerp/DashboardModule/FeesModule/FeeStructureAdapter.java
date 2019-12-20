package in.varadhismartek.patashalaerp.DashboardModule.FeesModule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;

public class FeeStructureAdapter extends RecyclerView.Adapter<FeeViewHolder> {
    private Context context;
    ArrayList<FeesModle> feesModles;
    String rvType;
    public FeeStructureAdapter(ArrayList<FeesModle> feesModles, Context context, String rvType) {
        this.feesModles =feesModles;
        this.context =context;
        this.rvType = rvType;

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
    public void onBindViewHolder(@NonNull FeeViewHolder holder, int position) {
        switch (rvType) {
            case Constant.RV_FEES_ROW:
                holder.tvSerialNo.setText(feesModles.get(position).getStrSerialNo());
                holder.tvFeeType.setText(feesModles.get(position).getStrFeeType());
                holder.tvFeeCode.setText(feesModles.get(position).getSteFeeCode());
                holder.tvInstallment.setText(feesModles.get(position).getStrInstallment());
                holder.tvFeeDueDate.setText(feesModles.get(position).getStrDueDate());
                break;
        }

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
