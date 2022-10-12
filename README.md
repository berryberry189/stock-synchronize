# stock-synchronize

재고시스템 재현
동시성 이슈 해결 프로젝트 

## java redis client

### Lettuce

- spring data redis 를 이용하면 lettuce가 기본이므로 별도의 라이브러리를 사용하지 않아도 된다
- setnx 명령어를 활용하여 분산락 구현
    - key,value 를 set 할 때 기존의 값이 없을때만 set 하는 방식
- spin lock 방식
    - retry 로직을 개발자가 작성 해야함
    - 동시에 많은 스레드가 lock 획득 대기 상태라면 redis에 부하가 갈 수 있다

### Redisson

- pub-sub 기반으로 lock 구현
    - 채널을 매개로 lock을 점유중인 스레드가 lock을 획득하려고 대기중인 스레드에게 해제를 알려주면 안내를 받은 스레드가 lock 획득시도를 하는 방식
    - 별도의 retry 로직 작성할 필요 없음
    - lettuce 에 비해 redis에 부하가 덜 간다
- lock 획득 재시도를 기본으로 제공한다
- 별도의 라이브러리 사용 필요
<br>

<aside>
💡 재시도가 필요하지 않는 lock 은 lettuce 를 활용하고
재시도가 필요한 lock 은 reddison 을 활용하는 것이 좋다

</aside>
