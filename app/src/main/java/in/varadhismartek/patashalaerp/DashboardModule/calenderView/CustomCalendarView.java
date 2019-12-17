package in.varadhismartek.patashalaerp.DashboardModule.calenderView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.varadhismartek.patashalaerp.R;


public class CustomCalendarView extends LinearLayout {

    private static final String TAG = CustomCalendarView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private GridView calendarGridView;
    private Button addEventButton;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private SimpleDateFormat formatter_month = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;

    private GridAdapter mAdapter;


    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        //setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout() {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_calendar_layout, this);

        previousButton   = view.findViewById(R.id.previous_month);
        nextButton       = view.findViewById(R.id.next_month);
        currentDate      = view.findViewById(R.id.display_current_date);
        addEventButton   = view.findViewById(R.id.add_calendar_event);
        calendarGridView = view.findViewById(R.id.calendar_grid);
    }

    private void setPreviousButtonClickEvent(){
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

    /*private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context, "Clicked " + position, Toast.LENGTH_LONG).show();
            }
        });
    }
*/
    private void setUpCalendarAdapter() {

        List<Date> dayValueInCells = new ArrayList<>();
        Calendar mCal = (Calendar) cal.clone();

        mCal.set(Calendar.DAY_OF_MONTH, 1);
        Log.d("Date Is---------", String.valueOf(mCal.getTime()));

        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        Log.d("Date Is---------", String.valueOf(firstDayOfTheMonth));

        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        Log.d("Date Is---------", String.valueOf(mCal.getTime()));


        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            Log.d("ARRAY_LIST_CURRENT", String.valueOf(dayValueInCells.size()+" "+mCal.getTime()));
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
            Log.d("ARRAY_LIST_INCREMENT", String.valueOf(mCal.getTime()));

        }

        /*List<ScheduleModel> mEvents = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Log.d("LIST_SIZR_ASC",)*/

        /*try {
            mEvents.add(new EventObject("Happy",dateFormat.parse("27-10-2019")));
            mEvents.add(new EventObject("Happy",dateFormat.parse("28-10-2019")));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
       // Constant.TOOLBAR_MONTH = formatter_month.format(cal.getTime());
        mAdapter = new GridAdapter(context, dayValueInCells, cal /*,Constant.ARRAY_LIST_SCHEDULE_MODEL*/);
        calendarGridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        // write logic here for see the month wise holiday

    }

}
