
package edu.moravian.model;

import java.util.ArrayList;
import java.util.Collection;

public class TowerList {
    public TowerList()
    {
        
    }
    private ArrayList<Tower> towerMembers = new ArrayList<>();
    
    public void addTower(Tower tower)
    {
        towerMembers.add(tower);
    }
    
    public void removeTower(Tower tower)
    {
        towerMembers.remove(0);
    }
    
    public Collection<Tower> getClanMembers()
    {
        while (towerMembers.size() < 100) {

        }
    }
}
