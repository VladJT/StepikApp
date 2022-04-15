package jt.projects.stepikapp.weatherapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jt.projects.stepikapp.R;
import jt.projects.stepikapp.common.GetDataFromInternet;

public class WeatherMainActivity extends AppCompatActivity implements GetDataFromInternet.AsyncResponce, View.OnClickListener {

    private static final String TAG = "WeatherMainActivity";
    private Button buttonSearch;
    private EditText editTextCityName;
    private TextView textviewCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);
        editTextCityName = findViewById(R.id.editTextCityName);
        textviewCityName = findViewById(R.id.textCity);

    }

    @Override
    public void processFinish(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weather = jsonObject.getJSONObject("main");
            JSONObject sys = jsonObject.getJSONObject("sys");

            TextView temp = findViewById(R.id.textTemperature);
            String tempInKalvin = weather.getString("temp");
            float tempC = Float.valueOf(tempInKalvin) - 273.15f;
            temp.setText(tempC + "");

            TextView preasure = findViewById(R.id.textPreasure);
            preasure.setText(weather.getString("pressure"));

            TextView sunrise = findViewById(R.id.textTimeSunrise);
            Locale m = new Locale("ru", "RU");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", m);
            String dateSunRise = formatter.format(new Date(Long.parseLong(sys.getString("sunrise")) * 1000 + (60 * 60 * 1000) * 3));
            sunrise.setText(dateSunRise);

            TextView sunset = findViewById(R.id.textTimeSunset);
            String dateSunset = formatter.format(new Date(Long.parseLong(sys.getString("sunset")) * 1000 + (60 * 60 * 1000) * 3));
            sunset.setText(dateSunset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "processFinish: " + response);
    }

    @Override
    public void onClick(View v) {
        URL url = buildUrl(editTextCityName.getText().toString());
        textviewCityName.setText(editTextCityName.getText().toString());
        new GetDataFromInternet(this).execute(url);
    }

    private URL buildUrl(String city) {
        String Base = "https://api.openweathermap.org/data/2.5/weather";
        String PARAM_CITY = "q";
        String PARAM_APP_ID = "appid";
        String appidValue = "2e7129709316d930ca03fb6be2f03ed3";
        Uri myUri = Uri.parse(Base).buildUpon().appendQueryParameter(PARAM_CITY, city).appendQueryParameter(PARAM_APP_ID, appidValue).build();
        URL myUrl = null;
        try {
            myUrl = new URL(myUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return myUrl;
    }
}
