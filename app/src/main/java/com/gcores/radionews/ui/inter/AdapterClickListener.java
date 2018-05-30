package com.gcores.radionews.ui.inter;

import com.gcores.radionews.ui.model.CateMenu;
import com.gcores.radionews.ui.model.User;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.model.news.Top;

public interface AdapterClickListener {

    void onNewsHeaderClick(int topId, Results results);
    void onNewsItemClick(int topId, Top results);
    void onUserClick(int topId, User user);
    void onCateClick(CateMenu cateMenu);
}
