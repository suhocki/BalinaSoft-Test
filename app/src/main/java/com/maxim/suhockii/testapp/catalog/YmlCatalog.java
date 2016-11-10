package com.maxim.suhockii.testapp.catalog;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hzkto on 11/9/2016.
 */

@Root(name = "yml_catalog")
public class YmlCatalog {

    @Attribute(name = "date")
    public String date;

    @Element(name = "shop")
    public Shop shop;

    public YmlCatalog(){

    }

}


