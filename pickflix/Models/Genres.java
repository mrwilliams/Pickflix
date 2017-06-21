package mobi.kewi.pickflix.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.List;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class Genres {

    @SerializedName("genres")
    @Expose
    @ParcelPropertyConverter(Genre.class)
    public List<Genre> genres = null;

    @ParcelPropertyConverter(Genre.class)
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}