const getRegisterTemplate = () => `
    <section class="registerArea">
        <section class="registerArea__backGround">
            <div class="registerArea__frame">
                <h1><a href="/">Oil Pocket</a></h1>
                <div class="registerArea__termOfUse">
                    <h2 class="registerArea__termOfUseTitle">이용 약관</h2>
                    <p class="registerArea__termOfUseContent">
                        제 1장 총칙제 1 조(목적)본 약관은 국가공간정보포털 웹사이트(이하 "국가공간정보포털")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 회원과 국가공간정보포털의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다. <br><br>
                        제 2 조(약관의 효력과 변경)1. 국가공간정보포털은 이용자가 본 약관 내용에 동의하는 경우, 국가공간정보포털의 서비스 제공 행위 및 회원의 서비스 사용 행위에 본 약관이 우선적으로 적용됩니다.2. 국가공간정보포털은 약관을 개정할 경우, 적용일자 및 개정사유를 명시하여 현행약관과 함께 국가공간정보포털의 초기화면에 그 적용일 7일 이전부터 적용 전일까지 공지합니다. 단, 회원에 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 국가공간정보포털은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 회원이 알기 쉽도록 표시합니다.3. 변경된 약관은 국가공간정보포털 홈페이지에 공지하거나 e-mail을 통해 회원에게 공지하며, 약관의 부칙에 명시된 날부터 그 효력이 발생됩니다. 고 국가공간정보포털에서 부여하는 문자와 숫자의 조합5. 비밀번호 : 회원과 계정이 일치하는지를 확인하고 통신상의 자신의 비밀보호를 위하여 회
                    </p>
                    <div class="registerArea__btnBox">
                        <button class="registerArea__termOfUseContentBtn">위 사항에 동의합니다</button>
                    </div>
                </div>
                <div class="registerArea__dropDownArea">
                    <div class="registerArea__genderArea">
                        <div class="registerArea__genderValue">남자</div>
                        <div class="registerArea__genderValue">여자</div>
                        <div class="registerArea__genderTitle">성별</div>
                    </div>
                    <div class="registerArea__ageArea">
                        <div class="registerArea__ageValue">20대</div>
                        <div class="registerArea__ageValue">30대</div>
                        <div class="registerArea__ageValue">40대</div>
                        <div class="registerArea__ageValue">50대</div>
                        <div class="registerArea__ageValue">60대 이상</div>
                        <div class="registerArea__ageTitle">나이</div>
                    </div>
                </div>
                <div class="registerArea__IDArea">
                    <input type="text" placeholder="ID를 입력해주세요." minlength="6" maxlength="20">
                    <button class="IDValidateBtn">중복 확인</button>          
                </div>
                <input type="password" minlength="6" maxlength="12" placeholder="PW를 입력해주세요." id="registerArea__pwInput">
                <input type="password" minlength="6" maxlength="12" placeholder="PW를 다시 입력해주세요." id="registerArea__pwReInput">
                <button class="registerBtn" disabled>회원가입</button>
            </div>
        </section>
    </section>
`;

export { getRegisterTemplate }