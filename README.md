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
 http :8088/authors id="id"email="email"authorName="authorName"introduction="introduction"featuredWorks="featuredWorks"isApprove="isApprove"
```
- subscriber
```
 http :8088/users id="id"email="email"userName="userName"password="password"isSubscription="isSubscription"
 http :8088/readings id="id"isReading="isReading"startReading="startReading"webUrl="webURL"
```
- writing
```
 http :8088/manuscripts id="id"authorId="authorId"title="title"content="content"authorName="authorName"date="date"aiImage="ai_image"aiSummary="ai_summary"
```
- point
```
 http :8088/points id="id"point="point"isSubscribe="isSubscribe"
```
- platform
```
 http :8088/books id="id"authorId="authorId"bookName="bookName"category="category"isBestSeller="isBestSeller"authorName="authorName"aiImage="ai_image"aiSummary="ai_summary"bookContent="bookContent"view="view"date="date"
```
- ai
```
 http :8088/ais id="id"manuscriptId="manuscriptId"aiImage="ai_image"aiSummary="ai_summary"title="title"authorId="authorId"content="content"
```
- monitoring
```
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
