/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitysimulator.domain;

/**
 *
 * @author eero
 * Star is the subclass for all stars in game
 */
public class Star extends CelestialObject {
    
    String type;
    
    public Star(int id, String name, int x, int y, double xSpeed, double ySpeed, double mass, double size, int priority) {
        super(id, name, x, y, xSpeed, ySpeed, mass, size, priority);    
        type = "star";
    }
    
    @Override
    public String getType() {
        return type;
    }
}
