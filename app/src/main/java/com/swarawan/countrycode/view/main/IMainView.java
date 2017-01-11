package com.swarawan.countrycode.view.main;

import com.swarawan.countrycode.base.MvpView;
import com.swarawan.countrycode.model.CountryModel;

import java.util.List;

/**
 * Created by rioswarawan on 1/11/17.
 */

public interface IMainView extends MvpView {

    void onCountryLoaded(List<CountryModel> list);
}
