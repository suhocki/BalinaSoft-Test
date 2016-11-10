package com.maxim.suhockii.testapp.catalog;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by hzkto on 11/9/2016.
 */
@DatabaseTable(tableName = "offer")
@Root(name ="offer",strict = false)
public class Offer {

    @DatabaseField(id = true)
    @Attribute(name = "id")
    public long id;

    @DatabaseField()
    @Element(name = "url")
    public String mUrl;

    @DatabaseField()
    @Element(name = "name")
    public String mName;

    @DatabaseField()
    @Element(name = "price")
    public double mPrice;

    @DatabaseField()
    @Element(name = "description", required = false)
    public String mDescription;

    @DatabaseField()
    @Element(name = "picture", required = false)
    public String mPictureUrl;

    @Element(name = "categoryId")
    public long mCategoryId;

    public int mNumber = 0;

    @ElementList(entry = "param", inline = true, required = false)
    public List<Param> mParamList;

    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    public Category category;

    public Offer() {

    }
}
