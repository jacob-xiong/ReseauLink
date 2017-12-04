package com.reseau.link.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.reseau.link.R;
import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;
import com.reseau.link.mvp.presenter.TestPresenter;
import com.reseau.link.mvp.view.TestView;
import com.reseau.link.utils.Constant;
import com.reseau.link.utils.FileUtils;
import com.reseau.link.utils.ImgUtils;
import com.reseau.link.utils.LoadImageUtil;
import com.reseau.link.utils.MyToast;
import com.reseau.link.widget.SelectedDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.reseau.link.utils.Constant.REQUEST_PICTURE;

/**
 * @author xiongyan
 * @date 2017/11/20
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestView, View.OnClickListener {
    @BindView(R.id.test_img)
    ImageView demoImg;
    private String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511157212722&di=47ce8ac4cf2baf6f1d78df7fe6ab73c0&imgtype=0&src=http%3A%2F%2Fimg.12365auto.com%2Fa%2F201705%2F22%2F201705221026016663_sst.jpg";
    @BindView(R.id.show_selected_dialog)
    Button mShowSelectedDialog;
    @BindView(R.id.selected_path)
    TextView mSelectedPath;
    @BindView(R.id.selected_img_view)
    LinearLayout mSelectedImgView;
    ArrayList<ImageItem> selectedItemArrayList = null;
    ArrayList<ImageItem> images = null;
    private String[] array;
    private SelectedDialogFragment selectedDialogFragment;

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
        array = getResources().getStringArray(R.array.selected_media_array);
        selectedDialogFragment = new SelectedDialogFragment();
        selectedItemArrayList = new ArrayList<>();

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

    @OnClick({R.id.show_selected_dialog,})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_selected_dialog:
                showSelectedDialog();
                break;

            default:
                super.onClick(v);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PICTURE:
                if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    Map sizeMap;
                    int size = mSelectedImgView.getWidth() / 3;
                    if (images != null) {
                        selectedItemArrayList.addAll(images);
                    }
                    for (ImageItem item : selectedItemArrayList) {
                        sizeMap = ImgUtils.getImgSize(item.path);
                        ImageView imageView = new ImageView(this);
                        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size);
                        imageView.setLayoutParams(params);
                        imageView.setBackgroundColor(Color.parseColor("#00000000"));
                        imagePicker.getImageLoader().displayImage(this, item.path, imageView, size, size);
                        mSelectedImgView.addView(imageView);
                    }
                    setSelectedPathStr("");

                } else {
                    MyToast.showShort(this, "没有数据");
                }
                break;
            case Constant.REQUEST_FILE_SELECT_CODE:
                if (data != null && resultCode == RESULT_OK) {
                    List<String> list = data.getStringArrayListExtra("paths");
                    MyToast.showShort(this, "选中了" + list.size() + "个文件");
                    String str = "";
                    for (String path : list) {
                        str = path + "\n";
                    }
                    setSelectedPathStr(str);
                } else {
                    MyToast.showShort(this, "没有数据");
                }
                break;
            case Constant.REQUEST_AUDIO:
                if (data != null && resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String filePath = FileUtils.getPath(this, uri);
                    System.out.println("----------------------" + filePath);
                    System.out.println("----------------------" + uri);
                    setSelectedPathStr(filePath);
                } else {
                    MyToast.showShort(this, "没有数据");
                }
                break;
            default:
                break;
        }

    }


    /**
     * 打开选择对话框
     */
    private void showSelectedDialog() {
        selectedDialogFragment.setSelecterArray(array);
        selectedDialogFragment.show(getSupportFragmentManager(), "selected_fragment");
        selectedDialogFragment.setOnDialogItemClickListener(new SelectedDialogFragment.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position) {
                actionSelectedMediaPosition(position);
            }
        });
    }

    /**
     * 0、拍照
     * 1、相册
     * 2、录音
     * 3、选择文件
     */

    private void actionSelectedMediaPosition(int position) {
        switch (position) {
            case 0:
                openCamera();
                break;
            case 1:
                openPhotoAlbum();
                break;
            case 2:
                openRecord();
                break;
            case 3:
                openFileManagement();
                break;
            default:
                break;
        }
    }

    private void openFileManagement() {
        new LFilePicker()
                .withActivity(this)
                .withIconStyle(Constant.ICON_STYLE_YELLOW)
                .withRequestCode(Constant.REQUEST_FILE_SELECT_CODE)
                .withBackIcon(Constant.BACKICON_STYLETHREE)
                .withMutilyMode(true)
                .withFileFilter(new String[]{".txt", ".png"})
                .start();
    }

    /**
     * 打开录音
     */
    private void openRecord() {
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, Constant.REQUEST_AUDIO);
    }

    /**
     * 打开相册
     */
    private void openPhotoAlbum() {
        imagePicker.setMultiMode(true);
        Intent intent = new Intent(TestActivity.this, ImageGridActivity.class);
        if (images != null) {
            intent.putExtra(Constant.EXTRAS_IMAGES, images);
        }
        startActivityForResult(intent, REQUEST_PICTURE);
    }

    /***
     * 打开相机仅能选择拍摄相片
     */
    private void openCamera() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_PICTURE);
    }

    private void setSelectedPathStr(String path) {
        String str = "还未选择文件";
        if (TextUtils.isEmpty(path)) {
            for (ImageItem item : selectedItemArrayList) {
                str = item.path + "\n";
            }
        } else {
            str = path;
        }
        mSelectedPath.setText(str);
    }
}