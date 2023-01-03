# RDS

## RDS란?
- Amazon Relational Database Service 의 약어로 클라우드에서 관계형 데이터베이스를 간편하게 설정,운영 및 확장할 수 있습니다.

## RDS의 특징
- ### 관계형 데이터베이스를 제공하는 서비스
    - Relational Database Service : 관계형 데이터베이스
    
- ### 가상 머신 위에서 동작
    - 단 직접 시스템에 로그인 불가능 > OS 패치 , 관리 등은 AWS의 역할

- ### RDS 는 Serverless 서비스가 아님
    - 단 Aurora Serverless 는 Serverless 서비스

- ### CloudWatch 와 연동
    - DB 인스턴스의 모니터링 (EC2와 동일)
    - DB에서 발생하는 여러 로그 (Error ,General 등)을 CloudWatch 와 연동하여 확인 가능

- ### 내부에서는 EC2를 활용
    - VPC 안에서 동작
        - 기본적으로 public IP를 부여하지 않아 외부에서 접근 불가능
        - 설정에 따라 Public 으로 오픈 가능 (DNS로 접근)
    - 서브넷과 보안그룹 지정 필요
    - EC2 타입의 지정 필요
    - 스토리지는 EBS를 활용
        - EBS 타입 선택 필요
        - 생성 시 EBS 용량을 지정해서 생성
- ### Paramter Group 
    - Root 유저만 설정 가능한 DB의 설정값들을 모아 그룹화
    - DB 클러스터에 파라메터 그룹을 적용시켜 설정값을 적용

- ### 업데이트
    - 마이너 버전 엔진 업데이트는 자동으로 업데이트 설정 가능
    - 기타 업데이트의 경우 점검시간을 활용하여 업데이트 설정 가능

- ### RDS 인증방법
    - 유저/패스워드 방식
        - AWS Secret Manager 와 연동하여 자동 로테이션 가능
    - IAM DB 인증
        - 데이터베이스를 IAM 유저 Credential / Role 을 통해 관리 가능
    - Kerberos 인증
        - Active Dir 내 포함 (대칭키 기반)

- ### RDS 가격 모델
    - 컴퓨팅파워 + 스토리지용량 + 백업용량 + 네트워크비용
    - Reserved Instance (RI) 구매 가능

    
- ### RDS 제공 DB엔진
    - 라이선스 비용 o
        - MySQL Server
        - Oracle
            - Oracle OLAP
    - 라이선스 비용 x
        - MySQL Server
        - PostgresSQL
        - MariaDB
        - Amazon Aurora

- ### RDS 암호화
    - 암호화 지원
    - SQL 서버 혹은 Oracle TDE (Transparent Data Encryption) 지원
    - 모든 엔진에서 EBS 볼륨 암호화 지원
        - Default Master Key 혹은 생성한 Master Key 선택 가능
    - 자동 백업 , 스냅샷 , Read Replica 등에 적용됨


## RDS 백업

- ### 자동 백업
    - 매일마다 스냅샷을 만들고 트랜잭션 로그 저장
    - 데이터는 S3에 저장되며 데이터베이스의 크기 만큼의 공간 점유
    - 저장된 데이터를 바탕으로 일정 기간 내의 특정 시간으로 롤백 가능
        - 다른 DB 클러스터를 새로 생성
    - 1~35일까지 보관 지원
    - BackUp 을 시행할 때는 약간의 딜레이 발생 가능성
    - 기본적으로 사용 상태로 설정되어 있음

- ### 수동 백업
    - 유저 , 혹은 다른 프로세스로 부터 요청에 따라 만들어지는 스냅샷
    - 데이터베이스가 삭제된 이후에도 계속 보관
    - 스냅샷의 복구는 항상 새로운 DB Instance를 생성하여 수행

- ### RDS Multi AZ
    - 두개 이상의 AZ에 걸쳐 데이터베이스를 구축하고 원본과 다른 DB (StandBy) 를 자동으로 동기화 (Sync)
        - SQL Server , Oracle , MySql , PostgresSQL , MariaDB 에서 지원
        - Aurora의 경우 다중 AZ를 설계 단계에서 지원
    - 원본 DB의 장애 발생 시 자동으로 다른 DB가 원본으로 승격됨 (DNS가 StandBy DB로)
    - StandBy DB는 접근 불가능
    - 퍼포먼스의 상승 효과가 아닌 안정성을 위한 서비스

- ### RDS Multi Region
    - 다른 리전에 지속적으로 동기화 시키는 DB 클러스터를 생성
        - Async 복제
    - 주로 로컬 퍼포먼스 혹은 DR 시나리오로 활용
    - 각 리전별로 자동 백업 가능
    - 리전별로 Multi-AZ 가능

- ### Read Replica (읽기 전용 복제본)
    - 특징
        - 원래의 데이터베이스의 읽기 전용 복제본을 생성 (Async)
        - 쓰기는 원본 데이터베이스에 , 읽기는 복제본에서 처리하여 워크로드 분산
        - MySql , PostgresSql , MariaDB , Oracle , Aurora 에서 지원
        - 안정성이 아닌 퍼포먼스를 위한 서비스
        - 총 5개까지 생성 가능
        - 각각의 복제본은 고유 DNS가 할당됨 => 접근가능
            - 원본 DB의 장애 발생 시 수동으로 DNS 변경이 필요함
        - 복제본 자체에 Multi-AZ 설정 가능 (MySql , MariaDB , PostgresSql , Oracle)
        - Multi-AZ DB에 Read Replica 설정 가능
        - 자동 백업이 활성화 되어 있어야 읽기 전용 복제본 생성 가능
        - 각 DB의 엔진 버전이 다를 수 있음

- ### RDS AZ,Reigon , R2 비교

| |Multi-AZ|Multi-Region|Read Replica|
|------|------|---|---|
|목적|고가용성|DR/로컬 퍼포먼스|확장성/성능|
|복제방식|Sync|Async|Async|
|액티브|Only Primary DB R/W|Only Read|Only Read|
|백업|자동백업(StandBy 기준)|자동백업가능|기본적으로 백업 x|
|엔진 업데이트|Only Priamry|각 리전별로 다른 업데이트|DB별로 다른 업데이트|
|Fail Over|Auto StandBy FailOver|manual FailOver|manual FailOver|







