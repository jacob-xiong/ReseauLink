package com.reseau.link.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.reseau.link.R;
import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;
import com.reseau.link.mvp.presenter.TestPresenter;
import com.reseau.link.mvp.view.TestView;
import com.reseau.link.utils.ImgUtils;
import com.reseau.link.utils.LoadImageUtil;
import com.reseau.link.utils.MyLog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xiongyan
 * @date 2017/11/20
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestView, View.OnClickListener {
    @BindView(R.id.test_img)
    ImageView demoImg;
    private String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511157212722&di=47ce8ac4cf2baf6f1d78df7fe6ab73c0&imgtype=0&src=http%3A%2F%2Fimg.12365auto.com%2Fa%2F201705%2F22%2F201705221026016663_sst.jpg";
    @BindView(R.id.luyin)
    Button luyin;
    @BindView(R.id.zhaoxiang)
    Button zhaoxiang;
    /**
     * 系统相册路径
     */
    private String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;
    Uri photoUri;
    private static int REQUEST_CODE = 1024;

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
        LoadImageUtil.load(this, URL, demoImg);

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

    @OnClick({R.id.zhaoxiang, R.id.luyin, R.id.tuku})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhaoxiang:
                oPenImg();
                break;
            case R.id.luyin:
                oPenSouce();
                break;
            case R.id.tuku:
                pickImageFromAlbum2();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void oPenSouce() {
        Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivity(mi);
    }

    private void oPenImg() {
        try {
            photoUri = ImgUtils.takePhoto(this, 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void pickImageFromAlbum2() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 222);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                return;
            }
            MyLog.d("-----------------------------", "开始回调");
            Uri uri = null;
            if (data != null && data.getData() != null) {
                uri = data.getData();
            }
            if (uri == null) {
                if (photoUri != null) {
                    uri = photoUri;
                }
            }

            demoImg.setImageBitmap(ImgUtils.getImage(photoUri.getPath()));
            galleryAddPic();
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(photoUri);
        this.sendBroadcast(mediaScanIntent);
    }

}