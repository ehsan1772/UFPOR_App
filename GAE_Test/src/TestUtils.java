import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ehsan Barekati on 6/21/15.
 */
public class TestUtils {
    public static ArrayList<LineItem> getLines(String ifcFile) {
        String[] ifcs = ifcFile.split("\n");
        ArrayList<LineItem> result = new ArrayList<>();

        for (String line : ifcs) {
            if (line.charAt(0) == '#') {
                result.add(LineItem.getLineItem(line));
            }
        }

        return result;
    }

    public static LineItem findByName(String type, ArrayList<LineItem> lines) {
        for (LineItem line : lines) {
            if (line.type.equals(type)) {
                return line;
            }
        }

        return null;
    }

    public static ArrayList<LineItem> findAllByName(String type, ArrayList<LineItem> lines) {
        ArrayList<LineItem> items = new ArrayList<>();

        for (LineItem line : lines) {
            if (line.type.equals(type)) {
                items.add(line);
            }
        }

        return items;
    }

    public static ArrayList<String> findNumbers(String string) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(string);

        ArrayList<String> result = new ArrayList<>();

        while (m.find()) {
            result.add(m.group());
        }

        return result;
    }

    public static LineItem findByNumber(String numberString, ArrayList<LineItem> lines) {
        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher(numberString);

        if (m.find()) {
            int number = Integer.valueOf(m.group());
            for (LineItem line : lines) {
                if (line.number == number) {
                    return line;
                }
            }
        }

        return null;
    }

    public static class LineItem {
        ArrayList<String> properties;
        int number;
        String type;
        String source;

        public LineItem(ArrayList<String> properties, int number, String type, String source) {
            this.properties = properties;
            this.number = number;
            this.type = type;
            this.source = source;
        }

        public static LineItem getLineItem(String line) {
            if (line.charAt(0) != '#') {
                return null;
            }

            String originalLine = line;

            String[] items = line.split("=");
            int number = Integer.valueOf(items[0].substring(1));

            Pattern p = Pattern.compile("IFC(\\w)+");
            Matcher m = p.matcher(line);
            m.find();

            String type = m.group();

            String propertiesLine = items[1].substring(type.length() + 1);

            ArrayList<String> properties = new ArrayList<>();

            String[] preprops = propertiesLine.split(",");

            StringBuilder collectionProperty = new StringBuilder();

            boolean collectionPropertyInProgress = false;
            for (String preprop : preprops) {

                if (preprop.charAt(preprop.length() - 1) == ')') {
                    collectionProperty.append(preprop);
                    collectionPropertyInProgress = false;
                    properties.add(collectionProperty.toString());
                    continue;
                }

                if(collectionPropertyInProgress) {
                    collectionProperty.append(", ");
                    collectionProperty.append(preprop);
                    continue;
                }

                if (preprop.length() < 3 || !preprop.substring(0, 3).equals(" (#")) {
                    properties.add(preprop);

                } else {
                    collectionProperty.append(preprop);
                    collectionPropertyInProgress = true;
                }
            }

            if (collectionPropertyInProgress) {
                properties.add(collectionProperty.toString());
            }

            return new LineItem(properties, number, type, originalLine);

        }
    }
}
