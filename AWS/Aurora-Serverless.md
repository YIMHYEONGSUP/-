# Aurora Serverless

## Aurora Serverless 란?
- Amazon Aurora Serverless 는 Amazno Aurora 의 On-Demand Auto Scaling 구성입니다. 
- 어플리케이션 요구 사항을 기반으로 자동으로 시작 및 종료하고 용량을 확장 또는 축소 합니다.
- Aurora Serverless를 사용하면 데이터베이스 용량을 관리하지 않고도 클라우드에서 데이터베이스를 실행할 수 있습니다.
- `Aurora의 Serverless 버전`
    - 인스턴스를 미리 프로비저닝하거나 관리할 필요가 없음
    - T2.micro / T2.mdium 등의 인스턴스 타입 선택 불필요
- V1 , V2 존재
    - V2 는 Preview 상태
    - V1 기준 서술    

## Aurora Serverless 특징
- On-Demand
    - 사용한 리소스를 1초 단위로 곽므
- Single AZ
    - 단 Multi-AZ Failover (기존의 Provisioned 보다 느림)
- 용량은 10Gb ~ 128Tb 로 자동 스케일링
- `DB Cluster Parameter Group`만 지원
    - DB Cluster Parameter Group : 클러스터 전체에 적용
    - DB Instance Parameter Group : 개별 인스턴스에 적용
- ACU (`Aurora Capacity Unit`) 단위로 컴퓨팅 조절
    - 1ACU = 약 2Gb RAM , CPU , 네트워크
    - 최대/최소 ACU 설정 가능
    - AWS 에서 Warm Pool에서 인스턴스를 준비하고 스케일링에 따라 인스턴스를 할당/회수
    - 최소 0ACU 까지 스케일 다운 가능 = 스토리지 비용만 지불
        - 단 0ACU에서 1이상의 ACU로 전환하는데 25~40초 정도 시간 소요
        - 선택적인 기능 , 최소 1ACU 이상 유지 가능
        - 디폴트 5분 , 최대 24시간
        - 7일동안 이용 내역이 없으면 스냅샷으로 저장 , 요청 발생 시 자동 복구

## Aurora Serverless의 아키텍처
- VPC EndPoints
    - Instance
- Network Load Balancer
- VPC EndPoints
    - Requester Router (Maintain to Connect)
    - Instance Layer (Request Processing)
    - Storage Layer 

## Aurora Serverless 보안과 복구
- Multi-aZ Failover
    - 장애 발생 시 자동으로 다른 AZ 복구
- 기본적으로 암호화 
    - 비활성 불가
- 패치/업데이트 시 스케일링 포인트 찾아 업데이트
    - 스케일링 포인트  : 쿼리 처리가 없는 상태
    - 하루 이상 찾지 못하면 클러스터 이벤트로 알려줌
    - TimeOutAction 설정에 따라 롤백 혹은 강제 업데이트

## Aurora Serverless 제약사항
- VPC 밖에서 사용 불가능
    - 직접 외부 접속 불가능
    - Public IP 할당 불가능
    - Data API 혹은 Bastion Host 방법으로 접근
- Replica 불가능
- 클로닝 불가능
- BackTrack 불가능
- Multi-Master 불가능
- 포트 Mysql(3306) , PostgresSQL(5432) 고정 사용
- Inter Region VPC Peering 불가능
- 엔진 버전 고정

<br/>

---
<br/>

