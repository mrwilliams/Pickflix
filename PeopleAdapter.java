package mobi.kewi.pickflix.Views;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mobi.kewi.pickflix.Models.KnownFor;
import mobi.kewi.pickflix.Models.PersonResult;
import mobi.kewi.pickflix.R;

/**
 * Created by Kevin on 10/06/17.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    // Adapter used to create custom views with text and image for each list row


    final public String IMAGEURL = "http://image.tmdb.org/t/p/w300/";
    private String image;
    public static List<PersonResult> people = new ArrayList<>();
    private ArrayList<Object> personKnownForArray;
    public Resources res;
    Context context;
    private Collection<PersonResult> mPeople;
    private ArrayList<String> keys;


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView person_name;
        public TextView person_known_for;
        public ImageView person_image;

        public ViewHolder(View convertView) {
            super(convertView);

            person_name = (TextView) convertView.findViewById(R.id.person_name);
            person_known_for = (TextView) convertView.findViewById(R.id.person_known_for);
            person_image = (ImageView) convertView.findViewById(R.id.person_image);
        }
    }

    public PeopleAdapter(ArrayList<PersonResult> people) {
        this.people = people;
        mPeople = new ArrayList<>();
        mPeople.addAll(people);
    }

    public int getCount() {
        return people.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public Object getItems() {return mPeople;}

    public long getItemId(int position) {
        return position;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        context = holder.person_image.getContext();
        int i = 0;

        PersonResult person = people.get(position);
        holder.person_name.setText(person.getName());

        Object[] personKnownForArray = person.getKnownFor().toArray();
        Log.i("PeopleAdapter","Array size = "+(personKnownForArray).length);
        String[] personKnownFor = new String[(person.getKnownFor()).size()];
        Log.i("PeopleAdapter","PersonKnownFor size = "+person.getKnownFor().size());
        for (Object o : personKnownForArray) {
            KnownFor known = person.getKnownFor().get(i);
            personKnownFor[i] = known.getTitle();
            i++;
        }
        String knownFor = TextUtils.join(", ", personKnownFor);
        holder.person_known_for.setText(knownFor);

        Log.i("PeopleAdapter","Known for = "+knownFor);
        image = person.profilePath;
        Picasso.with(context)
                .load(IMAGEURL+image)
                .into(holder.person_image);
    }
}