package com.hfad.seahawktours;

import com.hfad.seahawktours.R;

public class Building {
    private int name;
    private int description;
    private int addinfo;
    private int imageResourceId;
    private int imageResourceId2;
    private int url;
    private Double latitude;
    private Double longitude;

    public static final Building[] buildings = {
            new Building(R.string.alderman_hall, R.string.Alderman, R.string.ald_name, R.drawable.aldermanhall, R.drawable.eaalderman, R.string.ald_url, 34.226838, -77.876943 ),
            new Building(R.string.belk_hall, R.string.Belk, R.string.belk_name, R.drawable.belkhall1, R.drawable.irwinbelk, R.string.belk_url, 34.221944, -77.871404 ),
            new Building(R.string.bear_hall, R.string.Bear , R.string.bear_name, R.drawable.bear, R.drawable.bear2, R.string.bear_url, 34.228627, -77.872819),
            new Building(R.string.cis, R.string.Cis, R.string.cis_name, R.drawable.cis1, R.drawable.cis2, R.string.cis_url, 34.226131, -77.871788 ),
            new Building(R.string.bluthenthal, R.string.Bluthenthal , R.string.bluthenthal_name, R.drawable.bluth, R.drawable.bluth2, R.string.bluthenthal_url, 34.224309, -77.871365)
    };

    private Building(int name, int description, int addinfo, int imageResourceId, int imageResourceId2, int url, Double latitude, Double longitude){
        this.name=name;
        this.description=description;
        this.addinfo=addinfo;
        this.imageResourceId=imageResourceId;
        this.imageResourceId2=imageResourceId2;
        this.url=url;
        this.latitude=latitude;
        this.longitude=longitude;
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

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public int getClosestBuilding(Double latitude, Double longitude){

        Building closeBuilding = null;
        Double shortestDistance = 100.00;

        for (Building b : buildings){
            if(distance(latitude, longitude, b.latitude, b.longitude) < shortestDistance){
                shortestDistance = distance(latitude, longitude, b.latitude, b.longitude);
                closeBuilding = b;
            }
        }
        return closeBuilding.name;
    }

    public Double distance(Double x1, Double y1, Double x2, Double y2){
        return Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

}
