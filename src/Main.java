import java.util.*;

public class Main {

    public static void main (String[] args) {
        Collector collector = new Collector();
        collector.calculate(args[0], args[1]);

        Map<String, PackageInfo> map = collector.getAllPackages();
        List<PackageInfo> packageInfoList = new ArrayList<>();
        packageInfoList.addAll(map.values());

        Collections.sort(packageInfoList, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo o1, PackageInfo o2) {
                return o2.getClassCount() - o1.getClassCount();
            }
        });

        Writer writer = new Writer(args[2]);
        writer.write(packageInfoList);

    }
}
