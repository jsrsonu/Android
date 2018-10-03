package info.android.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import info.android.task.DetailActivity;
import info.android.task.R;
import info.android.task.adapter.PeopleAdapter;
import info.android.task.model.PeopleList;
import info.android.task.model.Result;
import info.android.task.rest.ApiClient;
import info.android.task.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button buttonRefresh;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.peoples_recycler_view);
        buttonRefresh = (Button) findViewById(R.id.buttonRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CallApi();

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallApi();
            }
        });

    }

    private void CallApi() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PeopleList> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<PeopleList>() {
            @Override
            public void onResponse(Call<PeopleList> call, Response<PeopleList> response) {
                int statusCode = response.code();
                PeopleList model=response.body();
                List<Result>list=model.getResults();

                PeopleAdapter adapter=new PeopleAdapter(list, R.layout.list_item_people, getApplicationContext()){
                    public void onClickPeopleName(Result result){
                        Intent mainIntent = new Intent(MainActivity.this,DetailActivity.class);
                        mainIntent.putExtra("Name",result.getName());
                        mainIntent.putExtra("Height",result.getHeight());
                        mainIntent.putExtra("Mass",result.getMass());
                        mainIntent.putExtra("Datetime",result.getCreated());
                        startActivity(mainIntent);
                    }
                };

                recyclerView.setAdapter(adapter);

                if (list.size() <= 0){
                    buttonRefresh.setVisibility(View.VISIBLE);
                }else {
                    buttonRefresh.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<PeopleList> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
