package com.hfad.seahawktours;

import com.hfad.seahawktours.R;

public class Building {
    private int name;
    private int description;
    private int addinfo;
    private int imageResourceId;
    private int imageResourceId2;
    private int url;

    public static final Building[] buildings = {
            new Building( R.string.alderman_hall, R.string.Alderman, R.string.ald_name, R.drawable.aldermanhall, R.drawable.eaalderman, R.string.ald_url ),
            new Building(R.string.belk_hall, R.string.Belk, R.string.belk_name, R.drawable.belkhall1, R.drawable.irwinbelk, R.string.belk_url),
            new Building(R.string.bear_hall, R.string.Bear , R.string.bear_name, R.drawable.bear, R.drawable.bear2, R.string.bear_url),
            new Building(R.string.cis, R.string.Cis, R.string.cis_name, R.drawable.cis1, R.drawable.cis2, R.string.cis_url ),
            new Building(R.string.bluthenthal, R.string.Bluthenthal , R.string.bluthenthal_name, R.drawable.bluth, R.drawable.bluth2, R.string.bluthenthal_url)
    };

    private Building(int name, int description, int addinfo, int imageResourceId, int imageResourceId2, int url){
        this.name=name;
        this.description=description;
        this.addinfo=addinfo;
        this.imageResourceId=imageResourceId;
        this.imageResourceId2=imageResourceId2;
        this.url=url;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public int getAddinfo() {
        return addinfo;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getImageResourceId2() {
        return imageResourceId2;
    }

    public int getUrl() {
        return url;
    }
}
