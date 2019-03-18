package com.mobiquityinc.util;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Pack;
import com.mobiquityinc.model.PackSpecs;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PackReaderTest {

    private static final String TEST_FILE_NAME = "src/test/resources/valid_content.txt";
    private static final String INVALID_TEST_FILE_NAME = "src/test/resources/invalid_content.txt";

    @Test
    public void should_read_packages() throws APIException {
        //given
        PackSpecs pack1 = new PackSpecs(new LinkedList<>(), 81.0);
        pack1.getItemList().add(new Item(1, 53.38, 45.0));
        pack1.getItemList().add(new Item(2, 88.62, 98.0));

        PackSpecs pack2 = new PackSpecs(new LinkedList<>(), 8);
        pack2.getItemList().add(new Item(1, 15.3, 34.0));

        List<PackSpecs> packList = new LinkedList<>();
        packList.add(pack1);
        packList.add(pack2);

        //when
        List<PackSpecs> result = PackReader.readPackages(TEST_FILE_NAME);

        //then
        assertThat(result).isEqualTo(packList);
    }

    @Test
    public void should_throw_exception_for_invalid_file() {
        //when
        Throwable throwable = catchThrowable(() -> PackReader.readPackages(INVALID_TEST_FILE_NAME));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).hasMessage("INVALID FILE");
    }
}