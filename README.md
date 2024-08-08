# MSA 구현 개인 프로젝트 과제

![image](https://github.com/user-attachments/assets/41c05e2f-1f22-4514-b9a1-bdff803af858)

<aside>
🏁 **Goal:  MSA 구성, Redis 캐싱, Docker 기반 CI/CD 구성하기**

</aside>

<aside>
❗ **필수 기능**

</aside>

- [ ]  **기본 API 구성하기**
    - API 목록
        1. `POST /products`  상품 추가 API 
        **상품 Entity**
            
            
            | Key | Value |
            | --- | --- |
            | product_id | Long (Primary, Auto Increment) |
            | name | String |
            | supply_price | Integer |
        2. `GET /products` 상품 목록 조회 API
            
            **응답 형태: List<응답 객체>**
            
            **응답 객체**
            
            | Key | Value |
            | --- | --- |
            | product_id | Long |
            | name | String |
            | supply_price | Integer |
        3. `POST /order` 주문 추가 API
            
            **주문 Entity**
            
            | Key | Value |
            | --- | --- |
            | order_id | Long (Primary, Auto Increment) |
            | name | String |
            | product_ids | List<주문 매핑 상품 Entity> |
            
            **주문 매핑 상품 Entity**
            
            | Key | Value |
            | --- | --- |
            | id | Long (Primary, Auto Increment) |
            | order | 주문 Entity |
            | product_id | Long |
        4. `PUT /order/{orderId}`  주문에 상품을 추가하는 API
            
            **요청 Body**
            
            | Key | Value |
            | --- | --- |
            | product_id | Long |
        5. `GET /order/{orderId}`  주문 단건 조회 API
            
            **응답 객체**
            
            | Key | Value |
            | --- | --- |
            | order_id | Long |
            | product_ids | List<Integer> |
        6. `GET /auth/signIn?user_id={string}`  로그인 API 
            
           Gateway 서비스의 Filter를 통과할 수 있도록 구성해
            

- [ ]  **상품 서비스는 라운드로빈 형식으로 로드밸런싱 구성하기**
    1. 라운드로빈 형식으로 로드밸런싱을 구현해서 상품 서비스가 호출될 때마다 두 서비스를 반복하며 호출되게 구성
    2. 상품 목록을 조회할 때마다 API 의 응답 헤더의 `Server-Port` 값이 `19093` , `19094` 로 변경되어야 함
    
- [ ]  **주문에 상품을 추가하는 API를 만들 때 아래와 같이 구성**
    1.  `FeignClient`를 이용해서 주문 서비스에 상품 서비스 클라이언트 연결
    2. 상품 목록 조회 API를 호출해서 파라미터로 받은 `product_id` 가 상품 목록에 존재하는지 검증
    3. 존재할경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않음.
    
- [ ]  **분산추적 구현해보기**
    - `주문 서비스` 와 `상품 서비스` 에 Zipkin 을 연동하고, 호출시 Zipkin 대시보드에 `Duration` 이 측정 되는지 확인해보기
    
- [ ]  **캐싱 기능 구현하기**
    - 주문 조회 API 의 결과를 캐싱 처리하여 **60초 동안**은 DB 에서 불러오는 데이터가 아닌 
    **메모리에 캐싱된 데이터**가 보여지도록 설정

- [ ]  **외부 요청 보호**
    - Oauth2,JWT 기반으로 인증/인가를 구성하여 인가 없이 `상품 서비스`, `주문 서비스`를 호출할 때 
    **401 HTTP Status Code**를 응답하도록 설정

- [ ]  **캐시를 더 활용 해보기**
    1. 상품 추가 API를 호출 할 경우 상품 목록 조회 API의 응답 데이터 캐시가 갱신되도록 구현
    2. 상품 추가 후 상품 목록 조회 API호출 시 데이터가 변경 되는지 확인
    
- [ ]  **로컬과 서버의 환경을 분리**
    - 로컬에서는 localhost:3306 으로 DB에 접근하고, 서버에 배포시엔 RDS 주소로 접근하게 구성하도록 환경을 분리 - 환경은 `dev`, `prod` 프로필로 나누기
