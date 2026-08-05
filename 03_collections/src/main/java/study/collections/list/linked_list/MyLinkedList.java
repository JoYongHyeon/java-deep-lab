package study.collections.list.linked_list;

/**
 * 이중 연결 리스트 직접 구현 클래스
 */
public class MyLinkedList<E> {

    /**
     * 노드(Node) 내부 클래스
     * 데이터(item)와 이전 노드(prev), 다음 노드(next)의 포인터 주소를 가짐.
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size = 0;
    // 첫 번째 노드를 가리키는 포인터
    private Node<E> first;
    // 마지막 노드를 가리키는 포인터
    private Node<E> last;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    /**
     * 리스트의 맨 끝에 새 원소 추가 (add / linkLast)
     *
     * 문제점
     * - 처음 원소를 넣을 때 아무 일도 안일어남
     * - last 새 노드로 옮겨주지 않음
     * - size 가 늘어나지 않음
     */
//    public void add(E element) {
//        if (first != null && last != null) {
//            last.next = new Node<>(last, element, null);
//        }
//    }

    /** 
     * 리스트의 맨 끝에 새 원소 추가 (add / linkLast)
     */
    public void add(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null); // 2. 새 노드 생성 (prev는 l)
        last = newNode; // 3. last 포인터를 새 노드로 즉시 이동

        if (l == null) {
            first = newNode; // 4-A. 비어있던 리스트라면 first 포인터도 새 노드로 지정
        } else {
            l.next = newNode; // 4-B. 기존 노드가 있었다면 기존 노드(l)의 next를 새 노드로 연결
        }
        size++;
    }

    /**
     * [미션 2] 리스트의 맨 앞에 새 원소 추가 (addFirst / linkFirst)
     */
    public void addFirst(E element) {
        // 1. 기존 첫 번째 노드를 스택 지역변수에 백업
        final Node<E> f = first;

        // 2. 새 노드 생성 (next는 f)
        final Node<E> newNode = new Node<>(null, element, f);

        // 3. first 포인터를 새 노드로 즉시 이동
        first = newNode;

        // 4-1. 비어있던 리스트라면 last 포인터도 새 노드로 지정
        if (f == null) {
            last = newNode;
        // 4-2. 기존 노드가 있었다면 기존 노드(f)의 prev를 새 노드로 연결!
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * [미션 3] 지정한 인덱스의 원소 조회 (get)
     * - 배열과 달리 포인터를 타고 이동해야 함 ($O(N)$).
     * - JDK 최적화 팁: index가 (size / 2) 보다 작은지 큰지에 따라 first부터 찾을지, last부터 역순으로 찾을지 결정 (양방향 탐색)
     */
    public E get(int index) {
        // TODO: 직접 구현해 보세요!
        return null;
    }

    /**
     * [미션 4] 지정한 인덱스의 노드 삭제 및 반환 (remove / unlink)
     * - 해당 노드의 이전(prev)과 다음(next) 포인터를 서로 직통으로 연결하여 기존 노드를 끊어낸다.
     * - 끊어낸 노드의 item, next, prev를 모두 null 처리하여 GC 수거 대상으로 만든다.
     */
    public E remove(int index) {
        // TODO: 직접 구현해 보세요!
        return null;
    }

    /**
     * [미션 5] 모든 노드 비우기 (clear)
     * - 단순히 size = 0 만 하는 것이 아니라, 모든 노드를 순회하며 item, next, prev를 null로 연결 해제해야
     *   GC가 엮여있는 모든 노드 개체를 완벽히 메모리 해제함 (Explicit Nulling).
     */
    public void clear() {
        // TODO: 직접 구현해 보세요!
    }

    /**
     * [미션 6] 문자열 출력 (toString)
     * - first 노드부터 next 포인터를 타고 끝까지 순회하며 [A, B, C] 형태로 출력한다.
     */
    @Override
    public String toString() {
        if (size == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        Node<E> x = first; // 1. first 노드부터 출발
        while (x != null) { // 2. next 손가락을 타고 null이 아닐 때까지 이동
            sb.append(x.item);
            if (x.next != null) {
                sb.append(", ");
            }
            x = x.next; // 다음 노드로 이동
        }
        sb.append("]");
        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
