package study.collections.list.array_list;


import java.util.Arrays;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elementData.length;
    }

    public void add(E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;

        // 1.5배 증가
        int newCapacity = oldCapacity * 3 / 2;

        // oldCapacity 가 0 일 경우 대비
        if (newCapacity <= oldCapacity)
            newCapacity++;
        
        // elementData를 새 크기로 교체
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // toString 기능 직접 구현
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 내가 만든 clear() - 2026-07-24
     * - 루프마다 힙(Heep) 메모리의 'this.size'와 'elementData'를 매번 주소 찾아가서 읽음
     *   (미세한 성능 차이)
     */
//    public void clear() {
//        for (int i = 0; i < size; i++) {
//            elementData[i] = null;
//        }
//        this.size = 0;
//    }

    /**
     * 수정 된 clear()
     * 스택(Stack) 캐싱: 'size'와 'elementData'를 멀리있는 힙(Heep)대신 CPU 바로 옆
     *                 스택 지역변수('to', 'es')에 1번만 읽어와 캐싱.
     *
     */
    public void clear() {
        int to = size;
        size = 0;
        final Object[] es = elementData;
        for (int i = 0; i < to; i++) {
            es[i] = null;
        }
    }

    // 내가 만든 remove
//    public void remove(int index) {
//        checkIndex(index);
//
//        int to = size;
//        final Object[] es = elementData;
//
//        // size = 5 ([A, B, C, D, E] )
//        // remove(1) = B
//        for (int i = index; i < to - 1; i++) {
//            es[i] = es[i + 1];
//        }
//    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        // 인덱스 범위 체크
        checkIndex(index);

        int to = size;
        final Object[] es = elementData;

        // 지워질 원래 값 기억
        E oldValue = (E) es[index];

        // 전체 사이즈 - 지울 인덱스 - (배열크기 - 1 = 인덱스위치)
        int numMoved = to - index -1;

        // 당길 원소가 있을 때만 메모리 복사 실행
        if (numMoved > 0) {
            System.arraycopy(es, index + 1, es , index, numMoved);
        }

        // 맨 뒤 잔재 null 처리 및 size 1 감소
        es[to - 1] = null;
        size--;
        // 지워진 값 반환
        return oldValue;
    }

    //  내가 작성 2026-07-27
//    public boolean contains(E element) {
//        int to = size;
//        final Object[] es = elementData;
//
//        for (int i = 0; i < to; i++) {
//            if (element == null) {
//                if (es[i] == null) {
//                    return true;
//                }
//            } else {
//                if (element.equals(es[i])) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public boolean contains(E element) {
        int to = size;
        final Object[] es = elementData;

        if (element == null) {
            for (int i = 0; i < to; i++) {
                if (es[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < to; i++) {
                if (element.equals(es[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}
