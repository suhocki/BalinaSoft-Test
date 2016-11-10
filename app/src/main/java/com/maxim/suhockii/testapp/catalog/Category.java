package com.maxim.suhockii.testapp.catalog;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.Collection;

/**
 * Created by hzkto on 11/9/2016.
 */

@DatabaseTable()
@Root(name = "category")
public class Category {

    @DatabaseField(id = true)
    @Attribute(name = "id")
    public long id;

    @DatabaseField()
    @Text
    public String text;

    @ForeignCollectionField
    public Collection<Offer> offers;

    public Category(){
    }
}
