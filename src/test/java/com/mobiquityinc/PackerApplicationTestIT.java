package com.mobiquityinc;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PackerApplicationTestIT {

    private PackerApplication packerApplication;
    private static final String RESULT = "4\n" +
            "-\n" +
            "2,7\n" +
            "8,9";
    private static final String INVALID_COMMAND = "Invalid command: EMPTY COMMAND";
    private static final String INVALID_FILE = "Api exception: INVALID FILE";

    @Before
    public void setUp(){
        packerApplication = new PackerApplication();
    }

    @Test
    public void should_pack() throws Exception {
        //given
        String [] args = new String[]{"-file", "src/test/resources/item_list.txt"};

        //when
        String result = packerApplication.doMain(args);

        //than
        assertThat(result).isEqualTo(RESULT);
    }

    @Test
    public void should_warn_invalid_command() throws Exception {
        //given
        String [] args = new String[]{"-file", ""};

        //when
        String result = packerApplication.doMain(args);

        //than
        assertThat(result).isEqualTo(INVALID_COMMAND);
    }

    @Test
    public void should_warn_invalid_file() throws Exception {
        //given
        String [] args = new String[]{"-file", "src/test/resources/item_list.yyyyyy"};

        //when
        String result = packerApplication.doMain(args);

        //than
        assertThat(result).isEqualTo(INVALID_FILE);
    }
}