package com.maxim.suhockii.testapp.catalog;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by hzkto on 11/9/2016.
 */

@Root(name="categories")
public class Categories{

    @ElementList(inline=true)
    public List<Category> mCategoryForAdapterList;

    public  Categories(){

    }
}
