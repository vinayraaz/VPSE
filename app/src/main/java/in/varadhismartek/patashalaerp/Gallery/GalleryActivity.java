package in.varadhismartek.patashalaerp.Gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_container);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new GalleryLandingFragment()).commit();

        callFragment(Constant.GALLERY_LANDING_FRAGMENT, null);

        /*ImageGallery imageGallery = (ImageGallery) findViewById(R.id.imageGallery);
        imageGallery
                .setImages(getImages())
                //.setImages(getImagesAsArray())
                .setLanguageHelper(new LanguageHelper(this)
                        .setNoImagesAvailable("No images are available!")
                        .setOutOf("out of"))
                .setOnLargeImageClickCallback(new OnClickCallback() {
                    @Override
                    public void OnClick(String currentImageUrl) {
                        Toast.makeText(getApplicationContext(), "Clicked image", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }

    private List<String> getImages() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/Dbmy6yY.jpg");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");
        imageUrls.add("https://i.imgur.com/nJB4kqZ.png");

        return imageUrls;
    }

    private String[] getImagesAsArray() {
        Object[] objectList = getImages().toArray();
        return Arrays.copyOf(objectList, objectList.length, String[].class);
    }*/

    }

    public void callFragment(String fragmentTAG, Bundle bundle) {

        switch (fragmentTAG){

            case Constant.GALLERY_LANDING_FRAGMENT:
                callFragment(new GalleryLandingFragment(), Constant.GALLERY_LANDING_FRAGMENT, null, null);
                break;

        }
    }

    private void callFragment(Fragment fragment, String tag, String addBackStack, Bundle bundle) {

        if (bundle != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment, tag)
                    .addToBackStack(addBackStack)
                    .commit();
            fragment.setArguments(bundle);

        }else{

            if (Constant.SCHEDULE_FRAGMENT.equals(tag)){
                getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container, fragment, tag)
                        .commit();
            }

            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment, tag)
                    .addToBackStack(addBackStack)
                    .commit();

        }

    }

}