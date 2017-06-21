package mobi.kewi.pickflix.Models;

import android.content.ClipData.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class TvResult extends ArrayListParcelConverter<TvResult>  {

    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = new ArrayList<String>();

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public void itemToParcel(TvResult input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public TvResult itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }

}