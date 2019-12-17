package in.varadhismartek.patashalaerp.DashboardModule.Homework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.R;

public class HomeWorkActivity extends AppCompatActivity {
    String MenuName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);


        Bundle b = getIntent().getExtras();
        if (b!= null){
            MenuName= b.getString("MENU_TYPE");
        }

        if (MenuName.equalsIgnoreCase("Homework")){
            loadFragment(Constant.HOMEWORK_INBOX_FRAGMENT, null);
        }else {


            loadFragment(Constant.HOMEWORK_BARRIER_FRAGMENT, null);
        }

    }

    public void loadFragment(String fragmentString, Bundle bundle) {

        switch (fragmentString) {


            case Constant.HOMEWORK_BARRIER_FRAGMENT:
                callFragment(new Fragment_HomeworkBarriers(), Constant.HOMEWORK_BARRIER_FRAGMENT, null, null);
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

            if (Constant.HOMEWORK_BARRIER_FRAGMENT.equals(tag)){
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
