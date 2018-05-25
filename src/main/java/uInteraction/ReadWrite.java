package uInteraction;

import errors.MyException;
import errors.Response;
import uInteraction.data.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {

    private static final String FILENAMEREAD = "text.txt";

    public static List<Data> readFile() {
        List<Data> data = new ArrayList<Data>();
        try {
            InputStream inputStream = new FileInputStream(FILENAMEREAD);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] s = sCurrentLine.split(";");
                if (s[1].length() <= 100) {
                    data.add(new Data(Integer.parseInt(s[0]), s[1]));
                } else {
                    throw new NumberFormatException();
                }
            }
            return data;
        } catch (NumberFormatException e) {
            throw new MyException(Response.NUBER_FORMAT_EXCEPTION);
        } catch (UnsupportedEncodingException e) {
            throw new MyException(Response.INVALID_INPUT_DATA);
        } catch (IOException e) {
            throw new MyException(Response.FILE_NOT_FOUND);
        }
    }
}
