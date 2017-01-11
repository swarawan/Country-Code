package com.swarawan.countrycode.view.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.resepkita.utils.ViewUtils;
import com.swarawan.countrycode.R;
import com.swarawan.countrycode.model.CountryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by rioswarawan on 7/13/16.
 */
public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CountryModel> data;
    private ArrayList<CountryModel> arraylist;
    private LayoutInflater inflater;

    public CountryAdapter(Context context, List<CountryModel> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_country, parent, false);
        return new BodyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CountryModel model = data.get(position);
        BodyViewHolder body = (BodyViewHolder) holder;
        body.populate(model);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class BodyViewHolder extends RecyclerView.ViewHolder {

        View view;

        BodyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        void populate(final CountryModel model) {
            ViewUtils.setTextView(view, R.id.country_name, model.name);
            ViewUtils.setTextView(view, R.id.country_alpha2, context.getString(R.string.item_alpha2, model.alpha2));
            ViewUtils.setTextView(view, R.id.country_alpha3, context.getString(R.string.item_alpha3, model.alpha3));
            ViewUtils.setTextView(view, R.id.country_region, context.getString(R.string.item_region, model.region));
            ViewUtils.setTextView(view, R.id.country_subregion, context.getString(R.string.item_sub_region, model.subRegion));
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(arraylist);
        } else {
            for (CountryModel country : arraylist) {
                if (country.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(country);
                }
            }
        }
        notifyDataSetChanged();
    }
}