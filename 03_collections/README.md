# 03_collections

컬렉션 주요 메서드 백지에서 직접 구현 -> 테스트 코드 검증 -> JDK 소스 비교해보기.

### MyArrayList
- initialCapacity 0/1일 때 1.5배 공식 쓰면 정수 버림으로 크기 고정됨 -> newCapacity <= oldCapacity 보장 조건
- remove나 clear 후 맨 뒤 칸 null 처리 안 해주면 GC 수거 안 됨 (Explicit Nulling)
- contains(null) 호출 시 equals 부르면 NPE 터짐 -> == null 분기 필수
- for문 당기기보다 System.arraycopy가 빠른 이유 및 numMoved > 0 호출 스킵 최적화
- JDK clear()에서 to, es 스택 지역변수 캐싱으로 힙 메모리 접근 횟수 줄이는 디테일
