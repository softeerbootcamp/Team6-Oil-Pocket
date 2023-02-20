const getLoginTemplate = () => `
    <section class="loginArea__backGround">
        <div class="loginArea__errorModal">
            로그인에 실패하였습니다.
        </div>
        <div class="loginArea__frame">
            <h1><a href="/" data-link>Oil Pocket</a></h1>
            <input type="text" placeholder="ID를 입력해주세요." class="loginArea__IDInput" value="team6">
            <input type="password" placeholder="PW를 입력해주세요." class="loginArea__PWInput" value="team6">
            <button id="loginArea__loginBtn">로그인</button>
            <label for="loginArea__registerBtn">
                <span>새로운 주행자이신가요?</span>
                <button id="loginArea__registerBtn"><a href="/register" data-link>회원가입</a></button>
            </label>
        </div>
    </section>
`;
export { getLoginTemplate }