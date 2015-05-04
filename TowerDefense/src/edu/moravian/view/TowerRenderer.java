/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.view;

import edu.moravian.math.CoordinateTranslator;
import edu.moravian.model.Entity;
import edu.moravian.model.Tower;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import towerdefense.Game;

/**
 *
 * @author Acer
 */
public class TowerRenderer {
    private int drawX, drawY;
    private  Renderable renderable;
    private  CoordinateTranslator ct;

     public TowerRenderer(Renderable renderable) throws SlickException
    {
        ct = Game.getInstance().getCT();
        this.renderable = renderable;
    }
    public void update(Tower spriteEntity)
    {
        drawX = spriteEntity.getEntityMX();
           
        drawY =spriteEntity.getEntityMY();
  
    }
    
    public void render(Graphics grphcs)
    {
            renderable.draw(drawX, drawY);
    }
    
    
}
