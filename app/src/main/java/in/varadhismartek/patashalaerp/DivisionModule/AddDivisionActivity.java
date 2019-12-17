package in.varadhismartek.patashalaerp.DivisionModule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.CheckerMaker.MakerCheckerFragment;
import in.varadhismartek.patashalaerp.ClassAndSection.AddClassesFragment;
import in.varadhismartek.patashalaerp.ClassAndSection.AddSectionFragment;
import in.varadhismartek.patashalaerp.R;
import in.varadhismartek.patashalaerp.SessionModule.AddSessionFragment;

public class AddDivisionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_container);
        loadFragment(Constant.MAKER_CHECKER_FRAGMENT, null);
        //loadFragment(Constant.ADD_SECTION_FRAGMENT, null);

    }


    public void loadFragment(String fragmentString, Bundle bundle) {

        switch (fragmentString) {

            case Constant.MAKER_CHECKER_FRAGMENT:
                callFragment(new MakerCheckerFragment(), Constant.MAKER_CHECKER_FRAGMENT, null, null);
                break;

            case Constant.ADD_DIVISION_FRAGMENT:
                callFragment(new AddDivisionFragment(), Constant.ADD_DIVISION_FRAGMENT, null, null);
                break;
            case Constant.ADD_CLASSES_FRAGMENT:
                callFragment(new AddClassesFragment(), Constant.ADD_CLASSES_FRAGMENT, null, null);
                break;
            case Constant.ADD_SECTION_FRAGMENT:
                callFragment(new AddSectionFragment(), Constant.ADD_SECTION_FRAGMENT, null, null);
                break;
            case Constant.ADD_SESSION_FRAGMENT:
                callFragment(new AddSessionFragment(), Constant.ADD_SESSION_FRAGMENT, null, null);
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
            fragment.getArguments();

        } else {

            if (Constant.ADD_DIVISION_FRAGMENT.equals(tag)) {
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