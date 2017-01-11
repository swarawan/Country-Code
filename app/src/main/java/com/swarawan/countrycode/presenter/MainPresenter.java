package com.swarawan.countrycode.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.swarawan.countrycode.base.BasePresenter;
import com.swarawan.countrycode.model.CountryModel;
import com.swarawan.countrycode.view.main.IMainView;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by rioswarawan on 1/11/17.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    private static final String TAG = "MainPresenter";

    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(IMainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void generateCountries() {
        getMvpView().showLoading();

        String countryJson = parseJsonFromAssets();

        CountryModel[] countryArray = new Gson().fromJson(countryJson, CountryModel[].class);
        List<CountryModel> countries = Arrays.asList(countryArray);
        getMvpView().onCountryLoaded(countries);

        getMvpView().hideLoading();
    }

    private String parseJsonFromAssets() {
        String json = "";
        try {
            InputStream is = context.getAssets().open("all.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Timber.e(e, TAG);
            e.printStackTrace();
        }
        return json;
    }
}
