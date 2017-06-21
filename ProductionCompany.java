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
public class ProductionCompany extends ArrayListParcelConverter<ProductionCompany> {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public void itemToParcel(ProductionCompany input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);

    }

    @Override
    public ProductionCompany itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }

}