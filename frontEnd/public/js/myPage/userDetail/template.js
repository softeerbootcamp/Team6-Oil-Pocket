import { userId, userAge, userGender } from "../../login/fetch";

const getUserDetailTemplate = () => `
    <section class="oilInfoArea">
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/userDetail" data-link>프로필 수정</a></div>
                <div class="oilInfoArea__tab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                <div class="oilInfoArea__tab"><a href="/comparison" data-link>이번 달 분석</a></div>
                <div class="oilInfoArea__tab"><a href="/chart" data-link>월별 비교</a></div>
                <div class="oilInfoArea__tab"><a href="/history" data-link>주유 기록 열람</a></div>
            </div>
            <div class="oilInfoArea__contentArea">
                <div class="oilInfoArea__profileContainer">
                    <div class="oilInfoArea__profileTitleBox">
                        <h2>주행자</h2>
                        <span>${userId ? userId : ""}</span>
                    </div>
                    <div class="oilInfoArea__profileContentBox">
                        <div class="oilInfoArea__profileContentBox--gender">
                            <h2>성별</h2>
                            <div class="oilInfoArea__inputBoxArea--gender">
                                <input type="radio" name="gender" value="남자" id="radio--male" ${userGender === "M" ? "checked" : ""}>
                                <label for="radio--male">남자</label>
                                <input type="radio" name="gender" value="여자" id="radio--female" ${userGender === "F" ? "checked" : ""}>
                                <label for="radio--female">여자</label>
                            </div>
                        </div>
                        <div class="oilInfoArea__profileContentBox--age">
                            <h2>나이</h2>
                            <div class="oilInfoArea__inputBoxArea--age">
                                <input type="radio" name="age" value="20대" id="radioAge--20" ${userAge === "20대" ? "checked" : ""}>
                                <label for="radioAge--20">20대</label>
                                <input type="radio" name="age" value="30대" id="radioAge--30" ${userAge === "30대" ? "checked" : ""}>
                                <label for="radioAge--30">30대</label>
                                <input type="radio" name="age" value="40대" id="radioAge--40" ${userAge === "40대" ? "checked" : ""}>
                                <label for="radioAge--40">40대</label>
                                <input type="radio" name="age" value="50대" id="radioAge--50" ${userAge === "50대" ? "checked" : ""}>
                                <label for="radioAge--50">50대</label>
                                <input type="radio" name="age" value="60대 이상" id="radioAge--60" ${userAge === "60대 이상" ? "checked" : ""}>
                                <label for="radioAge--60">60대 이상</label>
                            </div>
                        </div>
                    </div>
                    <div class="oilInfoArea__profileBtnBox">
                        <button class="oilInfoArea__profileBtn--cancel">취소</button>
                        <button class="oilInfoArea__profileBtn--register">적용</button>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

export { getUserDetailTemplate }