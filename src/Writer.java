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

            out.println("instability,abstractness");
            for (PackageInfo packageInfo : packageInfoList) {
                out.print(packageInfo.getInstability() + ",");
                out.println(packageInfo.getAbstract());
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
