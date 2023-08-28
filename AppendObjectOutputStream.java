import java.io.ObjectOutputStream;
import java.io.*;
// Importing utility classes
import java.util.*;

public class AppendObjectOutputStream extends ObjectOutputStream {
    AppendObjectOutputStream() throws IOException
    {

        // Super keyword refers to parent class instance
        super();
    }

    // Constructor of this class
    // 1. Parameterized constructor
    AppendObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}
