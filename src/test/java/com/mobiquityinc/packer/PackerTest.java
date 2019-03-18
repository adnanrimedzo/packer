package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class PackerTest {

    private static final String TEST_FILE_NAME = "src/test/resources/item_list.txt";
    private static final String INVALID_TEST_FILE_NAME = "src/test/resources/invalid_item_list.txt";
    private static final String RESULT = "4\n" +
            "-\n" +
            "2,7\n" +
            "6,9";

    @Test
    public void should_return_list() throws APIException {
        //when
        String result = Packer.pack(TEST_FILE_NAME);

        //then
        assertThat(result).isEqualTo(RESULT);
    }

    @Test
    public void should_throw_exception() {
        //when
        Throwable throwable = catchThrowable(() -> Packer.pack(INVALID_TEST_FILE_NAME));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessage("INVALID FILE");
    }
}