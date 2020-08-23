package com.agrictech.techrice.model;

import com.agrictech.techrice.model.products.ProductResponseData;

import java.util.List;

public class CategoryList {
    private String title;
    private List<ProductResponseData> data = null;

    public CategoryList(String title, List<ProductResponseData> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductResponseData> getData() {
        return data;
    }
}
