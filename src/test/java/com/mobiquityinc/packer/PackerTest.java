package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;

import java.util.LinkedList;
import java.util.List;

import com.mobiquityinc.model.Pack;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

public class PackerTest {

    private static final String TEST_FILE_NAME = "src/test/resources/item_list.txt";
    private static final String INVALID_TEST_FILE_NAME = "src/test/resources/invalid_item_list.txt";
    private static final String RESULT = "4\n" +
            "-\n" +
            "2,7\n" +
            "8,9";

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
        assertThat(throwable).isInstanceOf(APIException.class).hasMessage("INVALID FILE");
    }

    @Test
    public void knapsack() {
        //given
        List<Item> itemList = new LinkedList<>();
        itemList.add(new Item(1, 53.38, 45.0));
        itemList.add(new Item(2, 88.62, 98.0));
        itemList.add(new Item(3, 10.70, 51.0));
        itemList.add(new Item(4, 34.98, 12.0));

        List<Item> itemListPack = new LinkedList<>();
        itemListPack.add(new Item(1, 53.38, 45.0));
        itemListPack.add(new Item(3, 10.70, 51.0));

        Pack pack = new Pack(itemListPack, 64.08, 96.0);

        //when
        Pack result = Packer.knapsackUnbounded(80.0, itemList);

        //then
        assertThat(result).isEqualTo(pack);
    }
}