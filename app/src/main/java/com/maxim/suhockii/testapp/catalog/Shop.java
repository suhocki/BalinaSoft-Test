package com.maxim.suhockii.testapp.catalog;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hzkto on 11/9/2016.
 */

@Root(name = "shop")
public class Shop{

    @Element(name = "categories")
    public Categories mCategories;

    @Element(name = "offers")
    public Offers mOffers;

    public Shop(){

    }

}
