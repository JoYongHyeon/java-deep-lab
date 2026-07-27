package study.collections.list.array_list;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    @DisplayName("초기 용량(capacity=4)을 초과해서 5개의 원소를 추가하면 배열 용량이 1.5배(6)로 확장되어야 한다")
    void autoResizingWhenCapacityExceeded() {
        MyArrayList<String> list = new MyArrayList<>(4);

        assertEquals(4, list.capacity());
        System.out.println("capacity = " + list.capacity());

        assertEquals(0, list.size());
        System.out.println("list.size() = " + list.size());

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.capacity());
        assertEquals(4, list.size());

        // 5번째 원소 추가 시 용량이 확장되어야 함 (4 -> 6)
        list.add("E");

        assertEquals(5, list.size());
        assertEquals(6, list.capacity(), "용량이 4에서 1.5배인 6으로 늘어났는지 확인");
        assertEquals("E", list.get(4));
    }

    @Test
    @DisplayName("유효하지 않은 인덱스에 접근 시 IndexOutOfBoundsException이 발생해야 한다")
    void throwExceptionOnOutOfBoundsIndex() {
        MyArrayList<Integer> list = new MyArrayList<>(2);
        list.add(100);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    @DisplayName("1. [toString] 값이 전혀 없을 때는 '[]' 를 반환해야 함")
    void toString_EmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>(0);

        assertEquals("[]", list.toString());
        System.out.println(list);
    }

    @Test
    @DisplayName("2. [toString] 원소가 1개일 때는 쉼표 없이 '[1]'을 반환해야 한다.")
    void toString_SingleElement() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.add(1);

        assertEquals("[1]", list.toString());
        System.out.println(list);
    }

    @Test
    @DisplayName("3. [toString()] 초기 용량(5)을 초과하여 원소 6개를 넣어도 쉼표로 구분된 '[1, 2, 3, 4, 5, 5]'를 반환해야 한다.")
    void testToString() {

        MyArrayList<Integer> list = new MyArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);


        assertEquals("[1, 2, 3, 4, 5, 5]" , list.toString());
        System.out.println(list);
    }

    @Test
    @DisplayName("clear() 직접 구현 테스트")
    void clear() {
        MyArrayList<Integer> list = new MyArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.clear();

        // 상태 검증
        assertEquals(0, list.size(), "size는 0이어야 함");
        assertEquals(5, list.capacity(), "기존 capacity=5 유지 되어야 함");
        assertEquals("[]", list.toString(), "toString은 [] 이어야 함");

        // clear() 후 검증
        list.add(99);
        assertEquals(1, list.size());
        assertEquals(99, list.get(0));
        assertEquals("[99]", list.toString());

        System.out.println(list);
    }

    @Test
    @DisplayName("[contains] 일반 원소, 맨 마지막 원소, null 원소, 객체 동등성(equals) 탐색을 완벽히 검증")
    void contains_AllScenarios() {

        MyArrayList<String> list = new MyArrayList<>(5);
        list.add("Java");
        list.add("Spring");
        list.add("Kotlin");
        list.add("Docker");
        list.add("Kubernetes");


        // 1. 중간에 존재하는 원소 & 존재하지 않는 원소 탐색
        assertTrue(list.contains("Spring"), "중간 원소 'Spring'이 존재하므로 true이어야 함");
        assertFalse(list.contains("Python"), "존재하지 않는 원소 'Python'은 false이어야 함");

        // 2. 맨 마지막 방 원소 탐색 (탐색 범위 엣지케이스 검증)
        assertTrue(list.contains("Kubernetes"), "맨 마지막 원소 'Kubernetes'도 정상 탐색되어 true이어야 함");

        // 3. 주소는 다르지만 내용(Value)이 같은 객체 탐색 (equals 동등성 검증)
        assertTrue(list.contains("Java"), "new String으로 생성해도 내용이 같으면 true이어야 함");

        // 4. null 탐색 검증 (NPE 발생 여부 및 null 탐색 검증)
        assertFalse(list.contains(null), "null을 넣지 않은 상태에서는 false이어야 함");

        list.add(null); // 실제 null 원소 추가
        assertTrue(list.contains(null), "null을 실제로 추가한 후에는 true이어야 함");
    }
}
