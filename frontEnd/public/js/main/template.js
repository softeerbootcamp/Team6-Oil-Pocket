const getMainViewContentTemplate = () => `
    <section class="main">
        <section class="searchBar">
            <a href="/userDetail">
                <div class="searchBar__left">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="60" viewBox="0 0 120 120" fill="#fff">
                        <path xmlns="http://www.w3.org/2000/svg" d="M32.0993 0.386597V17.3706H20.2353C9.47856 17.3706 0.461898 27.0515 0.461898 38.6007V47.0927C0.461898 58.6419 7.42213 68.1529 17.7043 71.0402L58.2002 82.2497C60.4148 82.929 63.5785 87.175 63.5785 89.7227V98.2147C63.5785 100.592 61.8385 102.461 59.6238 102.461H20.0771C18.1788 102.461 16.7552 101.781 16.1224 101.442V85.4766H0.303711V102.461C0.303711 108.235 3.46745 113.161 7.26394 115.708C10.9022 118.426 15.4897 119.445 20.0771 119.445H31.9411V136.429H47.7598V119.445H59.6238C70.5387 119.445 79.3972 109.934 79.3972 98.2147V89.7227C79.3972 78.1735 72.437 68.6624 62.1548 65.7752L21.659 54.5657C19.4443 53.8863 16.2806 49.6403 16.2806 47.0927V38.6007C16.2806 36.2229 18.0207 34.3547 20.2353 34.3547H59.782C61.5221 34.3547 63.104 35.034 63.7367 35.3737V51.3387H79.5554V34.3547C79.5554 28.5801 76.3917 23.6547 72.5952 21.1071C68.9569 18.3897 64.3695 17.3706 59.782 17.3706H47.918V0.386597L32.0993 0.386597Z" fill="#fff"/>
                    </svg>
                    <h1>나의 절약 현황</h1>
                </div>
            </a>
            <a href="./mapView.html">
                <div class="searchBar__right">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="60" viewBox="0 0 100 120" fill="#fff">
                        <path xmlns="http://www.w3.org/2000/svg" d="M65.172 100.621H64.8371C63.6085 100.547 62.4389 100.069 61.5101 99.2614C60.5813 98.4538 59.9455 97.3618 59.7016 96.1554L51.1052 53.899C50.8864 52.8262 50.357 51.8415 49.5828 51.0673C48.8086 50.2931 47.8239 49.7637 46.7511 49.5449L4.49469 40.9485C3.28601 40.7085 2.19072 40.0752 1.37963 39.1475C0.568541 38.2197 0.0872346 37.0497 0.0107628 35.8198C-0.065709 34.5899 0.266953 33.3692 0.956876 32.3481C1.6468 31.3271 2.6552 30.563 3.82484 30.1751L93.1383 0.422536C94.1409 0.0085846 95.2423 -0.104334 96.3079 0.0975839C97.3736 0.299502 98.3574 0.807508 99.139 1.55951C99.9206 2.31151 100.466 3.27493 100.709 4.33201C100.952 5.38909 100.882 6.49404 100.507 7.51179L70.7541 96.8253C70.3657 97.9807 69.6098 98.9771 68.6019 99.6626C67.5939 100.348 66.3893 100.685 65.172 100.621Z" fill="#fff"/>
                    </svg>
                    <h1>근처 주유소 검색</h1>
                </div>
            </a>
            </section>
            <img class="chatBotArea__img" src="./public/img/main/chatBot/openBtn.png" alt="chatBot">
            <section class="explainAreaArray">
            <section class="explainArea" id="explainArea--1">
                <div class="explainArea__text">
                    <h1>간단한 회원가입 폼</h1>
                    <h2>ID와 비밀번호만을 통해 가입할 수 있어요.</h2>
                </div>
                <img class="explainArea__phone" src="./public/img/main/phoneAnimation/01.png" alt="phone_01">
            </section>
            <section class="explainArea" id="explainArea--2">
                <div class="explainArea__text">
                    <h1>편안한 주유 입력</h1>
                    <h2>
                        기름 종류, 주유액, 주유소를 입력하고 <br>
                        나만의 차계부를 관리할 수 있어요.
                    </h2>
                </div>
                <img class="explainArea__phone" src="./public/img/main/phoneAnimation/02.png" alt="phone_02">
            </section>
            <section class="explainArea" id="explainArea--3">
                <div class="explainArea__text">
                    <h1>이번 달 주유 금액 분석</h1>
                    <h2>
                        평균 주유 금액을 통해 <br>
                        주행자님의 소비 습관을 분석해요. <br>
                        친근한 음식을 통해 <br>
                        한 눈에 절약 금액을 파악할 수 있어요.
                    </h2>
                </div>
                <img class="explainArea__phone" src="./public/img/main/phoneAnimation/03.png" alt="phone_03">
            </section>
            <section class="explainArea" id="explainArea--4">
                <div class="explainArea__text">
                    <h1>월별 사용량 비교</h1>
                    <h2>
                        전국 주유소 평균 가격과 나의 사용량을 <br>
                        월별 차트를 통해 확인할 수 있어요.
                    </h2>
                </div>
                <img class="explainArea__phone" src="./public/img/main/phoneAnimation/04.png" alt="phone_04">
            </section>
            <section class="explainArea" id="explainArea--5">
                <div class="explainArea__text">
                    <h1>주유 내역 확인</h1>
                    <h2>
                        차계부 페이지에서 <br>
                        날짜, 주유소, 주유 금액, 절약 금액 내역을 <br>
                        한 눈에 확인할 수 있어요.
                    </h2>
                </div>
                <img class="explainArea__phone" src="./public/img/main/phoneAnimation/05.png" alt="phone_05">
            </section>
        </section>
    </section>
`;

const getChatBotTemplate = () => `
    <div class="chatBotArea__shadowArea"></div>
    <div class="chatBotArea__closeArea">
        <img class="chatBotArea__closeBtn" src="./img/main/chatBot/closeBtn.png" alt="chatBotCloseBtn">
    </div>
    <div class="chatBotArea__background">
        <div class="chatBotArea__profile">
            <img src="./img/main/chatBot/managerProfile.png" alt="profile">
            <span>안녕하세요 주행자님. 어떤 것을 도와드릴까요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>마이 페이지는 어떻게 활용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>주변 주유소 검색은 어떻게 활용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>그 밖에 문의를 하고 싶어요.</span>
        </div>
    </div>
`;

const getManagerChatTemplate = () => `
    <img src="./img/main/chatBot/managerProfile.png" alt="profile">
    <span>또 궁금한 게 있나요?</span>
`;

const getMyPageAnswerChatTemplate01 = () => 
    `<span>마이 페이지는 프로필 수정, 주유 기록 입력, 이번 달 비교, 월별 비교, 주유 기록 열람 텝으로 이루어져 있어요.</span>`;

const getMyPageAnswerChatTemplate02 = () => 
    `<span>주행자님의 인적 정보를 자유롭게 수정하고, 주유 기록을 관리할 수 있는 공간이에요.</span>`;

const getGasAnswerChatTemplate01 = () => 
    `<span>주행자님 접속 위치를 기반으로 주변 주유소를 보여드릴꺼에요.</span>`;

const getGasAnswerChatTemplate02 = () => 
    `<span>지도에서 위치를 확인할 수 있고, 지도 좌측 리스트에서 주유소 요약 정보를 확인할 수 있어요.</span>`;

const getGasAnswerChatTemplate03 = () => 
    `<span>주유소를 클릭하면 상세 정보를 확인할 수 있습니다.</span>`;

const getETCAnswerChatTemplate01 = () => 
    `<span>기타 문의 내용은 아래 메일로 보내주시면 친절히 답변드리도록 하겠습니다. 😀</span>`;

const getETCAnswerChatTemplate02 = () => 
    `<span>E-mail : <strong>king4mun@gmail.com</strong></span>`;

export { 
    getMainViewContentTemplate, getChatBotTemplate, getManagerChatTemplate,
    getMyPageAnswerChatTemplate01, getMyPageAnswerChatTemplate02,
    getGasAnswerChatTemplate01, getGasAnswerChatTemplate02, getGasAnswerChatTemplate03,
    getETCAnswerChatTemplate01, getETCAnswerChatTemplate02
}