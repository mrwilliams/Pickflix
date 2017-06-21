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
 * Created by Kevin on 08/06/17.
 */
@Parcel
public class PersonInfo extends ArrayListParcelConverter<PersonInfo>  {

    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("also_known_as")
    @Expose
    public List<String> alsoKnownAs = new ArrayList<String>();
    @SerializedName("biography")
    @Expose
    public String biography;
    @SerializedName("birthday")
    @Expose
    public String birthday;
    @SerializedName("deathday")
    @Expose
    public String deathday;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("imdb_id")
    @Expose
    public String imdbId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("place_of_birth")
    @Expose
    public String placeOfBirth;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void itemToParcel(PersonInfo input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    public PersonInfo itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }
}
