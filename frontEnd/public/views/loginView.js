export const loginView = `
    <section class="loginArea">
        <section class="loginArea__backGround">
            <div class="loginArea__frame">
                <h1>Oil Pocket</h1>
                <input type="text" placeholder="ID를 입력해주세요.">
                <input type="text" placeholder="PW를 입력해주세요.">
                <button>로그인</button>
                <label for="loginArea__registerBtn">
                    <span>새로운 주행자이신가요?</span>
                    <button id="loginArea__registerBtn"><a href="/frontEnd/register" data-link>회원가입</a></button>
                </label>
            </div>
        </section>
    </section>
`;