package streams;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreams_primitiveTypes {

    public static void main(String[] args) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data_output.dat"));
        dataOutputStream.writeInt(42);
        dataOutputStream.writeDouble(3.14);
    }
}
