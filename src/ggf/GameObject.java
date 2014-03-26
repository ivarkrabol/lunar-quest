package ggf;

import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import ggf.framework.Controls;
import java.awt.Graphics2D;

public interface GameObject {

    public void update(GameTime time, GameStateManager stateMgr, Controls controls);
    public void draw(Graphics2D g);
    
}
