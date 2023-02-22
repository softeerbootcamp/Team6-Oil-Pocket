import { userId, userAge, userGender } from "../../login/fetch";

const tabObjArray = [
    {choosed: true, text: "프로필 수정", path: "/userDetail"},
    {choosed: false, text: "주유 입력", path: "/inputOilInfo"},
    {choosed: false, text: "이번 달 분석", path: "/comparison"},
    {choosed: false, text: "평균 유가 비교", path: "/chart"},
    {choosed: false, text: "주유 기록 열람", path: "/history"}
];

const ageTextArray = ["20대", "30대", "40대", "50대", "60대 이상"];

const getUserDetailTemplate = () => `
    <section class="oilInfoArea">
        <div class="myPage__SuccessModal">
            프로필이 수정되었습니다.
        </div>
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                ${tabObjArray.map(({choosed, text, path}) => `
                    <div class="oilInfoArea__tab ${choosed ? "oilInfoArea__choosedTab" : ""}">
                        <a href=${path} data-link>${text}</a>
                    </div>
                `).join("")}
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
                                ${ageTextArray.map((ageText) => `
                                    <input type="radio" name="age" value=${ageText} id="radioAge--${parseInt(ageText)}" ${userAge === ageText ? "checked" : ""}>
                                    <label for="radioAge--${parseInt(ageText)}">${ageText}</label>
                                `).join("")}
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