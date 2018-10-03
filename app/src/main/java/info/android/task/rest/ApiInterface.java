package info.android.task.rest;

import info.android.task.model.PeopleList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("people/")
    Call<PeopleList> getTopRatedMovies();

    @GET("movie/{id}")
    Call<PeopleList> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
