package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class PackerApplication {

    @Option(name = "-file", usage = "file path")
    public String path = "";

    public static void main(String[] args) throws Exception {
        try {
            System.out.println(new PackerApplication().doMain(args));
            System.exit(0);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public String doMain(String[] args) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if ("".equals(path))
                throw new IOException("EMPTY COMMAND");

            return Packer.pack(path);
        } catch (IOException e) {
            return "Invalid command: " + e.getMessage();
        } catch (APIException e) {
            return "Api exception: " + e.getMessage();
        }catch (Exception e){
            return "Unexpected exception: " + e.getMessage();
        }
    }
}
