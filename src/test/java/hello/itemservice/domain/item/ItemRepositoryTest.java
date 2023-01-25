package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void find() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item saveItem = itemRepository.save(item1);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParam);

        //then
        Assertions.assertThat(saveItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(saveItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(saveItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }


}
