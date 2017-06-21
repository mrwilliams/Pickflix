package mobi.kewi.pickflix.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class TvShows {

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
    public List<TvResult> results = new ArrayList<TvResult>();

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

    public List<TvResult> getTvResults() {
        return results;
    }

    public void setTvResults(List<TvResult> results) {
        this.results = results;
    }

}