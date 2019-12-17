package in.varadhismartek.patashalaerp.Gallery;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.Gallery.Models.GalleryImage;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.Retrofit.APIService;
import in.varadhismartek.patashalaerp.Retrofit.ApiUtilsPatashala;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GalleryLandingFragment extends Fragment implements View.OnClickListener {

    ImageView iv_backBtn, iv_upload;
    RecyclerView recycler_view;

    String currentDate;

    private static final int PICK_BY_CAMERA = 3;
    private static final int PICK_IMAGE_MULTIPLE = 2;
    protected Uri filePathUri;

    ArrayList<GalleryModel> galleryModelArrayList;
    ArrayList<String> arrayListImage = new ArrayList<>();

    public static ArrayList<Uri> LIST_URI = new ArrayList<>();
    ArrayList<Uri> mArrayUri = new ArrayList<>();

    Dialog dialog;
    TextView tv_date;
    EditText et_gallery_title;
    TextView tv_add;
    RecyclerView recyclerViewDialog;
    LinearLayout camera;
    LinearLayout gallery;

    //new
    APIService apiService;
    ArrayList<GalleryModel> galleryModel;
    ArrayList<GalleryImage> galleryImages;
    String galleryID, galleryDesc, galleryTitle, galleryAdded;
    File file;
    String strTitle = "";
    ArrayList<File> imagesEncodedList;
    public GalleryLandingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_landing, container, false);

        initViews(view);
        initListeners();
        getTodayDate();
        //getGalleryAPI();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONObject jsonObject1 = obj.getJSONObject("data");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("gallery_details");

            Iterator<String> keys = jsonObject2.keys();

            while (keys.hasNext()) {

                String key = keys.next();
                JSONObject jsonObjectValue = jsonObject2.getJSONObject(key);

                galleryID = jsonObjectValue.getString("gallery_id");
                galleryDesc = jsonObjectValue.getString("description");
                galleryTitle = jsonObjectValue.getString("title");
                galleryAdded = jsonObjectValue.getString("added_by");


                System.out.println("obj****" + galleryID);

                JSONObject galleryJson = jsonObjectValue.getJSONObject("gallery_image");
                Iterator<String> galleryImagekey = galleryJson.keys();


                while (galleryImagekey.hasNext()) {
                    galleryImages = new ArrayList<>();
                    String strGalleryImage = galleryImagekey.next();

                    JSONObject imageJsonData = galleryJson.getJSONObject(strGalleryImage);
                    String imageDesc = imageJsonData.getString("gallery_description");
                    String imageName = imageJsonData.getString("gallery_image");
                    //  galleryImages.add(new GalleryImage(imageDesc,imageName));
                    System.out.println("gallery_description****" + imageDesc);
                    //   galleryModel.add(new GalleryModel(galleryID,galleryDesc,galleryTitle,galleryAdded,galleryImages));

                }

            }
            // setRecyclerview();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("galleryfile.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    private void initListeners() {

        iv_backBtn.setOnClickListener(this);
        iv_upload.setOnClickListener(this);
    }

    private void initViews(View view) {

        iv_backBtn = view.findViewById(R.id.iv_backBtn);
        iv_upload = view.findViewById(R.id.iv_upload);
        recycler_view = view.findViewById(R.id.recycler_view);

        galleryModelArrayList = new ArrayList<>();
        dialog = new Dialog(getActivity());


        apiService = ApiUtilsPatashala.getService();
        galleryModel = new ArrayList<>();
    }


    private void getGalleryAPI() {

        Constant.SCHOOL_ID = "4ad08911-3f53-4041-afa3-04aff062aaf0";
        apiService.getGallery(Constant.SCHOOL_ID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.i("GalleryList**", "" + response.body());
                    try {
                        JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                        String status = (String) object.get("status");


                        if (status.equalsIgnoreCase("success")) {

                            try {
                                JSONObject obj = new JSONObject(loadJSONFromAsset());

                                JSONObject jsonObject1 = obj.getJSONObject("data");
                                JSONObject jsonObject2 = jsonObject1.getJSONObject("gallery_details");

                                Iterator<String> keys = jsonObject2.keys();

                                while (keys.hasNext()) {

                                    String key = keys.next();
                                    JSONObject jsonObjectValue = jsonObject2.getJSONObject(key);

                                    galleryID = jsonObjectValue.getString("gallery_id");
                                    galleryDesc = jsonObjectValue.getString("description");
                                    galleryTitle = jsonObjectValue.getString("title");
                                    galleryAdded = jsonObjectValue.getString("added_by");


                                    System.out.println("obj****" + galleryID);

                                    JSONObject galleryJson = jsonObjectValue.getJSONObject("gallery_image");
                                    Iterator<String> galleryImagekey = galleryJson.keys();


                                    while (galleryImagekey.hasNext()) {
                                        galleryImages = new ArrayList<>();
                                        String strGalleryImage = galleryImagekey.next();

                                        JSONObject imageJsonData = galleryJson.getJSONObject(strGalleryImage);
                                        String imageDesc = imageJsonData.getString("gallery_description");
                                        String imageName = imageJsonData.getString("gallery_image");
                                        //  galleryImages.add(new GalleryImage(imageDesc,imageName));
                                        System.out.println("gallery_description****" + imageDesc);
                                        //   galleryModel.add(new GalleryModel(galleryID,galleryDesc,galleryTitle,galleryAdded,galleryImages));

                                    }

                                }
                                //  setRecyclerview();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    private void getTodayDate() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = dateFormat.format(calendar.getTime());

        Log.d("CURRENT_DATE", currentDate);
    }

    private void setRecyclerView(ArrayList<GalleryModel> list) {

        GalleryAdapter adapter = new GalleryAdapter(list, getActivity(), Constant.RV_GALLERY_IMAGES);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
  public void onClick(View view) {

        switch (view.getId()) {

            case R.id.iv_backBtn:
                getActivity().onBackPressed();
                break;

            case R.id.iv_upload:
                setImagesInDialog();
                break;
        }

    }

    private void openCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICK_BY_CAMERA);
    }

    private void openGallery() {


        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Image"), PICK_IMAGE_MULTIPLE);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK) {


            mArrayUri.clear();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
             imagesEncodedList = new ArrayList<File>();


            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();

                for (int i = 0; i < mClipData.getItemCount(); i++) {

                  /*  ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    File file = new File(uri.getPath());
                    Log.d("uriioewrwe", uri.toString() + "****" + file);
                    mArrayUri.add(uri);*/

                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    mArrayUri.add(uri);
                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn,
                            null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String  imageEncoded  = cursor.getString(columnIndex);
                    imagesEncodedList.add(new File(imageEncoded));

//                    file2 = new File(imageEncoded);

                    System.out.println("imageEncoded***"+imageEncoded);
                    cursor.close();

                }


                Log.v("LOG_TAG", "Selected Images" + mArrayUri.size()+"***"+mArrayUri.get(0));

                setRecyclerViewForDialog(mArrayUri);

            } else {

                mArrayUri.clear();

                if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    mArrayUri.add(mImageUri);

                    Log.d("mArrayUri**", "onActivityResult: " + mImageUri);

                    setRecyclerViewForDialog(mArrayUri);
                }

            }

        } else if (requestCode == PICK_BY_CAMERA) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            Bitmap bmp = Bitmap.createScaledBitmap(photo, 1024, 600, true);
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bmp, "Title", null);

            filePathUri = Uri.parse(path);
            Log.v("LOG_TAG_path", path);
            mArrayUri.add(filePathUri);
               setRecyclerViewForDialog(mArrayUri);
            Log.d("filePathUri**", "onActivityResult: " + filePathUri+"***"+mArrayUri.get(0).getPath());

        }
    }


    private void setImagesInDialog() {

        dialog.setContentView(R.layout.dialog_camera_gallery);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.flag_transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        tv_date = dialog.findViewById(R.id.tv_date);
        et_gallery_title = dialog.findViewById(R.id.et_gallery_title);
        tv_add = dialog.findViewById(R.id.tv_add);
        recyclerViewDialog = dialog.findViewById(R.id.recycler_view);
        camera = dialog.findViewById(R.id.ll_camera);
        gallery = dialog.findViewById(R.id.ll_gallery);

        tv_date.setText(currentDate);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Camera Selected...", Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Gallery Selected...", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tag = et_gallery_title.getText().toString();

                if (tag.equals(""))
                    Toast.makeText(getActivity(), "Gallery Title Is Required", Toast.LENGTH_SHORT).show();

                else if (mArrayUri.size() == 0)
                    Toast.makeText(getActivity(), "Images is Required", Toast.LENGTH_SHORT).show();

                else {

                    ArrayList<Uri> list = new ArrayList<>();
                    list.addAll(mArrayUri);


                    //Call to upload_gallery_image Api for upload images
                    galleryModelArrayList.add(new GalleryModel(currentDate, tag, list));
                    setRecyclerView(galleryModelArrayList);
                    dialog.dismiss();

                    Gson gson = new Gson();
                    Log.i("mArrayUri***", "" + mArrayUri.size()+"****"+list.size()+"***"+imagesEncodedList.size());


/*school_id:e4b9f0b8-78e6-4cc3-9194-defd7663a213
employee_id:73dcb877-7dc1-4d2f-ab1e-1ca68a630f45

multipart/form-data"

 RequestBody school_id = RequestBody.create(MediaType.parse("text/plain"),
                            sID);
                    RequestBody employee_id = RequestBody.create(MediaType.parse("text/plain"),
                            "73dcb877-7dc1-4d2f-ab1e-1ca68a630f45");
                    RequestBody first_name = RequestBody.create(MediaType.parse("text/plain"), "David");
                    RequestBody last_name = RequestBody.create(MediaType.parse("text/plain"), "Warner");
                    RequestBody phone_no = RequestBody.create(MediaType.parse("text/plain"), "91");
                    RequestBody adhaar_card_no = RequestBody.create(MediaType.parse("text/plain"), "92");
                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "descriptionH");
                    RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "titleH");
*/

String sID="e4b9f0b8-78e6-4cc3-9194-defd7663a213";

                    RequestBody school_idnew = RequestBody.create(MediaType.parse("text/plain"),
                            sID);
                    RequestBody employee_id = RequestBody.create(MediaType.parse("text/plain"),
                            "73dcb877-7dc1-4d2f-ab1e-1ca68a630f45");
                    RequestBody first_name = RequestBody.create(MediaType.parse("text/plain"), "David");
                    RequestBody last_name = RequestBody.create(MediaType.parse("text/plain"), "Warner");
                    RequestBody phone_no = RequestBody.create(MediaType.parse("text/plain"), "91");
                    RequestBody adhaar_card_no = RequestBody.create(MediaType.parse("text/plain"), "92");
                    RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "descriptionH");
                    RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "titleH");


                    System.out.println("school_id***"+gson.toJson(school_idnew)+"****"+gson.toJson(employee_id)+"***"+school_idnew);


                    JSONObject jsonMain = new JSONObject();
                    JSONObject jsonCount = new JSONObject();


                    try {
                        for (int i = 0; i < imagesEncodedList.size(); i++) {
                            JSONObject jsonImage = new JSONObject();

                            File file = new File(String.valueOf(imagesEncodedList.get(i)));

                            Log.i("file**100", "" + mArrayUri.get(i).getPath()+"***"+file.getName());



                            final RequestBody imageRequestBody = RequestBody.create
                                    (MediaType.parse("image/*"), file);



                            final MultipartBody.Part imageMultipart = MultipartBody.Part.
                                    createFormData("image"+i, file.getName(), imageRequestBody);

                            RequestBody gallery_description = RequestBody.create(MediaType.parse("text/plain"), tag);

                            jsonImage.put("attachement_image", imageMultipart);
                            jsonImage.put("gallery_description", gallery_description);

                            jsonCount.put(i + "", jsonImage);





                         /*   builder.addFormDataPart("attachement_image", fileNew.getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), fileNew));

                            RequestBody gallery_description = RequestBody.create(MediaType.parse("text/plain"), tag);*/




                        }

                    } catch (JSONException je) {
                        je.printStackTrace();
                    }

                    try {
                        jsonMain.put("Gallery", jsonCount);
                    } catch (JSONException je) {
                        je.printStackTrace();

                    }

                    Log.i("JSONMAIN**", "" + jsonMain);



//API CALL
 /*  builder.addFormDataPart("gallery_image", jsonMain.toString());
  MultipartBody requestBody = builder.build();
*/


                    RequestBody imageJSonRequestBody = RequestBody.create(MediaType.parse("text/plain"), jsonMain.toString());

                    apiService.createGallery(school_idnew, employee_id, first_name, last_name, phone_no, adhaar_card_no
                            , description, title,  imageJSonRequestBody).enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.i("jhjhjk", "" + response.code());

                            Log.i("Image res**", "result" + response.body());

                            if (response.isSuccessful()) {

                            }
                            else {

                                try {
                                    assert response.errorBody()!=null;
                                    JSONObject object = new JSONObject(response.errorBody().string());
                                    String message = object.getString("message");
                                    Log.d("ADMIN_APIIMAGE**", message);
                                    Toast.makeText(getActivity(), "out88888 "+message, Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.i("jhjhjkERROR", "" + t.toString());
                            Log.i("jhjhjkERROR1", "" + t.getMessage());

                        }
                    });


                }

            }
        });

    }

    private void setRecyclerViewForDialog(ArrayList<Uri> uriList) {


        GalleryAdapter adapter = new GalleryAdapter(getActivity(), uriList, Constant.RV_DIALOG_IMAGE_GALLERY);
        recyclerViewDialog.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerViewDialog.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}







                    /*Call<ResponseBody> call = apiService.createGallery(requestBody);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.i("RES_Image***", "" + response.body());
                            Log.i("RES_Image***", "" + response.code());
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("RES_Image***", "" + t.getMessage());
                        }
                    });*/





/*
                    MultipartBody.Builder builder = new MultipartBody.Builder();
                    builder.setType(MultipartBody.FORM);

                    builder.addFormDataPart("school_id", "889c953a-6968-4d01-8edf-e3fba43288ef");
                    builder.addFormDataPart("employee_id", "fc22c806-4e4b-4a38-9390-e2a03d7588c6");
                    builder.addFormDataPart("first_name", "Sharath");
                    builder.addFormDataPart("last_name", "Kumar");
                    builder.addFormDataPart("phone_no", "8277616360");
                    builder.addFormDataPart("adhaar_card_no", "8277616360");
                    builder.addFormDataPart("description", "decemberImage12");
                    builder.addFormDataPart("title", "title");*/



















