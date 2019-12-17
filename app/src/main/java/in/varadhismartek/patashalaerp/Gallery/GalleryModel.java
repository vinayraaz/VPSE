package in.varadhismartek.patashalaerp.Gallery;

import android.net.Uri;

import java.util.ArrayList;

public class GalleryModel {

    String date;
    String galleryTitle;
    ArrayList<Uri> uriArrayList;

    public GalleryModel(String date, String galleryTitle, ArrayList<Uri> uriArrayList) {
        this.date = date;
        this.galleryTitle = galleryTitle;
        this.uriArrayList = uriArrayList;
    }


    public String getDate() {
        return date;
    }

    public ArrayList<Uri> getUriArrayList() {
        return uriArrayList;
    }

    public String getGalleryTitle() {
        return galleryTitle;
    }
}
