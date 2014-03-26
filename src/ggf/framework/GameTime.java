package ggf.framework;

public class GameTime {
    
    public static final int NANOSEC_IN_MILLISEC = 1000000;
    public static final int MILLISEC_IN_SEC = 1000;
    
    private final double startTime;
    private double runTime;
    private double deltaTime;
    private double frameTime;
    private double timeScale;
    
    public GameTime(double framesPerSecond) {
        startTime = (System.nanoTime()/NANOSEC_IN_MILLISEC);
        runTime = 0;
        frameTime = MILLISEC_IN_SEC/framesPerSecond;
        timeScale = 1;
    }
    
    public void setFPS(double framesPerSecond) {
        frameTime = MILLISEC_IN_SEC/framesPerSecond;
    }

    public double getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(double timeScale) {
        this.timeScale = timeScale;
    }
    
    public void update() {
        double newRunTime = System.nanoTime()/NANOSEC_IN_MILLISEC - startTime;
        deltaTime = newRunTime - runTime;
        runTime = newRunTime;
    }
    
    public double startTime() {
        return startTime;
    }
    
    public double deltaTime() {
        return deltaTime;
    }
    
    public double runTime() {
        return runTime;
    }
    
    public double frameTimeLeft() {
        return startTime + runTime + frameTime - System.nanoTime()/NANOSEC_IN_MILLISEC;
    }
    
    public double sStartTime() {
        return startTime * timeScale;
    }
    
    public double sDeltaTime() {
        return deltaTime * timeScale;
    }
    
    public double sRunTime() {
        return runTime * timeScale;
    }
    
    public double sFrameTimeLeft() {
        return (startTime + runTime + frameTime - System.nanoTime()/NANOSEC_IN_MILLISEC) * timeScale;
    }
    
    public double getTrueFPS() {
        return MILLISEC_IN_SEC/deltaTime;
    }
}