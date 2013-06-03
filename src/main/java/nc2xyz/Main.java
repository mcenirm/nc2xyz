package nc2xyz;

import java.io.IOException;

/**
 * Convert a netCDF file to XYZ.
 *
 */
public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length != 1) {
            System.err.println(String.format("Usage: %s NC_FILE", Main.class.getName()));
            System.exit(1);
        }
        Input input = new Input(args[0]);
        Converter converter = new Converter(input);
        converter.convert();
    }
}
