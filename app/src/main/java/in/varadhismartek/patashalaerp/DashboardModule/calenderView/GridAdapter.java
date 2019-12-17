package in.varadhismartek.patashalaerp.DashboardModule.calenderView;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.varadhismartek.patashalaerp.R;


public class GridAdapter extends ArrayAdapter {

    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
 //   private List<ScheduleModel> allEvents;
    Context context;

    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate/*, List<ScheduleModel> allEvents*/) {
        super(context, R.layout.single_cell_layout);
        this.context = context;
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
       // this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;

        if(view == null){
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }

        TextView cellNumber = view.findViewById(R.id.calendar_date_id);
        RelativeLayout rl_calendar_circle = view.findViewById(R.id.rl_calendar_circle);

        if(displayMonth == currentMonth && displayYear == currentYear){

            view.setBackgroundColor(Color.parseColor("#FFFFFF"));

            int clone_year  = dateCal.get(Calendar.YEAR);
            int clone_month = dateCal.get(Calendar.MONTH)+1;
            int clone_day   = dateCal.get(Calendar.DAY_OF_MONTH);
            int day_name = dateCal.get(Calendar.DAY_OF_WEEK);

            Calendar cal = Calendar.getInstance();

            int current_year  = cal.get(Calendar.YEAR);
            int current_month = cal.get(Calendar.MONTH)+1;
            int current_day   = cal.get(Calendar.DAY_OF_MONTH);

            int currentDay =cal.get(Calendar.DAY_OF_MONTH);

            cellNumber.setText(String.valueOf(dayValue));
            if (cellNumber.getText().toString().equals(String.valueOf(currentDay))){
                cellNumber.setTextColor(Color.GREEN);
            }
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String value = String.valueOf(monthlyDates.get(position));
                   Toast.makeText(context,value, Toast.LENGTH_SHORT).show();
               }
           });

          /*  if(current_year == clone_year && current_month == clone_month && current_day == clone_day){
                rl_calendar_circle.setBackgroundResource(R.drawable.circle_solid_blue);
                cellNumber.setTextColor(Color.WHITE);

            }

            if(day_name == 1){
                cellNumber.setTextColor(Color.RED);

            }

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if(day_name != 1){

                        Log.d("VALIDATION_YEAR",clone_year+" "+clone_month+" "+clone_day);
                        Log.d("VALIDATION_YEAR",current_year+" "+current_month+" "+current_day);

                        if(clone_year > current_year){
                            Log.d("VALIDATION_YEAR",clone_year+" "+current_year);

                            ScheduleActivity scheduleActivity = (ScheduleActivity) context;
                            Bundle bundle = new Bundle();
                            bundle.putInt("year",clone_year);
                            bundle.putInt("month",clone_month);
                            bundle.putInt("day",clone_day);
                            scheduleActivity.loadFragment(Constant.ADD_SCHEDULE_FRAGMENT, bundle);

                        }else if(clone_year == current_year){
                            Log.d("VALIDATION_YEAR",clone_year+" "+current_year);

                            if (clone_month > current_month){

                                ScheduleActivity scheduleActivity = (ScheduleActivity) context;
                                Bundle bundle = new Bundle();
                                bundle.putInt("year",clone_year);
                                bundle.putInt("month",clone_month);
                                bundle.putInt("day",clone_day);
                                scheduleActivity.loadFragment(Constant.ADD_SCHEDULE_FRAGMENT, bundle);

                            } else if(clone_month == current_month){

                                if (clone_day >= current_day){

                                    ScheduleActivity scheduleActivity = (ScheduleActivity) context;
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("year",clone_year);
                                    bundle.putInt("month",clone_month);
                                    bundle.putInt("day",clone_day);
                                    scheduleActivity.loadFragment(Constant.ADD_SCHEDULE_FRAGMENT, bundle);

                                }else {
                                    Toast.makeText(context, "Please Select Valid Date", Toast.LENGTH_SHORT).show();
                                    Log.d("VALIDATION_YEAR","Please Select Valid Date");

                                }

                            }else {
                                Toast.makeText(context, "Please Select Valid Month", Toast.LENGTH_SHORT).show();
                                Log.d("VALIDATION_YEAR","Please Select Valid MONTH");
                            }

                        }else {
                            Toast.makeText(context, "Please Select Valid Year", Toast.LENGTH_SHORT).show();
                            Log.d("VALIDATION_YEAR","Please Select Valid Year");
                            Log.d("VALIDATION_YEAR",clone_year+" "+current_year);

                        }

                    }else {

                        Toast.makeText(context, "Sunday Is There", Toast.LENGTH_SHORT).show();
                    }
                }
            });
*/
        }else{
            view.setBackgroundColor(Color.parseColor("#F5F5F5"));
            view.setVisibility(View.GONE);
        }
        //Add day to calendar
        cellNumber.setText(String.valueOf(dayValue));
        //Add events to the calendar
        TextView eventIndicator1 = view.findViewById(R.id.event_id_1);
        TextView eventIndicator2 = view.findViewById(R.id.event_id_2);
        Calendar eventCalendar = Calendar.getInstance();


       /* for(int i = 0; i < allEvents.size(); i++) {
            Log.d("SIZE_OF_EVENT", String.valueOf(allEvents.size()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = null;
            Date date2 = null;

            try {
                date1 = dateFormat.parse(allEvents.get(i).getFromDate());
                date2 = dateFormat.parse(allEvents.get(i).getToDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Add logic for multiple indicator from date - to date
            List<Date> datesRange = getDateRange(date1, date2);

            Log.d("DATE_RANGER", date1+" "+date2);
            Log.d("DATE_RANGER", String.valueOf(datesRange.size()));

            for (int j = 0; j < datesRange.size(); j++){

                eventCalendar.setTime(datesRange.get(j));
                Log.d("ABDCHJGFH", allEvents.get(i).getToDate());

                if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                        && displayYear == eventCalendar.get(Calendar.YEAR)){

                    //Multiple Event Indicator Logic

                    eventIndicator1.setBackgroundResource(R.drawable.summer_winter);
                    eventIndicator2.setBackgroundResource(R.drawable.exams);
                }
            }

        }*/

        return view;

    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition( Object item) {
        return monthlyDates.indexOf(item);
    }


    public List<Date> getDateRange(Date startDate, Date endDate){

        List<Date> datesInRange = new ArrayList<>();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (startCalendar.before(endCalendar)){



            Date result = startCalendar.getTime();

            int dayName = startCalendar.get(Calendar.DAY_OF_WEEK);


            if (dayName == 1){

            }else if (dayName == 7){

            }else {
                datesInRange.add(result);
                Log.d("ajfbjalsbl", String.valueOf(dayName));
            }

            startCalendar.add(Calendar.DATE, 1);


        }

        datesInRange.add(endDate);

        return datesInRange;
    }
}
