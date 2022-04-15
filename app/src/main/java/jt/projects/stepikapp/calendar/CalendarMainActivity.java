package jt.projects.stepikapp.calendar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import jt.projects.stepikapp.R;
import jt.projects.stepikapp.common.GetDataFromInternet;

public class CalendarMainActivity extends AppCompatActivity implements GetDataFromInternet.AsyncResponce {

    private static final String TAG = "CALENDAR";
    private TextView textViewHolidays;
    private ListView list_view_holidays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_rec_view);
        textViewHolidays = findViewById(R.id.text_view_holidays_h);
        list_view_holidays = findViewById(R.id.list_view_holidays);

        try {
            URL url = new URL("https://calendarific.com/api/v2/holidays?api_key=312b82ac9b4a3a90002c36fa8d19aa1350f03460&country=RU&year=2022");
            new GetDataFromInternet(this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processFinish(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject responseJson = jsonObject.getJSONObject("response");
            JSONArray arrHolidays = responseJson.getJSONArray("holidays");

            ArrayList<String> holidays = new ArrayList<>();
            for (int i = 0; i < arrHolidays.length(); i++) {
                JSONObject h = arrHolidays.getJSONObject(i);
                String s = h.getString("name");
                holidays.add(s);
                Log.d(TAG, "processFinish: " + s);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, holidays);
            list_view_holidays.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
