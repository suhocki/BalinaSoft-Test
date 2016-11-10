package com.maxim.suhockii.testapp.catalog;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by hzkto on 11/9/2016.
 */

@Root(name = "offers")
public class Offers{

    @ElementList(inline=true)
    public List<Offer> mOfferList;

    public Offers(){

    }
}
