package com.reseau.link.activity;

import android.view.View;
import android.widget.ImageView;

import com.reseau.link.mvp.presenter.TestPresenter;

import butterknife.BindView;
import com.reseau.link.R;
import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;
import com.reseau.link.mvp.view.TestView;
import com.reseau.link.utils.LoadImageUtil;

/**
 * @author xiongyan
 * @date 2017/11/20
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestView {
    @BindView(R.id.test_img)
    ImageView demoImg;
    private String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511157212722&di=47ce8ac4cf2baf6f1d78df7fe6ab73c0&imgtype=0&src=http%3A%2F%2Fimg.12365auto.com%2Fa%2F201705%2F22%2F201705221026016663_sst.jpg";

    @Override
    protected int setLayoutId() {
        return R.layout.test;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        demoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.testLogin(1);
            }
        });
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initPresenter() {
        presenter = new TestPresenter(this, this);
    }

    @Override
    protected void onResume() {
        LoadImageUtil.load(this, URL, demoImg);
        super.onResume();
    }

    @Override
    public void loginSuccess(TestData data) {
        System.out.println("----------------------" + data.getName());
        System.out.println("----------------------" + data.getPassWord());
        System.out.println("----------------------" + data.getResult());
    }

    @Override
    public void loginFail(String msg) {
        System.out.println("----------------------" + msg);
    }

    @Override
    public void loadFailure(Throwable throwable) {

    }

    @Override
    public void onException(ResultData result) {

    }
}
