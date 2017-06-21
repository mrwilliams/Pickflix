package mobi.kewi.pickflix.Models;

import android.content.ClipData.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class Season extends ArrayListParcelConverter<Season> {

    @SerializedName("air_date")
    @Expose
    public String airDate;
    @SerializedName("episode_count")
    @Expose
    public Integer episodeCount;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("season_number")
    @Expose
    public Integer seasonNumber;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @Override
    public void itemToParcel(Season input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Season itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }
}