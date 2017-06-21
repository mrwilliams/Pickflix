package mobi.kewi.pickflix.Networking;

import mobi.kewi.pickflix.Models.MovieInfo;
import mobi.kewi.pickflix.Models.Movies;
import mobi.kewi.pickflix.Models.People;
import mobi.kewi.pickflix.Models.PersonInfo;
import mobi.kewi.pickflix.Models.TvShowInfo;
import mobi.kewi.pickflix.Models.TvShows;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Kevin on 10/06/17.
 */

public interface NetworkEndpoints {

    //Popular movie request
    @GET("popular?page=1&language=en-US&api_key=ab5b7a43e943cb2cb68881139eda4012")
    Call<Movies> getMovies();
    // Movie details request with ID from Movies
    @GET
    Call<MovieInfo> getMovieInfo(@Url String url);

    //Popular TV shows request
    @GET("popular?page=1&language=en-US&api_key=ab5b7a43e943cb2cb68881139eda4012")
    Call<TvShows> getTvResults();
    // TV show details request. ID from TvShows required
    @GET
    Call<TvShowInfo> getTvInfo(@Url String url);

    //Popular people request
    @GET("popular?page=1&language=en-US&api_key=ab5b7a43e943cb2cb68881139eda4012")
    Call<People> getPersonResults();
    // Person details request. ID from PersonResult required
    @GET
    Call<PersonInfo> getPersonInfo(@Url String url);

}
