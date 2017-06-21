package mobi.kewi.pickflix.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class Movies {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("results")
    @Expose
    public List<MovieResult> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieResult> getMovies() {
        return results;
    }

    public void setResults(List<MovieResult> results) {
        this.results = results;
    }

}