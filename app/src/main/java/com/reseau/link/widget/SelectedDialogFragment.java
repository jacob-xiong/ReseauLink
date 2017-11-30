package com.reseau.link.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.reseau.link.R;
import com.reseau.link.adapter.SelectedDialogAdapter;

/**
 * @author by xiongyan on 2017/11/29.
 *         选择弹窗
 */

public class SelectedDialogFragment extends DialogFragment {
    private TextView mSelectedTitleTv;
    private RecyclerView mSelectedRecyclerView;
    private Button mSelectedCancelBt;
    private String[] selectArray;
    private SelectedDialogAdapter selectedDialogAdapter;
    private OnDialogItemClickListener mOnDialogItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.selected_dialog_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    private void initView(View view) {
        mSelectedTitleTv = (TextView) view.findViewById(R.id.selected_title_tv);
        mSelectedRecyclerView = (RecyclerView) view.findViewById(R.id.selected_list);
        mSelectedCancelBt = (Button) view.findViewById(R.id.selected_cancel_bt);
        selectedDialogAdapter = new SelectedDialogAdapter(getContext(), selectArray);
        initRecyclerView();
        mSelectedCancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 设置选择弹窗数据
     *
     * @param array
     */
    public void setSelecterArray(String[] array) {
        this.selectArray = array;
    }

    /**
     * 设置弹窗Title
     *
     * @param title
     */
    public void setTitleStr(String title) {
        mSelectedTitleTv.setText(title);
        setTitleState(!TextUtils.isEmpty(title));
    }

    public void setTitleState(boolean show) {
        mSelectedTitleTv.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            setDialogBottom(window);
            setDialogWidth(window);
        }

    }

    /**
     * 设置弹窗位于Activity底部
     */
    private void setDialogBottom(Window window) {

        WindowManager.LayoutParams lp = window.getAttributes();
        // 紧贴底部
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.BottomDialogAnimation;
        window.setAttributes(lp);
    }

    /**
     * 设置弹窗宽度
     */
    private void setDialogWidth(Window window) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        window.setLayout((int) (dm.widthPixels * 0.95), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     *
     */
    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSelectedRecyclerView.setLayoutManager(mLayoutManager);
        //等高会增加效率
        mSelectedRecyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.list_divider);
        mSelectedRecyclerView.addItemDecoration(dividerItemDecoration);
        mSelectedRecyclerView.setAdapter(selectedDialogAdapter);
        selectedDialogAdapter.setOnItemClickListener(new SelectedDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                dismiss();
                mOnDialogItemClickListener.onItemClick(position);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    /**
     * 弹窗回调接口
     */
    public interface OnDialogItemClickListener {
        void onItemClick(int position);
    }

    public void setOnDialogItemClickListener(OnDialogItemClickListener onItemClickListener) {
        this.mOnDialogItemClickListener = onItemClickListener;
    }
}
