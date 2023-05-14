public class Person {
    public Person(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isDetected = false;
    }

    public void inMove(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public void detect() {
        this.isDetected = true;
    }

    public boolean getdetect() {
        return isDetected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int id;
    private int x;
    private int y;
    private boolean isDetected;
}
