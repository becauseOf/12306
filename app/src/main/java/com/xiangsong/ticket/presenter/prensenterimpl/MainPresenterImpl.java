package com.xiangsong.ticket.presenter.prensenterimpl;

import android.view.View;

import com.xiangsong.ticket.presenter.ipresenter.IMainPresenter;
import com.xiangsong.ticket.view.iview.IMainView;

/**
 * Created by xiangsong on 2016/9/9.
 */
public class MainPresenterImpl implements IMainPresenter{

    IMainView iMainView;

    public MainPresenterImpl(IMainView iMainView) {
        super();
        this.iMainView = iMainView;
    }


    @Override
    public void changeColor(View v) {
        iMainView.onChangeColor(v);
    }

    @Override
    public void changePage() {
        iMainView.onChangePage();
    }
}
