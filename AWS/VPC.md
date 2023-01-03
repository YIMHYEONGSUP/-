# VPC

## VPC란?
- AWS 클라우드에서 `논리적으로 격리된 공간`을 프로비저닝 하여 `가상네트워크`에서 AWS리소스를 시작할 수 있습니다.
- `IP주소범위선택 , 서브넷 생성 , 라우팅 테이블 및 네트워크 게이트웨이 구성` 등 가상 네트워킹 환경을 제어할 수 있습니다.
- Default VPC
    - 계정 생성 시 자동으로 셋업되어 있음 (모든 리전)
    - 모든 서브넷의 인터넷 접근이 가능
    - EC2가 퍼블릭 IP 와 Private IP 모두 가지고 있음
    - `삭제 시 복구 불가`

- Custon VPC
    - 새로 만들어야 함
    - Default VPC 특징 가지고 있지 않음

- VPC으로 할 수 있는 일
    - EC2 실행
    - 서브넷 구성
    - 보안 설정 (IP block , 인터넷에 노출되지 않은 EC2)
- VPC Peering : VPC 간 연결
    - Transitive Peering 불가능 : 한다리 건너 연결은 Peering 이 아님 (간접 연결 x 직접 연결 o)
- VPC Flow Log
    - VPC 로그를 Cloud Watch에 저장 가능
- IP 대역 지정 가능
- Region에 하나 : 다른 리전으로 확장 불가능 

## VPC의 구성요소
1. Availability Zone (Az)
    - 물리적으로 분리되어 있는 인프라가 모여 있는 데이터 센터
    - 고가용성을 위해 항상 일정 거리 이상 떨어져 있음
    - 하나의 리전은 2개 이상의 AZ으로 이루어져 있음
    - 각 계정의 AZ는 다른 계정의 AZ와 다른 아이디를 부여 받음
        - 계정 1의 AZ-A 는 계정 2의 AZ-A와 다른 곳에 있음
2. Subnet
    - VPC의 하위 단위
    - 하나의 AZ에만 생성 가능 : 다른 AZ로 확장 불가
        - 하나의 AZ에는 여러 Subnet 생성 가능
    - Private Subnet : 인터넷에 접근 불가능한 Subnet
    - Public Subnet : 인터넷에 접근 가능한 Subnet
    - `CIDR` Block Range 설정 가능
3. Internet Gate Way (IGT)
    - 인터넷으로 나가는 통로 
    - 고가용성이 확보되어 있음
    - IGW 으로 연결되지 않은 서브넷 = Private 
    - Route Table 에서 연결해줘야 함
    - 기본생성 x
4. Network Access Control List (NACL) / Security Group
    - 방화벽 역할
    - NACL > Stateless 
    - SG > Stateful
    - 기본적으로 VPC 생성 시 기본 생성
    - DENY 는 NACL에서만 가능
5. Route Table 
    - 트래픽이 어디로 가야할 지 알려주는 이정표
    - 기본적으로 VPC 생성 시 기본 생성
6. Network Address Translation Instance / NAT GateWay
7.Bastion Host
8. VPC EndPoint 




