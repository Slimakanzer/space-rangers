package com.spaceRangers.entities;

public class AttackPlanet {
    private boolean attacked;
    private Integer countShips;

    public Integer getCountShips() {
        return countShips;
    }

    public void setCountShips(Integer countShips) {
        this.countShips = countShips;
    }


    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean getAttacked() {
        return attacked;
    }
}
