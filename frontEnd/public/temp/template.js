const getChatBotAreaTemplate = () => `
    <div class="chatBotArea__background">
        <div class="chatBotArea__closeArea">
            <img class="chatBotArea__closeBtn" src="./img/closeBtn.png" alt="chatBotCloseBtn">
        </div>
        <div class="chatBotArea__profile">
            <img src="./img/managerProfile.png" alt="profile">
            <span>안녕하세요 주행자님. 어떤 것을 도와드릴까요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>근처 주유소 검색은 어떻게 사용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>출발지/도착지 주유소 검색은 어떻게 사용하나요?</span>
        </div>
        <div class="chatBotArea__chat">
            <span>다국어 제안을 하고 싶어요.</span>
        </div>
        <div class="chatBotArea__chat">
            <span>그 밖에 문의를 하고 싶어요.</span>
        </div>
    </div>
`;

const getLoginAreaTemplate = () => `
    <section class="loginArea__backGround">
        <div class="loginArea__frame">
            <h1>Oil Defence</h1>
            <input type="text" placeholder="ID를 입력해주세요.">
            <input type="text" placeholder="PW를 입력해주세요.">
            <button>로그인</button>
            <label for="loginArea__registerBtn">
                <span>새로운 주행자이신가요?</span>
                <button id="loginArea__registerBtn">회원가입</button>
            </label>
        </div>
    </section>
`

const getRegisterAreaTemplate = () => `
    <section class="registerArea__backGround">
        <div class="registerArea__frame">
            <h1>Oil Pocket</h1>
            <div class="registerArea__TermOfUse">
                <h2>이용 약관</h2>
                <div class="registerArea__TermOfUseBox">
                    Oil Pocket 이용약관, 개인정보 수집 및 이용, 위치기반서비스 이용약관(선택)에 모두 동의하시는 경우, 회원 가입을 진행해주세요.
                </div>
            </div>
            <div class="registerArea__dropDownArea">
                <select name="registerArea__genderBtn" id="registerArea__genderBtn">
                    <option disabled selected value="성별">성별</option>
                    <option value="남">남자</option>
                    <option value="여">여자</option>
                </select>
                <select name="registerArea__ageBtn" id="registerArea__ageBtn">
                    <option disabled selected value="나이">나이</option>
                    <option value="20대">20대</option>
                    <option value="30대">30대</option>
                    <option value="40대">40대</option>
                    <option value="50대">50대</option>
                    <option value="60대 이상">60대 이상</option>
                </select>
            </div>
            <div class="registerArea__IDArea">
                <input type="text" placeholder="ID를 입력해주세요.">
                <button>중복 확인</button>            
            </div>
            <input type="text" placeholder="PW를 입력해주세요.">
            <input type="text" placeholder="PW를 다시 입력해주세요.">
            <button>회원가입</button>
        </div>
    </section>
`

export { getChatBotAreaTemplate, getLoginAreaTemplate, getRegisterAreaTemplate }