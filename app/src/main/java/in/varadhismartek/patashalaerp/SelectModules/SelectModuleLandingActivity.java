package in.varadhismartek.patashalaerp.SelectModules;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import in.varadhismartek.Utils.Themes;
import in.varadhismartek.patashalaerp.R;

public class SelectModuleLandingActivity extends AppCompatActivity {

    final String themeGreen = "THEME_GREEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themes.onActivityCreateSetTheme(this,themeGreen);
        setContentView(R.layout.activity_select_module_landing);
        //This method is to detect screen size and set orientation
        DisplayMetrics metrics = new DisplayMetrics();
        SelectModuleLandingActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            // 6.5inch device or bigger set orientation to Landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            // smaller device set orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.selectModuleContainer, new ModuleListFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
