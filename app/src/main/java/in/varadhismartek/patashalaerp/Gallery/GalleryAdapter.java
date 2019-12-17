package in.varadhismartek.patashalaerp.Gallery;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.Gallery.ViewHolders.GalleryHolder;
import in.varadhismartek.patashalaerp.R;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {

    private ArrayList<GalleryModel> galleryModelArrayList;
    ArrayList<Uri> mArrayUri;
    private Context context;
    private String recyclerTag;


    public GalleryAdapter(ArrayList<GalleryModel> galleryModelArrayList, Context context, String recyclerTag){

        this.galleryModelArrayList = galleryModelArrayList;
        this.context = context;
        this.recyclerTag = recyclerTag;
    }

    public GalleryAdapter(Context context, ArrayList<Uri> mArrayUri, String recyclerTag) {

        this.mArrayUri = mArrayUri;
        this.context = context;
        this.recyclerTag = recyclerTag;
    }



    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (recyclerTag){

            case Constant.RV_GALLERY_IMAGES:
                return new GalleryHolder(LayoutInflater.from(context).inflate(R.layout.gallery_date_row, viewGroup, false));

            case Constant.RV_DIALOG_IMAGE_GALLERY:
                return new GalleryHolder(LayoutInflater.from(context).inflate(R.layout.attachment_image_row, viewGroup, false));

            case Constant.RV_NESTED_GALLERY_IMAGE:
                return new GalleryHolder(LayoutInflater.from(context).inflate(R.layout.layout_image_select, viewGroup, false));

        }

        return null;//RV_DIALOG_IMAGE_GALLERY
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, final int position) {

        switch (recyclerTag){

            case Constant.RV_GALLERY_IMAGES:

                holder.tv_date.setText(galleryModelArrayList.get(position).getDate());
                holder.tv_gallery_title.setText(galleryModelArrayList.get(position).getGalleryTitle());

                GalleryAdapter adapter = new GalleryAdapter(context, galleryModelArrayList.get(position).getUriArrayList(),Constant.RV_NESTED_GALLERY_IMAGE);
                holder.recycler_view.setLayoutManager(new GridLayoutManager(context, 4));
                holder.recycler_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                break;

            case Constant.RV_DIALOG_IMAGE_GALLERY:

                holder.iv_attach.setImageURI(mArrayUri.get(position));

                holder.iv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mArrayUri.remove(position);
                        notifyDataSetChanged();

                        if (GalleryLandingFragment.LIST_URI.size()>0)
                            GalleryLandingFragment.LIST_URI.clear();

                        GalleryLandingFragment.LIST_URI = mArrayUri;
                    }
                });

                break;

            case Constant.RV_NESTED_GALLERY_IMAGE:

                holder.ivImages.setImageURI(mArrayUri.get(position));
                break;


        }
    }

    @Override
    public int getItemCount() {

        switch (recyclerTag) {

            case Constant.RV_GALLERY_IMAGES:
                return galleryModelArrayList.size();

            case Constant.RV_DIALOG_IMAGE_GALLERY:
                return mArrayUri.size();

            case Constant.RV_NESTED_GALLERY_IMAGE:
                return mArrayUri.size();
        }

        return 0;
    }
}
