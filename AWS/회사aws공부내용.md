# Placement Group (배치그룹)

## Placement Group 이란?
- 새로운 EC2 인스턴스를 시작하면 인스턴스는 기본 하드웨어 전반에 분산되도록 하여 장애 시의 영향도를 줄임
- 워크로드에 따라서 인스턴스의 배치를 조정하는 것이 더 유리한 경우도 있음
- 배치그룹을 사용하여 인스턴스 배치 전략을 선택하여 워크로드에 따른 최적의 배치를 할 수 있도록 함
- 배치그룹을 사용하는 것에 따른 별도의 비용은 발생하지 않음

## 클러스터 Cluster
- 인스턴스를 가용 영역 내에 서로 근접하게 위치
- HPC (High Perfomence Computing) 와 같이 노드 간의 통신 시 Latency를 낮도록 구성이 필요한 경우 사용
- 높은 네트워크 처리량에 유리
- 클러스터 배치 그룹의 경우 동일한 인스턴스 유형을 사용하는 것을 권고
- 배치 그룹 내 인스턴스를 중지 후 다시 실행하는 경우 동일 배치그룹에서 시작하며 이 때 용량 부족으로 실행 불가할 수 있음
    - 배치그룹을 모두 정지하여 재시작시 배치그룹 내 인스턴스를 수용 가능한 하드웨어에서 시작될 수 있음
- Multi-AZ 사용 불가능
- 클러스터 배치 그룹 내부에서는 인스턴스가 최대 10Gbps , 외부와는 최대 5Gpbs 사용 가능
- 인터넷 및 DX를 통한 연결은 각각 최대 5Gbps 로 제한

## 파티션 Partition
- 논리적 파티션 분리를 통해 , 한 파티션에 있는 인스턴스 그룹이 다른 파티션의 인스턴스 그룹과 하드웨어를 공유하지 않도록 함
- 하둡 , 카산드라 , 카프카 등의 대규모 분산 및 복제 워크로드에 필요
- 파티션 배치 그룹 사용하여 인스턴스 생성 시에는 파티션에 인스턴스를 균일하게 배포
- 파티션 배치 그룹에서 실행할 수 있는 인스턴스는 계정에 제한
- 가용 영역당 최대 7개 파티션 배치 영역 가능
- 전용 인스턴스가 있는 파티션 배치 그룹은 최대 2개만 가능
- 전용 호스트에서는 사용 불가

## 분산 Spread
- 인스턴스 그룹을 다른 하드웨어로 분산하여 인스턴스 간의 장애 시 영향을 최소화 함
- 분산형 배치 그룹은 별개의 랙에 배치
- 분산 그룹당 가용영역별 최대 7개 실행중인 인스턴스 보유 가능
- 7개 이상의 인스턴스가 필요한 경우 다수의 분산형 배치 그룹 사용
- 서로 다른 분산형 배치 그룹간에는 동일한 하드웨어를 사용하게 될수도 있음
- 전용 인스턴스 및 전용 호스트에서는 사용 불가

## 배치그룹 규칙 및 제한사항 (공통)
- 배치그룹의 이름은 AWS계정&리전에서 고유해야함
- 배치 그룹 병행 불가
- 인스턴스는 하나의 배치 그룹에만 속할 수 잇음
- 테넌시를 host로 지정한 경우 배치 그룹 불가



---


# ENI , ENA , EFA

## ENI란?
- Elastic Network Interface 으로 인스턴스가 다른 서비스 (aws, 다른 인스턴스 , 온프레미스 , 인터넷) 등과 같은 다른 네트워크 리소스와 통신할 수 있도록 하며 SSH , RDP (Remote Desktop Protocol) 등을 이용해서
인스턴스에서 실행중인 OS와 통신할 수 있게 해줍니다.

- 기본 프라이빗 IP 주소 및 보조 프라이빗 IP 주소
    - 각 인스턴스는 서브넷으로 지정한 범위 내의 기본 프라이빗 IP주소를 지녀야 하며 기본 프라이빗 IP 주소는
    인스턴스의 기본 ENI와 연결됩니다. 이 주소는 변경 , 삭제는 불가하지만 기본 ENI에
    보조 프라이빗 IP 주소를 할당해서 사용할 수 있습니다.

- ENI 는 인스턴스와 독립적으로 존재할 수 있습니다.

## 성능강화 네트워크 
- Elastic Network Adapter (ENA)
    - 100Gbs 의 속도를 제공하며 대부분의 인스턴스 타입을 제공합니다.
    - 높은 대역폭과 낮은 지연시간
- Elastic Fabric Adaopter (EFA)
    - HPC 또는 ML (Merchine Learning) 등의 속도를 높일수 있는 네트워크 디바이스입니다.
    - 기존 클라우드 기반 HPC 시스템에서 사용하는 TCP 전송보다 지연율이 낮고 일정하며 더 높은 처리량을 제공합니다.

## 퍼블릭 , 프라이빗 탄력적 IP
 - 프라이빗 IP는 인스턴스 실행시 마다 변동됩니다,
 - 퍼블릭Ip에 탄력적 IP (Elastic IP)를 부여하여 고정적으로 네트워크 연결을 할 수 있습니다.
 - FailOver 시에 다른 AZ 에 Elastic Ip 만 부여하여 해결할 수 있습니다.

## NAT for Public address
- 인스턴스는 실행될 때마다 퍼블릭 IP가 변경됩니다.
- 프라이빗 IP는 변경되지 않습니다.


## private subnet and Bastion hosts
- Region
    - VPC
        - AZ
            - public subnet
                - CIDR 블록을 사용합니다.
                - 0.0.0.0/0 은 igw-id (인터넷 게이트웨이) 모두 허용합니다.
            - private subnet
                - 인터넷 게이트웨이로 접속할 수 없습니다.
                - 퍼블릭 서브넷으로 연결 뒤 베스티안 호스트를 이용하여 연결합니다. (프라이빗 IP 연결)

## NAT Gateways and NAT Instances Overview
|비교|NAT Instance|NAT Gateway|
|--|--|--|
|관리|사용자|AWS|
|확장|Scale up [인스턴스 타입]| 유연하게 확장 가능 최대 45Gbps|
|가용성|낮음|높음|
|보안|SG|No SG|
|접속|베스쳔 호스트|SSH접속불가|
|연결|NAT인스턴스의 Elastic Ip or Public Ip | NAT 게이트웨이와 Elastic IP 연결|
|포트포워딩|가능|불가능| 

## EC2 Instance LifeCycle
- AMI Launch
    - Pending 
        - Running
            - reboot
                - rebooting
            - stop
                - stopping
                    - relevant EBS-baked volumes only
                        - Stopped
                            - pending
                            - terminated
            - terminate
                - shutting-down
                - terminated

- Stoppping EC2 Instances
    - EBS baked instances Only
    - No charge for stopped instances
    - EBS volums remain attached (chargeable)
    - Data in Ram is lost
    - Instance is migrated to a diffrent host
    - private ipv4 addresses and ipv6 addresses retained
    public ipv4 addresses relased
    - Associated Elastic IPs retained

- Hibernating Ec2 Instances
    - Applies to On-demand or reserved Linux Instances
    - Contents of RAM saved to EBS volume
    - Must be enabled for hibernation when launched
    - Specific prerequisites apply
    - When started (after hibernation):
         - The EBS root volume is restored to its previous state
         - The RAM contents are reloaded
         -The proccesses that were previously running on the instance are resumed 
         - Previously attached data volumes are reattached and the instance retains its instance ID
- Rebooting EC2 instances
    - Equivalent to an OS reboot
    - DNS name and all ipv4 and ipv6 addresses retained
    - does not affect billing
- Retiring Ec2 Instances
    - Instances may be retired if AWS detects irreparable
    failure of the underlying hardware that hosts the instance
    - When an instance reaches its scheduled retirement date ,
    it is stopped or terminated by AWS
- Terminating EC2 Instances
    - Means deleting the EC2 instance
    - Cannot recover a terminated instance
    - By default root EBS volumes are deleted
- Recovering EC2 instances
    - CloudWatch can be used to monitor system status
    checks and recover instance if needed
    - Applies if the instance becomes impaired due to underlying hardware / platform issues
    - Recovered instance is identical to original instance

## AWS Nitro System
- Nitro is the underlying platform for the next generation of EC2 instances
- Support for many virtualized and bare metal instances types
- Breaks functions into specialized hardware with a Nitro Hypervisor 
- Specialized hardware includes 
    - Nitro cards for VPC
    - Nitro cards for EBS
    - Nitro for Instances Storages 
    - Nitro card Controlelr 
    - Nitro security chip
    - Nitro Hypervisor
    - Nitro Enclaves 
- Improves performance , Security and innovation
    - Performance close to bare metal for virtualized instances
    - Elastic Network Adapter and Elastic Fabric Adapter
    - More baremetal instance types
    - Higher network performance (e.g. 100 Gbps)
    - High Perfomance Computing (HPC) optimizations
    - Dense storage instaces (e.g. 60TB)

## AWS Nitro Enclaves (5세대 인스턴스)
    - Isolated compute environments
    - Runs on isolated and hardened virtual machines
    - No persistent storage , interactive access , or external networking
    - Uses cryptographic ttestation to ensure only authorized code is running
    - Integrates with AWS Key Management Service 
    - Protect and securely process highly sensitive data
        - Personally identifiable information
        - Healthcare data
        - Financial data
        - Intellectual Property data

## Nitro Instance Feature (5세대 인스턴스 특징)
- 특징
    - Nitro Hypervisor 도입
    - NVMe (Non-volatile Memory express) 스토리지 사용
        - 초고속 저장장치
        - SSD 전용 통신 표준/프로토콜
        - PCle 버스 통해 작동 
            - 하드 디스크보다 메모리에 가까움
        
    - ENA 네트워크 모듈 사용
- EC2 인스턴스 가상화 기술 발전
    - 참고 링크
        - https://kim-dragon.tistory.com/11
    - 발전 순서
        1. Xen PV
        2. Xen HVM
        3. Nitro
            - KVM 코어 커널모듈 사용

## Amazon EC2 가격 , 옵션 , 사용사례
- On-Demand
    - 특징
        - 할인정책 x
        - 사용한 만큼만 가격 부여
            - 상용소스 (시간당 지불)
            - 오픈소스 (초당 지불)
    - 사용사례
        - 선불결제 , 장기 약정 없이 EC2 사용
        - 중단 할 수 없는 단기 , 예측할 수 없는 워크로드가 있는 어플리케이션
        - 개발 , 테스트 어플리케이션
- Reserved
    - 특징
        - 1 or 3 년 약정
        - 최대 75% 할인
    - 사용사례
        - 수요가 꾸준한 어플리케이션
        - 예약 용량이 필요할 수 있는 어플리케이션
        - 컴퓨팅 비용 절감을 위해 장기적으로 사용해야하는 고객
- Spot Instances
    - 특징
        - AWS 인스턴스 총량을 실제 수요보다 넉넉히 잡아놓음
            그로 인해 남는 여유 인스턴스를 저렴하게 제공하는 것
        - 최대 90% 할인
        - 인스턴스 총수요 증가 시 언제든 반납
        - 가격이 실시간으로 변하며 지정한 가격 이하일 때 사용 , 지정한 가격보다 높을 시 반환
    - 사용사례
        - 언제든 종료되어도 상관 없는 케이스
            - 빅데이터 처리
            - 머신러닝
        - 많은 인스턴스가 필요한 어플리케이션
- Dedicated Instances
    - 특징
        - 실제 물리적 서버 대여
            - 이유
                - 가상 컴퓨팅은 여러 사용자가 함께 사용 시 퍼포먼스 하락 요소가 있기때문
    - 사용사례
        - 보안 , 규정(라이센스) 등에 따른 이유
        - 퍼포먼스 이유
- Savings Plan
    - 특징
        - EC2 + Fargate + Labmda 를 함께 사용하여 최대 3분의1 가격으로 사용

## Amazon EC2 Billing
- EC2
    - 상용소스
        - Widnow , Red Hat EL, SUSEES , 상용 Linux 등 시간단위

    -오픈소스
        - Amazon Linux , Ubuntu 등 On-Deamnd , Reserved , Spot 단위 가능
- Reserved Instances (Rls)
    - 연 1200 시간 이상 사용시 예약 인스턴스 사용 권장
        - 일 6시간 4일 = 1248시간 (주 24시간 이상 사용 시)
- Savings Plan
    - 특징
        - 시간 당 사용금액 미리 약정
        - 1년 또는 3년 선택
        - 
    - Compute Saving Plans
        - on-deamnd 대비 최대 66% 할인 적용 
        - 인스턴스 패밀리 , 사이즈 , AZ , 리전 , OS , 테넌시 상관 없이 적용되는 플랜
        - EC2 , Fargate , Lambada 자동 적용
    - EC2 Instance Saving Plans
        - on-deamnd 대비 최대 72% 할인 적용
        - 리전 , 인스턴스 패밀리 정해서 적용
        - 사이즈 , AZ , OS , 테넌시상관 없이 적용 EC2만 적용
- Spot Instance
    - Spot Instance
        - 하나 이상 EC2 인스턴스
    - Spot Fleet
        - 다수의 인스턴스 
        - 온디맨드
    - EC2 Fleet 
        - 다수 스팟 
        - 온디맨드
        - 예약인스턴스 단일 API CALL
    - Spot Block
        - 지정 방식
            ```
                $ aws ec2 request-spot-instances \
                    --block-duration-minutes 360 \ 사용시간
                    --instance-count 5 \ 인스턴스 갯수
                    --spot-price "0.25" ... 지정 금액
            ```
- 전용호스트 , 전용 인스턴스 차이점
|특징|전용 호스트|전용 인스턴스|
|--|--|--|
|결제 사용| 호스트 단위 결제 | 인스턴스 단위 결제 |
|소켓 , 코어 및 호스트 ID 표시| 소켓 및 물리 코어 수 표시 여부 제공 | x |
|호스트 및 인스턴스 선호도| 시간에 따라 지속적으로 동일한 물리서버 인스턴스 배포 허용 | x |
|대상 지정 인스턴스 배치|물리 서버 내 인스턴스 배치 방법에 대한 추가 가시성 및 제어 제공 | x |
|자동 인스턴스 복구||지원|
|BYOL (Bring Your Own License) | 지원 | x |
|용량 예약| x | 지원 |



