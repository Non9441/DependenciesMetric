import java.util.ArrayList;
import java.util.List;

public class PackageInfo {

    String packageName;

    double ca;
    double ce;
    double na;
    double nc;
    private List<ClassInfo> classes;

    public PackageInfo(String packageName) {
        this.ca = 0;
        this.ce = 0;
        this.na = 0;
        this.nc = 0;
        this.packageName = packageName;
        this.classes = new ArrayList<ClassInfo>();
    }

    public String getPackageName() { return this.packageName; }

    public void increaseCa() { this.ca++; }

    public void increaseCe() { this.ce++; }

    public void increaseNa() { this.na++; }

    public void increaseNc() { this.nc++; }

    public double getInstability() {
        return ca/(ca+ce);
    }

    public double getAbstract() {
        return na/nc;
    }

    public double getDistance() {
        return Math.abs(getAbstract() + getInstability() - 1);
    }

    public int getClassCount() {
        return (int) nc;
    }

    public void addClass(ClassInfo classInfo) {
        this.classes.add(classInfo);
    }
}
