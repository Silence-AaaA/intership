package com.Silence.mapper;

public interface MapMapper {

    void setMap(int[][] map);

    Double getMaxNum();

    void setMaxNum(Double maxNum);

    void createTimeRateMap();

    void calculateLocation();
}
