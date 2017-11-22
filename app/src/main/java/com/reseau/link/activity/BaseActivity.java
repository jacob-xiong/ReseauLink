package com.reseau.link.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.reseau.link.R;
import com.reseau.link.utils.MyToast;

import butterknife.ButterKnife;
import com.reseau.link.mvp.presenter.BasePresenter;

/**
 * @author xiongyan
 * @date 2017/11/20
 * 基类Activity
 */

public abstract class BaseActivity <T extends BasePresenter>extends Activity implements View.OnClickListener, Handler.Callback {
    protected LayoutInflater mInflater;
    protected Context mContext;
    private View mActionView;
    private ImageView mActionBarBack;
    private TextView mActionBarTitle;
    private ImageView mActionBarRightImg;
    private TextView mActionBarRightTv;
    public Handler handler = new Handler(this);
    private String DRAWABLE_TYPE = "drawable";
    private String STRING_TYPE = "string";
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 去掉标题栏
         */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mInflater = LayoutInflater.from(this);
        mContext = getApplicationContext();
        setContentView(R.layout.base_activity);
        initIntent();
        initLayout();
        ButterKnife.bind(this);
        initPresenter();
        initView();
        initActionBarTitle();
        loadData();
    }

    /**
     * 提供给之类中初始化对应的presenter
     */
    protected abstract void initPresenter();

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract int setLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

    protected abstract void initIntent();

    private void initActionBarTitle() {
        if (isShowActionBarTitle()) {
            initTitleView();
            mActionView.setVisibility(View.VISIBLE);
            mActionBarTitle.setText(getActionTitle());
            if (getRightBtnRes() != -1) {
                String sourceType = this.getResources().getResourceTypeName(getRightBtnRes());
                if (sourceType.contains(DRAWABLE_TYPE)) {
                    mActionBarRightImg.setVisibility(View.VISIBLE);
                    mActionBarRightImg.setImageResource(getRightBtnRes());
                } else if (sourceType.contains(STRING_TYPE)) {
                    mActionBarRightTv.setVisibility(View.VISIBLE);
                    mActionBarRightTv.setText(getRightBtnRes());
                }
            }
            mActionBarRightImg.setOnClickListener(this);
            mActionBarBack.setOnClickListener(this);
        }
    }


    protected boolean isShowActionBarTitle() {
        return false;
    }


    protected int getActionTitle() {
        return -1;
    }

    private void initTitleView() {
        mActionView = findViewById(R.id.action_bar_view);
        mActionBarBack = (ImageView) findViewById(R.id.action_back);
        mActionBarTitle = (TextView) findViewById(R.id.action_title);
        mActionBarRightImg = (ImageView) findViewById(R.id.action_right_img);
        mActionBarRightTv = (TextView) findViewById(R.id.action_right_tv);
    }

    protected int getRightBtnRes() {
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                if (isShowSaveTipWhenExit()) {
                    MyToast.showShort(this, "测试");
                } else {
                    this.finish();
                }
                break;
            case R.id.action_right_tv:
                break;
            case R.id.action_right_img:
                break;
        }
    }

    protected boolean isShowSaveTipWhenExit() {
        return false;
    }

    private void initLayout() {
        ViewStub viewStub = (ViewStub) this.findViewById(R.id.content_view);
        viewStub.setLayoutResource(setLayoutId());
        viewStub.inflate();
    }

    @Override
    public final boolean handleMessage(Message msg) {
        dealWithMessage(msg);
        return true;
    }

    protected void dealWithMessage(Message msg) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.release();
            presenter = null;
        }
    }
}
