package com.moziqi.testmvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.moziqi.testmvvm.databinding.ActivityMainBinding;

import cn.ittiger.net.http.TigerOkHttp;

public class MainActivity extends FragmentActivity implements IMainView {

    private MainPresenter mainPresenter = new MainPresenter(this);
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter.testGet();
    }

    @Override
    public void doSuccess(String result) {
        activityMainBinding.setUser(new UserBean(1, result));
    }

    @Override
    public void doFailure(String error) {
        activityMainBinding.setUser(new UserBean(1, error));
    }

    @Override
    protected void onDestroy() {
        mainPresenter.cancel();
        super.onDestroy();
    }
}
