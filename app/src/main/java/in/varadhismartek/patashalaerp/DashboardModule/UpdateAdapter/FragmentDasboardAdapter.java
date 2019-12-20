package in.varadhismartek.patashalaerp.DashboardModule.UpdateAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.varadhismartek.patashalaerp.DashboardModule.Assessment.AssessmentModuleActivity;
import in.varadhismartek.patashalaerp.DashboardModule.DashBoardMenuActivity;
import in.varadhismartek.patashalaerp.DashboardModule.FeesModule.FeesActivity;
import in.varadhismartek.patashalaerp.Gallery.GalleryActivity;
import in.varadhismartek.patashalaerp.R;

public class FragmentDasboardAdapter extends RecyclerView.Adapter<FragmentDasboardAdapter.FragmentDashboardViewHolder> {
    Context context;
    ArrayList<String> name = new ArrayList<>();

    public FragmentDasboardAdapter(Context context, ArrayList<String> name) {
        this.context = context;
        this.name = name;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public FragmentDashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FragmentDashboardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FragmentDashboardViewHolder holder, int position) {
        holder.tvModuleName.setText(name.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String menuName= holder.tvModuleName.getText().toString();

                if (menuName.equalsIgnoreCase("Homework")) {
                    Intent intent = new Intent(context, DashBoardMenuActivity.class);
                    intent.putExtra("Fragment_Type", "HomeWorkInbox");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    //((Activity)context).finish();
                }
                else if (menuName.equalsIgnoreCase("Gallery")){
                    Intent intent = new Intent(context, GalleryActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
                else if (menuName.equalsIgnoreCase("Fees")){
                    Intent intent = new Intent(context, FeesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }


                /*final */
                else if (menuName.equalsIgnoreCase("Assesment")){
                    Intent intent = new Intent(context, AssessmentModuleActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
                else if (menuName.equalsIgnoreCase("Add Subject")){
                    Intent intent = new Intent(context, DashBoardMenuActivity.class);
                    intent.putExtra("Fragment_Type","Subject");
                    context.startActivity(intent);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class FragmentDashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvModuleName;

        public FragmentDashboardViewHolder(View itemView) {
            super(itemView);
            tvModuleName = itemView.findViewById(R.id.tvMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
