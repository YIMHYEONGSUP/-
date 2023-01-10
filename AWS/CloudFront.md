# Cloud Front 

## Cloud Front란?
- 개발자 친화적 환경에서 짧은 지연 시간 , 빠른 전송 속도로 데이터 , 동영상 , 어플리케이션 및 API를 CDN(고속 컨텐츠 전송 네트워크) 으로 제공하는 서비스 

## CDN이란?
- 웹페이지 , 이미지 , 동영상 등의 컨텐츠를 본래 서버에서 받아와 캐싱
- 해당 컨텐츠에 대한 요청이 들어오면 캐싱해 둔 컨텐츠 제공
- 컨텐츠를 제공하는 서버와 실제 요청 지점 간 지리적 거리가 매우 먼 경우 , 혹은 통신 환경이 안좋은 경우 요청 지점 근처의 CDN을 통해 빠르게 컨텐츠 제공 가능
- 서버로 요청이 필요 없기 때문에 서버의 부하를 낮춤

## Edge Location이란?
- 컨텐츠가 캐싱되고 유저에게 제공되는 지점

## Cloud Front 동작 방식
- 요청받은 컨텐츠가 엣지 로케이션에 있다면 바로 전달
- 요청 받은 컨텐츠가 엣지 로케이션에 없다면 컨텐츠를 제공하는 근원에서 제공받아 전달

## Cloud Front 구성
- Origin
    - 실제 컨텐츠가 존재하는 근원 (S3, EC2, ELB , Route53)
    - AWS서비스 
    - 온프레미스 서버
- Distribution 
    - CDN 구분 단위 여러 엣지 로케이션으로 구성된 컨텐츠 제공 채널
- Cache Key
    - 어떤 기준으로 컨텐츠를 캐싱할 것인지 결정
    - 기본적으로 URL
    - 이후 Header , Cookie , 쿼리스트링등을 사용 가능
- 정책
    - Cloud Front 동작 방법을 정의
    -  어떻게 캐싱할지, 어떤 내용을 Origin에 보낼지 , 어떤 헤더를 허용할지 등을 결정

## Cloud Front 기능
- CDN 
    - 컨텐츠를 최적화하여 빠르게 제공
    - 정적/`동적` 컨텐츠 모두 최적화
- 정적 컨텐츠
    - 서버를 거치지 않고 클라이언트에서 직접 보여주는 내용
    - 이미지 , css , 기타 서버가 필요없는 내용들
- 동적 컨텐츠
    - 서버계산 , DB조회등이 필요한 내용
    - 로그인 , 게시판 등
- 컨텐츠 처리 방법
    - 정적/동적 처리 구분하여 `경로패턴 분기처리`
    - ex ) Static => S3 , Dynamic => EC2
- Https 지원
    - Origin에서 Https 를 지원하지 않더라도 Https 통신을 지원할 수 있도록 구성 가능
- 지리적 제한
    - 특정 지역의 컨텐츠 접근 제한 가능
- 다른 서비스 연계
    - AWS WAF , Lambda@Egde 등을 사용 가능

## Cloud Front 최적화
- 정적컨텐츠
    - 캐싱으로 접근 속도 최적화
- 동적 컨텐츠
    - 네트워크 최적화 , 연결 유지 , Gzip 압축등을 사용
        - DNS LookUp , TCP Connection , Time to First Byte 등을 최적화 

## Cloud Front 연산 서비스
- Lambda@edge
    - 람다 사용
    - 사용 예시
        - 한국에서 요청 => 한국 웹서버
        - 미국에서 요청 => 미국 웹서버
        - Cookie 검사 후 다른페이지로 리다이렉팅 => A/B 테스팅
        - CloudFront 에서 Origin 도착 이전에 인증 등
- CluoudFront Function
    - Lambda@edge 의 1/6 비용으로 경량 js 실행
    - 사용 예시
        - 캐싱 , 헤더 조작등 가벼운 작업

## Cloud Front 리포팅
- 주요 이용지표 확인 가능
    - 캐시 상태
    - 가장 많이 요청 받은 컨텐츠
    - Top Referrer

## Cloud Front 정책
- 캐시 컨트롤
    - 캐시 방법 및 압축
        - TTL 및 Cache Key 정책
        - CloudFront 가 어떻게 캐싱할 지 결정
- 원본 요청
    - Origin으로 어떤 내용을 보낼것인가
        - Origin에 쿠키,헤더,쿼리스트링 중 어떤것을 보낼 것인가     
- 뷰어에게 보낼 Http 헤더
    - Cloud Front가 뷰어에게 응답과 같이 보낼 헤더 설정
    - 확인 가능한 뷰어의 정보
        - 디바이스 타입 (Android / IOS / SmartTV / Tablet / Desktop 등)
        - IP Address
        - 지역정보 (Country / City / latitude , longitude (위도,경도) / time zone)

## Cloud Front 보안
- Signed URL
    - 어플리케이션에서 Cloud Front의 컨텐츠에 접근할 수 있는 URL 제공하여 컨텐츠 제공 방법
        - URL에는 시작,종료 시간 , IP, 파일명 , 유효기간 등의 정보를 담을 수 있음
        - 해당 URL 접근 이외 접근을 막고 허용된 유저에게만 URL 전달하여 컨텐츠 제공
        - 단 하나의 파일 또는 컨텐츠에 대한 허용만 가능
        - S3 Signed URL과 비슷한 방식
- Signed Cookie
    - 다수의 컨텐츠의 제공 방식을 제어하고 싶을 때 사용
        - Signed URL과 마찬가지로 여러 제약사항 설정 가능
        - 다수의 파일 및 스트리밍 접근 허용 가능 
- Origin Access Identity
    - S3의 컨텐츠를 Cloud Front를 사용해서만 볼 수 있도록 제한하는 방법
    - CloudFront만 권한을 가지고 S3에 접근하고 나머지 접근권한은 막음
    - S3 Bucket Policy로 CloudFront 접근을 허용해야 사용 가능
- Field Level Encryption
    - CloudFront로 부터 Origin 사이의 통신을 암호하
    - 최대 10개 필드까지
    - 공개키 방식으로 암호화 => CloudFront에 공개키 제공 후 Origin에서 Private Key로 해독
