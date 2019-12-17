package in.varadhismartek.patashalaerp.LoginAndRegistation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.DashboardModule.DashboardActivity;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.SelectModules.SelectModuleLandingActivity;

public class LoginScreenActivity extends AppCompatActivity {
    SharedPreferences pref = null, pref2,login_pref;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 101;
    String filter = "thisIsForMyFragment";
    Intent intent = new Intent(filter);
    boolean booleanStatus = false;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFullScreenActivity();
        setContentView(R.layout.activity_login_screen);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //This method is to detect screen size and set orientation
        DisplayMetrics metrics = new DisplayMetrics();
        LoginScreenActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches >= 6.5) {
            // 6.5inch device or bigger set orientation to Landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            // smaller device set orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        pref2 = getSharedPreferences("SecurityStatus", Context.MODE_PRIVATE);

        booleanStatus = pref2.getBoolean("activity_executed", false);
        Constant.ACTIVE_PAGE=pref2.getString("ACTIVE_PAGE", "0");

        Constant.SCHOOL_ID = pref2.getString("SchoolId", "0");
        Constant.EMPLOYEE_BY_ID = pref2.getString("EmployeeId", "0");
        Constant.POC_NAME = pref2.getString("PocName", "0");
        Constant.POC_Mobile_Number = pref2.getString("PocMobileNo", "0");

        Log.i("Constant.SCHOOL_ID***FC", Constant.SCHOOL_ID);

        Log.i("booleanStatus_Lo", "" + booleanStatus);
        Log.i("ACTIVE_PAGE", "" +  Constant.ACTIVE_PAGE);


        checkLoadingFirstTime();
        AskPermissionsNeeded();


    }

    private void getFullScreenActivity() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void checkLoadingFirstTime() {

/*        if (pref.getBoolean("activity_executed", false)) {
           // Intent intent = new Intent(this, HomeActivity.class);
            Intent intent = new Intent(this, SelectModuleLandingActivity.class);
            startActivity(intent);
            finish();

        }*/

        if (booleanStatus) {
            if (Constant.ACTIVE_PAGE.equals("5")){
               Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(this, SelectModuleLandingActivity.class);
                startActivity(intent);
                finish();
            }

        }

        else {
        /*    Intent intent = new Intent(this, AddDivisionActivity.class);
            startActivity(intent);
            finish();*/
           loadFragment(Constant.FIRST_CHOOSE_AUDIENCE_FRAGMENT, null);

        }
    }

    public void loadFragment(String fragmentString, Bundle bundle) {

        switch (fragmentString) {

            case Constant.FIRST_CHOOSE_AUDIENCE_FRAGMENT:
                callFragment(new FirstChooseAudienceFragment(), Constant.FIRST_CHOOSE_AUDIENCE_FRAGMENT, null, null);
                break;

            case Constant.SECOND_SEARCH_USER_FRAGMENT:
                callFragment(new SecondEnterMobileFragment(), Constant.SECOND_SEARCH_USER_FRAGMENT, null, null);
                break;

            case Constant.THIRD_OTP_ENTER_FRAGMENT:
                callFragment(new ThirdOTPFragment(), Constant.THIRD_OTP_ENTER_FRAGMENT, null, null);
                break;

            case Constant.FOURTH_MPIN_FRAGMENT:
                callFragment(new FourthMpinFragment(), Constant.FOURTH_MPIN_FRAGMENT, null, null);
                break;

        }
    }

    private void callFragment(Fragment fragment, String tag, String addBackStack, Bundle bundle) {
        if (bundle != null) {
            getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.headContainer, fragment, tag).addToBackStack(addBackStack).commit();
            fragment.getArguments();

        } else {

            getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.headContainer, fragment, tag).addToBackStack(addBackStack).commit();

        }

    }

    public void AskPermissionsNeeded() {

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(LoginScreenActivity.this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginScreenActivity.this,
                    Manifest.permission.READ_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(LoginScreenActivity.this,
                        new String[]{Manifest.permission.READ_SMS},
                        MY_PERMISSIONS_REQUEST_READ_SMS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(LoginScreenActivity.this, "Permission Denied to Read SMS", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
