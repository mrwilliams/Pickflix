package mobi.kewi.pickflix.Models;

import android.content.ClipData.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.parceler.converter.ArrayListParcelConverter;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

/**
 * Created by Kevin on 07/06/17.
 */
@Parcel
public class ProductionCountry extends ArrayListParcelConverter<ProductionCountry> {

    @SerializedName("iso_3166_1")
    @Expose
    public String iso31661;
    @SerializedName("name")
    @Expose
    public String name;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void itemToParcel(ProductionCountry input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);

    }

    @Override
    public ProductionCountry itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Item.class.getClassLoader()));
    }
}