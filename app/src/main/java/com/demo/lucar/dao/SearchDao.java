package com.demo.lucar.dao;

import com.demo.lucar.MyApplication;
import com.demo.lucar.bean.SearchBean;
import com.demo.lucar.bean.SearchBean_;

import java.util.List;

import io.objectbox.Box;

public class SearchDao {

    public static void add(SearchBean bean) {
        getBoxStore().put(bean);
    }

    public static void addMore(List<SearchBean> beans) {
        getBoxStore().put(beans);
    }

    public static List<SearchBean> getAll() {
        return getBoxStore().getAll();
    }


    public static void delete(SearchBean query) {
        getBoxStore().remove(query);
    }

    public static void deleteAll(long id) {
        getBoxStore().removeAll();
    }


    public static SearchBean query(String id) {
        return getBoxStore().query().equal(SearchBean_.id, id).build().findFirst();
    }

    private static Box<SearchBean> getBoxStore() {
        return MyApplication.getApplicationInstance().getBoxStore().boxFor(SearchBean.class);
    }


}
