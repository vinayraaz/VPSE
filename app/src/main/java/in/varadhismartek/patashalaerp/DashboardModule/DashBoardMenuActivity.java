package in.varadhismartek.patashalaerp.DashboardModule;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import in.varadhismartek.Utils.Constant;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AddExamination;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AddHouses;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AddSport;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AddSubject;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_AssessmentGrade;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_Curricular;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_Exam_Barrier;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_QuestionBank;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_QuestionBankList;
import in.varadhismartek.patashalaerp.DashboardModule.Assessment.Fragment_SportBarrier;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.Fragment_HomeworkCreate;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.Fragment_HomeworkInbox;
import in.varadhismartek.patashalaerp.DashboardModule.Homework.HomeworkViewFragment;
import in.varadhismartek.patashalaerp.R;

public class DashBoardMenuActivity extends AppCompatActivity {
    String fragmentType ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.frame_container);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
            fragmentType = bundle.getString("Fragment_Type");
        }

        Log.i("fragmentType**",""+fragmentType);

        switch (fragmentType){


            case "Subject":
                loadFragment(Constant.ADD_SUBJECT, null);
                break;

            case "HomeWorkInbox":
                loadFragment(Constant.HOMEWORK_INBOX_FRAGMENT, null);



        }
    }

    public void loadFragment(String fragmentString, Bundle bundle) {
        switch (fragmentString) {

            case Constant.ADD_SUBJECT:
                callFragment(new Fragment_AddSubject(), Constant.ADD_SUBJECT, null, null);
                break;

            case Constant.HOMEWORK_INBOX_FRAGMENT:
                callFragment(new Fragment_HomeworkInbox(), Constant.HOMEWORK_INBOX_FRAGMENT, null, null);
                break;

            case Constant.HOMEWORK_CREATE_FRAGMENT:
                callFragment(new Fragment_HomeworkCreate(), Constant.HOMEWORK_CREATE_FRAGMENT, null, null);
                break;

            case Constant.HOMEWORK_VIEW_FRAGMENT:
                callFragment(new HomeworkViewFragment(), Constant.HOMEWORK_VIEW_FRAGMENT, null, bundle);
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

        } else {

            if (Constant.ADD_SUBJECT.equals(tag)) {
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
        Intent intent= new Intent(DashBoardMenuActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
       // finish();
    }
}
