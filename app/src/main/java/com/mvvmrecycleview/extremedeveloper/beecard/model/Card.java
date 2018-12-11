package com.mvvmrecycleview.extremedeveloper.beecard.model;

//import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by מחשב שלי on 05/12/2018.
 */
//@IgnoreExtraProperties
public class Card {
    public String Name;
    public String Last_Name;
    public String Company;
    public String Phone;

    public Card()
    {
        Name = "";
        Company = "IDF";
        Phone = "";
        Last_Name = "";
    }

    public String getFullName()
    {
        return Name+" " + Last_Name;
    }

    public boolean haveName()
    {
        if(Name.length()<=0)
            return false;
        return true;
    }

    public boolean haveLastName()
    {
        if(Last_Name.length()<=0)
            return false;
        return true;
    }

    public boolean haveCompany()
    {
        if(Company.length()<=0)
            return false;
        return true;
    }

    public boolean havePhone()
    {
        if(Phone==null||Phone.length()<=0)
            return false;
        return true;
    }
}
