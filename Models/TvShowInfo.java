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
public class TvShowInfo extends ArrayListParcelConverter<TvShowInfo> {

    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("created_by")
    @ParcelPropertyConverter(CreatedBy.class)
    public List<CreatedBy> createdBy = new ArrayList<CreatedBy>();
    @SerializedName("episode_run_time")
    @Expose
    public List<Integer> episodeRunTime = new ArrayList<Integer>();
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("genres")
    @Expose
    @ParcelPropertyConverter(Genre.class)
    public List<Genre> genres = new ArrayList<Genre>();
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("in_production")
    @Expose
    public Boolean inProduction;
    @SerializedName("languages")
    @Expose
    public List<String> languages = new ArrayList<String>();
    @SerializedName("last_air_date")
    @Expose
    public String lastAirDate;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("networks")
    @Expose
    @ParcelPropertyConverter(Network.class)
    public List<Network> networks = new ArrayList<Network>();
    @SerializedName("number_of_episodes")
    @Expose
    public Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    @Expose
    public Integer numberOfSeasons;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = new ArrayList<String>();
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("production_companies")
    @Expose
    @ParcelPropertyConverter(ProductionCompany.class)
    public List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    @SerializedName("seasons")
    @Expose
    @ParcelPropertyConverter(Season.class)
    public List<Season> seasons = new ArrayList<Season>();
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("vote_average")
    @Expose
    public Integer voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Integer voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public void itemToParcel(TvShowInfo input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    public TvShowInfo itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }
}