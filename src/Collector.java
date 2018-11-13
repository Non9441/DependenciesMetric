import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Collector {
    private Map<String, PackageInfo> allPackages;
    private Map<String, ClassInfo> allClassInfo;
    static String root;

    public Collector() {
        allPackages = new TreeMap<>();
        allClassInfo = new TreeMap<>();
    }

    public void calculate(String directory, String root) {
        Collector.root = root;
        collectFromDirectory(directory);
        calculateInfo();
    }

    private void calculateInfo() {
        Collection<ClassInfo> all = allClassInfo.values();
            for(ClassInfo info : all) {
                for(String imported: info.getImportedPackage()) {
                    PackageInfo p = allPackages.get(imported);
                    if(p!=null) p.increaseCa();
                }
            }

    }

    public void collectFromDirectory(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isDirectory()) collectFromDirectory(directory+"/"+listOfFiles[i].getName());

            if (listOfFiles[i].isFile()) {
                if(isJavaFile(listOfFiles[i].getName())) {
                    ClassInfo classInfo = new ClassInfo(listOfFiles[i]);
                    String packageName = classInfo.getPackageName();

                    if (packageName == null) {
                        continue;
                    }

                    allClassInfo.put(classInfo.getPackageName() + "." + classInfo.getClassName(),classInfo);

                    if (allPackages.get(packageName) == null) {
                        allPackages.put(packageName, new PackageInfo(packageName));
                    }

                    PackageInfo packageInfo = allPackages.get(packageName);
                    packageInfo.increaseNc();
                    packageInfo.addClass(classInfo);

                    if (classInfo.isAbstact()) { packageInfo.increaseNa(); }
                    if (classInfo.isImporting()) { packageInfo.increaseCe(); }
                }
            }
        }
    }

    public boolean isJavaFile(String fileName) {
        return fileName.endsWith(".java");
    }

    public Map<String, PackageInfo> getAllPackages() {
        return this.allPackages;
    }
}
