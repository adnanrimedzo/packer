package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class PackerApplication {

    @Option(name = "-file", usage = "pddx file path")
    public String path = "";

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        try {
            new PackerApplication().doMain(args);
            System.exit(0);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public void doMain(String[] args) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (arguments.isEmpty())
                throw new CmdLineException(parser, "No argument is given");

            Packer.pack(path);

        } catch (CmdLineException ce) {
            System.err.println("Invalid command " + ce.getMessage());
        } catch (APIException e) {
            System.err.println("Api exception " + e.getMessage());
        }
    }
}
