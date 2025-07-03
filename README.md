# 

## Model
www.msaez.io/#/134488624/storming/9439dd7b4b80aeef1f64c487df234037

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin

./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

readme

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- author
- subscriber
- writing
- point
- platform
- ai
- monitoring


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- author
```
작가 요청 - http :8082/authors email="email" authorName="authorName" introduction="introduction" featuredWorks="featuredWorks"
작가 승인 - http PUT :8082/authors/1/approveauthor
작가 비승인 - http PUT :8082/authors/1/disapproveauthor
```
- subscriber
```
구독자 가입 - http :8083/users email="email" userName="민수야" password="123" isSubscription="false"
구독권 구매 - http PUT :8083/users/{id}/buysubscription isSubscription="true"
--------------------------------------------------------------
대여 신청 - http :8083/reading userId=1 bookId=1
대여 취소 - http DELETE :8083/reading id=1 userId=1 bookId=1
```
- writing
```
집필 등록 - http :8084/manuscripts/registermanuscript title="신데렐라" content="이벤트 발행 테스트" authorId=123 authorName="윤원우"
집필 수정 - http PUT :8084/manuscripts/1/editmanuscript title="신데렐라2" content="수정된 내용22"
AI요청 - http PUT :8084/manuscripts/1/requestai
출간 요청 - http PUT :8084/manuscripts/1/requestpublishing
```
- point
```
포인트 구매 - http POST localhost:8085/points/charged userId=1 amount=3000
대여 후 포인트 확인 - http GET localhost:8085/points/1
```
- monitoring
```
구독자 - http :8080/userpages | http :8080/userpages/{id}
포인트 - http :8080/points | http :8080/points/{id}
도서 - http :8080/bookpages | http :8080/bookpages/{id}
원고 - http :8080/manuscriptpages | http :8080/manuscriptpages/{id}
대여 - http :8080/readingpages | http :8080/readingpages/{id}
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
 Azure AKS + ACR 설정 가이드

이 문서는 Azure Kubernetes Service(AKS)와 Azure Container Registry(ACR)를 연동하여 컨테이너 배포 환경을 구성하는 과정을 정리

---

## 📦 Azure CLI 설치 (Ubuntu)

```bash
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash
🔐 Azure 로그인 및 구독 선택
bash
복사
편집
az login --use-device-code
# 출력된 코드 입력 후 브라우저에서 로그인
# 구독 목록에서 사용할 구독 번호 입력
☸️ AKS 클러스터 연결
bash
복사
편집
az aks get-credentials --resource-group a1025-rsrcgrp --name a0725-aks
kubectl get all
🏗️ ACR (Azure Container Registry) 생성
bash
복사
편집
az acr create --resource-group a1025-rsrcgrp --name a1025 --sku Basic
az acr list --resource-group a1025-rsrcgrp -o table
