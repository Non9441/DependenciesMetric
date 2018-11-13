import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Writer {

    private String fileNanme;

    public Writer(String fileNanme) {
        this.fileNanme = fileNanme;
    }

    public void write(List<PackageInfo> packageInfoList) {
        FileWriter writer;
        try {
            writer = new FileWriter(fileNanme);
            PrintWriter out = new PrintWriter(writer);

            out.println("id,package,NC,A,I,D");
            int id = 0;
            for (PackageInfo packageInfo : packageInfoList) {
                out.print(++id + ",");
                out.print(packageInfo.getPackageName() + ",");
                out.print(packageInfo.getClassCount() + ",");
                out.print(packageInfo.getAbstract() + ",");
                out.print(packageInfo.getInstability() + ",");
                out.println(packageInfo.getDistance());
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
