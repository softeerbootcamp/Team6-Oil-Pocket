<a name="readme-top"></a>
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://www.oilpocket.kro.kr/">
    <img src="https://user-images.githubusercontent.com/74173976/216799361-7934e2c4-85b8-4640-bf8b-e7d80d7be275.png" alt="Logo" width="70%" height="70%">
  </a>

  <h3 align="center"><a href="https://www.oilpocket.kro.kr/">⛽️ 유류비 절약 플랫폼</a></h3>

  <p align="center">
    <a href="https://youtu.be/D2eJQR__sO4">View 1st Demo</a>
    &nbsp;·&nbsp;
    <a href="https://youtu.be/f9LQ9UuMMdw">View 2nd Demo</a>
    &nbsp;·&nbsp;
    <a href="https://github.com/softeerbootcamp/Team6/discussions/landing">Report Bug</a>
    &nbsp;·&nbsp;
    <a href="https://hyundaibootcamp06.notion.site/6-Oil-Pocket-c7076bc7e7e64e51aeee7f10237f37a0">Team Notion</a>
  </p>
</div>

<br>

## 📑 &nbsp; About The Project

#### 메인화면

https://user-images.githubusercontent.com/74173976/220026872-30cf758a-0448-445b-87aa-b97b55a0c6af.mov

#### 로그인 / 회원가입

https://user-images.githubusercontent.com/74173976/220026787-be4ea910-a243-4c5c-835f-e46d822b8b2d.mov

#### 마이페이지

https://user-images.githubusercontent.com/74173976/220026714-540d982c-2e78-47a4-a208-a51c0cacecdd.mov

<br>

## 👉 &nbsp; User Flow
<img src="https://user-images.githubusercontent.com/92264609/217170500-849ef0c0-5dce-4a68-b6a5-a9343be0ad45.png" alt="">

##### 🔥 열심히 완성도를 높여가는 중!
<p align="right">(<a href="#readme-top">back to top</a>)</p>


<br>

## 🏗 &nbsp; Architecture
<img width="768" alt="스크린샷 2023-02-22 오후 6 31 59" src="https://user-images.githubusercontent.com/87477702/220579767-85df5cc2-7510-4395-917d-35f519d1db05.png">

- NGiNX
    - 프론트 웹서버로 사용
    - 리버스 프록시를 통한 WAS 보호
    - 정적파일 캐싱 이점
    - HTTPS 구성이 필요했는데 이를 좀더 원활하게 할 수 있음

- API Server
    - 비지니스 로직을 처리하는 API Server이다

- Scheduler
    - 셀레니움을 사용하여 일일 전국 유가 정보 csv파일다운 자동화
    - 해당 csv파일을 DB형태로 변경하고 Batch Insert를 활용해서 전국 일일 유가 데이터 저장
    - 매일 저장되는 과정을 slack을 통해 알람기능을 받음

- Redis
    - 세션 스토리지(쿠키/세션을 사용한 로그인 방식 사용하기 때문)
    - 사용자 최근 주유소 조회: 세션과 연동하여 지도에서 사용자가 조회한 주유소를 저장하고 주유기록때 보여줌

- MYSQL
    - 회원정보/주유소 관련 데이터를 저장

- GitHubAction
    - 프론트 백엔드의 CI/CD 자동화를 구성

- OPENAPI(TMAP, OPINET)
    - TMAP: 프론트에서 사용하며, 근처 주유소 탐색에서 사용
    - OPINET: 현재 유가 평균, 주유소 일일 가격 데이터 획득에 사용

<br>

<!-- 기술 스택은 https://simpleicons.org/?q=vite <-- 여기서 찾으세용 -->
## 📱 &nbsp; Built With

FE : &nbsp; <img src="https://img.shields.io/badge/html5-e44d26?style=flat&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/SCSS-CC6699?style=flat&logo=sass&logoColor=white"/> <img src="https://img.shields.io/badge/javascript-f7df1e?style=flat&logo=javascript&logoColor=white"/> <img src="https://img.shields.io/badge/node.JS-73AA63?style=flat&logo=node.JS&logoColor=white"/> <img src="https://img.shields.io/badge/vite-646CFF?style=flat&logo=vite&logoColor=white"/>

BE : &nbsp; <img src="https://img.shields.io/badge/Java-A8B9CC?style=flat&logo=openjdk&logoColor=white"/> <img src="https://img.shields.io/badge/Spring-5cb230?style=flat&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=Jenkins&logoColor=white"/> <img src="https://img.shields.io/badge/NGINX-009639?style=flat&logo=NGINX&logoColor=white"/> <img src="https://img.shields.io/badge/REDIS-DC382D?style=flat&logo=REDIS&logoColor=white"/> <img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white"/> <img src="https://img.shields.io/badge/amazon EC2-FF9900?style=flat&logo=amazon EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Selenium-43B02A?style=flat&logo=selenium&logoColor=white"/> <img src="https://img.shields.io/badge/docker-2496ED?style=flat&logo=docker&logoColor=white"/>  <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat&logo=GitHub Actions&logoColor=white"/>  <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat&logo=Amazon S3&logoColor=white"/>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<br>

## 📖 &nbsp; api 명세서
#### <img width="1093" alt="명세서 사진" src="https://user-images.githubusercontent.com/35219960/218905730-93371ea5-0d8d-451e-b514-91de862fb1f6.png">
#### <a href="https://hyundaibootcamp06.notion.site/API-9f0f154f923341749dfce02ee37f1cf0">명세서 자세히 보기</a>

<br>

## 📝 &nbsp; Functions
#### 1️⃣ 주변 주유소 유류비 검색 및 비교
#### 2️⃣ 로그인 / 로그아웃
#### 3️⃣ 사용자 프로필 수정
#### 4️⃣ 사용자 주유 기록 입력
#### 5️⃣ 사용자 유류비 다른 사용자와 비교
#### 6️⃣ 사용자 유류비 월별 차트
#### 7️⃣ 사용자 주유 내역 확인
#### 8️⃣ 유가 정보 1일 단위로 자동 다운로드 (셀레니움 + 스프링 스케줄러 활용)

<br>

## 🛠 ERD
<img width="809" alt="image" src="https://user-images.githubusercontent.com/35219960/218905377-26c50bec-8491-4f72-a320-5827c270e38b.png">

<br>

## 🔗 &nbsp; Project ManageMent
#### <a href="https://github.com/softeerbootcamp/Team6/wiki">Github Wiki</a>
#### <a href="https://github.com/softeerbootcamp/Team6/issues">Github Issues</a> <br>
#### <a href="https://github.com/orgs/softeerbootcamp/projects/14">Github Project 간판보드</a> <br>
#### <a href="https://www.figma.com/file/HNqNdLbIeWnWxMnB94uHwJ/User-Flow?node-id=366%3A596&t=xycFfE7TcGXamIa1-1">Design Detail</a> <br>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<br>

##  👨‍👩‍👧‍👦 &nbsp; Contributors

|```BE``` 김규민|```FE``` 문경덕|```BE``` 박원종|```FE``` 최재원|
|:-:|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/74173976/216749262-407cb3a3-f74b-4021-aea3-99cda8540be7.png" width=130>|<img src="https://user-images.githubusercontent.com/74173976/216749372-fe3715b9-9249-4e89-b43b-3bf8198c9b0b.png" width=130>|<img src="https://user-images.githubusercontent.com/74173976/216749451-eaa4cdc6-4a8d-4449-8a67-590d87ad5c6d.png" width=130>|<img src="https://user-images.githubusercontent.com/74173976/216749380-2937f315-24b6-4ddd-9b97-784de587d7b8.png" width=130>|
|[@gyuturn](https://github.com/gyuturn)|[@Moon-GD](https://github.com/Moon-GD)| [@ajongs](https://github.com/ajongs) |[@jaewonjjang](https://github.com/jaewonjjang)|
|회원가입/로그인 API|메인화면|유가 데이터 자동 다운로드|지도 API 통신|
|마이페이지/주유기록 API|로그인/회원가입|유가 조회/유저 절약 정보 API|지도 상세 텝|
|CI/CD 및 인프라 관리|마이페이지|과거 유가 데이터 csv 파일 변환 프로그램||


<p align="right">(<a href="#readme-top">back to top</a>)</p>
