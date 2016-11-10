package com.maxim.suhockii.testapp.catalog;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by hzkto on 11/9/2016.
 */

@Root(name = "param",strict = false)
public class Param{
    @Attribute(name = "name")
    public String mName;

    @Text
    public String mValue;

    public Param(){

    }
}
