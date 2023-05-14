public class Person {
    public Person(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isDetected = false;
    }
    public void detect() {
        this.isDetected = true;
    }
    public boolean getdetect(){
        return isDetected;
    }
    public int getX(){
        return x;
    }
    public  int getY(){
        return y;
    }

    private int id;
    private int x;
    private int y;
    private boolean isDetected;
    .
        <.
