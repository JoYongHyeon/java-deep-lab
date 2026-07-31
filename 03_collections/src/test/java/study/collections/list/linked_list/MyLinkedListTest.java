package study.collections.list.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    @DisplayName("MyLinkedList 기본 동작 테스트 뼈대")
    void testSkeleton() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("add() 메서드 테스트")
    void testAdd() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0);
        assertEquals(1, list.size(), "list.size() == 1");
    }
}
