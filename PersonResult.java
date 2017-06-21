package mobi.kewi.pickflix.Models;

import android.content.ClipData.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.parceler.ParcelPropertyConverter;
import org.parceler.converter.ArrayListParcelConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class PersonResult extends ArrayListParcelConverter<PersonResult> {

    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("known_for")
    @Expose
    @ParcelPropertyConverter(KnownFor.class)
    public List<KnownFor> knownFor = new ArrayList<KnownFor>();
    @SerializedName("adult")
    @Expose
    public Boolean adult;

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    @Override
    public void itemToParcel(PersonResult input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public PersonResult itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }
}