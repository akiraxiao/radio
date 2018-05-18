package com.gcores.radionews.ui.model.news;


import java.util.List;

public class TopListList  {

    private String type;
    private Object data;

    private List<Results> arr;
    private Results bean;

    public List<Results> getArr() {
        return arr;
    }

    public void setArr(List<Results> arr) {
        this.arr = arr;
    }

    public Results getBean() {
        return bean;
    }

    public void setBean(Results bean) {
        this.bean = bean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        /*if (data instanceof List){
            data = arr;
        }else{
//            data = bean;
        }*/
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
