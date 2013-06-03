package nc2xyz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ucar.ma2.Array;
import ucar.ma2.Section;
import ucar.nc2.Dimension;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class Input {

    private final String pathname;
    private final String variableName;
    private final NetcdfFile netcdfFile;
    private final Variable variable;
    private final Variable[] coordinateVariables;
    private Array[] coordinateArrays;
    private Array valueArray;

    Input(String pathname, String variableName) throws IOException {
        this.pathname = pathname;
        this.variableName = variableName;
        this.netcdfFile = NetcdfFile.open(pathname);
        this.variable = netcdfFile.findVariable(variableName);
        List<Dimension> dimensions = variable.getDimensions();
        this.coordinateVariables = new Variable[dimensions.size()];
        for (int i = 0; i < coordinateVariables.length; i++) {
            Dimension dimension = dimensions.get(i);
            String name = dimension.getFullName();
            Variable coordinateVariable = netcdfFile.findVariable(name);
            coordinateVariables[i] = coordinateVariable;
        }
        this.coordinateArrays = new Array[coordinateVariables.length];
    }

    Input(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetcdfFile getNetcdfFile() {
        return netcdfFile;
    }

    public List<String> getCoordinateNames() {
        List<String> rv = new ArrayList<String>();
        for (int i = 0; i < coordinateVariables.length; i++) {
            Variable cv = coordinateVariables[i];
            String fullName = cv.getFullName();
            rv.add(fullName);
        }
        return rv;
    }

    String getVariableName() {
        return variable.getFullName();
    }

    long getSize() {
        return variable.getSize();
    }

    double getCoordinateValue(int c, int i) throws IOException {
        if (coordinateArrays[c] == null) {
            Variable x = coordinateVariables[c];
            coordinateArrays[c] = x.read();
        }
        return coordinateArrays[c].getDouble(i);
    }

    double getValue(int i) throws IOException {
        if (valueArray == null) {
            this.valueArray = variable.read();
        }
        return valueArray.getDouble(i);
    }
}
