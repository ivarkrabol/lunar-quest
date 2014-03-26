package ggf.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GraphicsManager {
    
    public static final int DEFAULT_WINDOW_WIDTH = 1024;
    public static final int DEFAULT_WINDOW_HEIGHT = 768;
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
    
    private String windowTitle;
    private int windowWidth;
    private int windowHeight;
    private JPanel jPanel;
    private JFrame jFrame;
    
    public GraphicsManager(String windowTitle) {
        this.windowTitle = windowTitle;
        
        windowWidth = DEFAULT_WINDOW_WIDTH;
        windowHeight = DEFAULT_WINDOW_HEIGHT;
        
        jPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(windowWidth, windowHeight);
            }
            
            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g = (Graphics2D)graphics;
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                draw(g);
            }
            
            @Override
            public boolean isManagingFocus() {
                return false;
            }
        };
        jPanel.setDoubleBuffered(true);
        jPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        
        jFrame = new JFrame(windowTitle);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        
        init();
        
        jFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point center = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(center.x - (jFrame.getWidth() / 2), center.y - (jFrame.getHeight() / 2));
        jFrame.setLocation(newLocation);
        jFrame.setVisible(true);
        
    }
    
    public void repaint() {
        jPanel.repaint();
    }
    
    public void addInputListeners(KeyListener keyListener, MouseListener mouseListener) {
        jFrame.addKeyListener(keyListener);
        jFrame.addMouseListener(mouseListener);
    }
    
    public void setWindowSize(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }
    
    public void setBackgroundColor(Color color) {
        jPanel.setBackground(color);
    }
    
    protected abstract void draw(Graphics2D g);
    protected abstract void init();

}
