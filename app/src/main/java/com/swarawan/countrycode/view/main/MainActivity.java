package com.swarawan.countrycode.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.resepkita.utils.Convert;
import com.resepkita.utils.ViewUtils;
import com.swarawan.countrycode.R;
import com.swarawan.countrycode.model.CountryModel;
import com.swarawan.countrycode.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by rioswarawan on 1/11/17.
 */

public class MainActivity extends AppCompatActivity implements IMainView {

    private List<CountryModel> data;
    private CountryAdapter countryAdapter;
    private MainPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        presenter.generateCountries();
    }

    private void setAdapter() {
        countryAdapter = new CountryAdapter(this, data);
        RecyclerView countryList = Convert.as(RecyclerView.class, findViewById(R.id.country_list));
        countryList.setAdapter(countryAdapter);
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setHasFixedSize(true);

    }

    private void initialize() {
        data = new ArrayList<>();
        presenter = new MainPresenter(this);
        presenter.attachView(this);
        dialog = new ProgressDialog(this);

        EditText searchView = Convert.as(EditText.class, findViewById(R.id.search_view));
        searchView.addTextChangedListener(searchWatcher);
    }

    private TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String text = charSequence.toString().toLowerCase(Locale.getDefault());
            countryAdapter.filter(text);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onCountryLoaded(List<CountryModel> list) {
        data.clear();
        data.addAll(list);
        setAdapter();
    }

    @Override
    public void showLoading() {
        if (dialog != null)
            dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog != null)
            dialog.dismiss();
    }
}
