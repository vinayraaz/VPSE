package in.varadhismartek.patashalaerp.DashboardModule.UpdateAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.varadhismartek.patashalaerp.DashboardModule.Assessment.AssessmentModuleActivity;
import in.varadhismartek.patashalaerp.DashboardModule.DashboardSettingActivity;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.HomeWorkActivity;
import in.varadhismartek.patashalaerp.DashboardModule.extraUtils.ModuleModel;
import in.varadhismartek.patashalaerp.R;


public class GridViewAdapter extends BaseAdapter /*implements Filterable*/ {
    private List<ModuleModel> dataList;
    Context mContext;

    public GridViewAdapter(Context mContext, List<ModuleModel> datas, int page) {
        this.mContext = mContext;
        dataList = new ArrayList<>();
        int start = page * DashboardSettingActivity.item_grid_num;
        int end = start + DashboardSettingActivity.item_grid_num;
        while ((start < datas.size()) && (start < end)) {
            dataList.add(datas.get(start));
            start++;
        }
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View itemView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (itemView == null) {
            mHolder = new ViewHolder();
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gridview, viewGroup, false);
            mHolder.iv_img =  itemView.findViewById(R.id.iv_img);
            mHolder.tv_text =  itemView.findViewById(R.id.tv_text);
            mHolder.rlModules =  itemView.findViewById(R.id.rlModules);
            itemView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) itemView.getTag();
        }
        ModuleModel bean = dataList.get(i);
        Log.i("Beans***",""+bean);
        Log.i("Beans***",""+bean.getName());

        if (bean != null) {
            mHolder.iv_img.setImageResource(dataList.get(i).getImage());
            mHolder.tv_text.setText(dataList.get(i).getName());

            mHolder.rlModules.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataList.get(i).getName().equalsIgnoreCase("Assessment")){
                        Intent intent = new Intent(mContext, AssessmentModuleActivity.class);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                    }else if (dataList.get(i).getName().equalsIgnoreCase("HomeWork")){
                        Intent intent = new Intent(mContext, HomeWorkActivity.class);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                    }

                    else {

                        Toast.makeText(mContext, "" + dataList.get(i).getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return itemView;
    }

   /* public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults result = new FilterResults();
                List<ModuleModel> allJournals = getFilter()

               *//* constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < dataList.size(); i++) {
                    String dataNames = dataList.get(i).getName();
                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(dataNames);
                        Log.e("VALUES**", dataNames);
                    }
                }
                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());*//*
                if(constraint == null || constraint.length() == 0){

                    result.values = allJournals;
                    result.count = allJournals.size();
                }else{
                    ArrayList<JournalModel> filteredList = new ArrayList<JournalModel>();
                    for(JournalModel j: allJournals){
                        if(j.source.title.contains(constraint))
                            filteredList.add(j);
                    }
                    result.values = filteredList;
                    result.count = filteredList.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataList = (List<ModuleModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }*/

    private class ViewHolder {
        private ImageView iv_img;
        private TextView tv_text;
        private RelativeLayout rlModules;
    }
}
