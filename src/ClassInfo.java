import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;

public class ClassInfo {
    private BufferedReader reader;
    private File file;

    private boolean isAbstact;
    private boolean isImporting;
    private String packageName;
    private Set<String> importedPackage;
    private String className;

    public ClassInfo(File file) {
        this.file = file;
        this.isAbstact = false;
        this.isImporting = false;
        this.className = this.file.getName();
        collectInfo();
    }

    public void collectInfo() {
        String line;
        try {
            reader = new BufferedReader(new FileReader(this.file));
            while((line=reader.readLine())!=null) {
                if (line.startsWith("package")) {
                    String[] data = line.split(" ");
                    packageName = data[1].substring(0, data[1].length()-1);
                }
                if(line.startsWith("public abstract class") || line.startsWith("public interface")) this.isAbstact = true;
                if(line.startsWith("import " + Collector.root)) {
                    this.isImporting = true;
                    String[] data = line.split(" ");
                    String useClassName = data[1].substring(0, data[1].length()-1);
                    String usePackageName = useClassName.substring(0, useClassName.lastIndexOf("."));
                    importedPackage.add(usePackageName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAbstact() { return this.isAbstact; }
    public boolean isImporting() { return this.isImporting; }
    public String getPackageName() { return this.packageName; }
    public Set<String> getImportedPackage() { return this.importedPackage; }
    public String getClassName() { return className.replace(".java", ""); }
    public String getAbsolutePath() { return this.file.getPath(); }


}
