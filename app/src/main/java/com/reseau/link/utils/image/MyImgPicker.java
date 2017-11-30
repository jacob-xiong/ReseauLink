package com.reseau.link.utils.image;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.reseau.link.utils.Constant;
import com.reseau.link.utils.image.loader.PicassoImageLoader;

/**
 * @author by xiongyan on 2017/11/28.
 */

public class MyImgPicker {
    public static ImagePicker imagePicker;

    public static ImagePicker getImagePicker() {
        if (imagePicker == null) {
            imagePicker = ImagePicker.getInstance();
            setImgeePickerConfig();
        }
        return imagePicker;
    }

    /**
     * 1、设置图片加载器
     * 2、显示拍照按钮
     * 3、允许裁剪（单选才有效）
     * 4、是否按矩形区域保存
     * 5、选中数量限制
     * 6、裁剪框的形状
     * 7、裁剪框的宽度。单位像素（圆形自动取宽高最小值）
     * 8、裁剪框的高度。单位像素（圆形自动取宽高最小值）
     * 9、保存文件的宽度。单位像素
     * 10、保存文件的高度。单位像素
     *
     */

    private static  void setImgeePickerConfig(){
        /**
         * 设置图片加载器
         */
        imagePicker.setImageLoader(new PicassoImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(false);
        imagePicker.setMultiMode(false);
        imagePicker.setSelectLimit(Constant.MAX_COUNT);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);
    }

}
