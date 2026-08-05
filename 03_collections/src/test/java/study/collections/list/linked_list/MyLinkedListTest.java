package study.collections.list.linked_list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    @DisplayName("[add] get() 없이 size와 toString()만으로 add() 동작 검증")
    void testAddOnly() {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.add("A");
        assertEquals(1, list.size());
        assertEquals("[A]", list.toString());

        list.add("B");
        assertEquals(2, list.size());
        assertEquals("[A, B]", list.toString());

        list.add("C");
        assertEquals(3, list.size());
        assertEquals("[A, B, C]", list.toString());
    }

    @Test
    @DisplayName("[addFirst] get() 없이 size와 toString()만으로 addFirst() 메서드만 독립 검증")
    void testAddFirstOnly() {
        MyLinkedList<String> list = new MyLinkedList<>();

        // 1. 비어있는 상태에서 addFirst
        list.addFirst("C");
        assertEquals(1, list.size());
        assertEquals("[C]", list.toString());

        // 2. 이미 원소가 있는 상태에서 연속 addFirst
        list.addFirst("B");
        assertEquals(2, list.size());
        assertEquals("[B, C]", list.toString());

        // 3. 맨 앞에 또 addFirst
        list.addFirst("A");
        assertEquals(3, list.size());
        assertEquals("[A, B, C]", list.toString());
    }
}
