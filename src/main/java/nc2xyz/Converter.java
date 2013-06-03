package nc2xyz;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Converter {

    private final Input input;

    public Converter(Input input) {
        this.input = input;
    }

    public void convert() throws IOException {
        char sep = ',';
        PrintStream out = System.out;
        List<String> coordinateNames = input.getCoordinateNames();
        String variableName = input.getVariableName();
        for (String coordinateName : coordinateNames) {
            out.print(coordinateName);
            out.print(sep);
        }
        out.print(variableName);
        out.println();
        long size = input.getSize();
        for (int i = 0; i < size; i++) {
            for (int c = 0; c < coordinateNames.size(); c++) {
                double coordinateValue = input.getCoordinateValue(c, i);
                out.print(coordinateValue);
                out.print(sep);
            }
            double value = input.getValue(i);
            out.print(value);
            out.println();
        }
    }
}
