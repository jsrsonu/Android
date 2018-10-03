package info.android.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.android.task.activity.MainActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView height;
    private TextView mass;
    private TextView datetime;

    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Page");

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent data=getIntent();

        name = (TextView) findViewById(R.id.name);
        name.setText(data.getExtras().getString("Name"));

        height = (TextView) findViewById(R.id.height);
        height.setText(data.getExtras().getString("Height")+" cm");

        mass = (TextView) findViewById(R.id.mass);
        mass.setText(data.getExtras().getString("Mass")+" Kg");

        datetime = (TextView) findViewById(R.id.datetime);
        datetime.setText(data.getExtras().getString("Datetime"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
