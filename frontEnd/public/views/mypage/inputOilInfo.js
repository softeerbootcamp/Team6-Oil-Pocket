import { navBarView } from "../navBarView";

const inputOilInfoView = () => {
    const $inputOilInputContainer = document.createElement("section");
    const $inputOilInputContent = document.createElement("section");
    $inputOilInputContent.classList.add("main");

    $inputOilInputContent.innerHTML = `
        <section class="oilInfoArea">
            <section class="oilInfoArea__background">
                <div class="oilInfoArea__tabArea">
                    <div class="oilInfoArea__tab"><a href="/userDetail" data-link>프로필 수정</a></div>
                    <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                    <div class="oilInfoArea__tab"><a href="/comparison" data-link>이번달 비교</a></div>
                    <div class="oilInfoArea__tab"><a href="/chart" data-link>월별 비교</a></div>
                    <div class="oilInfoArea__tab"><a href="/history" data-link>주유 기록 열람</a></div>
                </div>
                <div class="oilInfoArea__contentArea">
                    <h1 class="oilInfoArea__contentTitle">
                        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 48" fill="none">
                            <path d="M17.5 0L15.5167 1.98333C14.875 2.625 0 17.675 0 30.45C0 40.075 7.875 47.95 17.5 47.95C27.125 47.95 35 40.075 35 30.45C35 17.7333 20.125 2.625 19.4833 1.98333L17.5 0ZM8.75 27.5333C10.3833 27.5333 11.6667 28.8167 11.6667 30.45C11.6667 33.6583 14.2917 36.2833 17.5 36.2833C19.1333 36.2833 20.4167 37.5667 20.4167 39.2C20.4167 40.8333 19.1333 42.1167 17.5 42.1167C11.0833 42.1167 5.83333 36.8667 5.83333 30.45C5.83333 28.8167 7.11667 27.5333 8.75 27.5333Z" fill="#14BD7E"/>
                        </svg>
                        <span>주유 입력</span>
                    </h1>
                    <div class="oilInfoArea__contentBody">
                        <div class="oilInfoArea__inputBox">
                            <input type="text" placeholder="기름 종류">
                            <input type="text" placeholder="주유금액">
                            <input type="text" placeholder="주유소">
                        </div>
                        <div class="oilInfoArea__effectBox">
                            <div class="oilInfoArea__effectBoxBackGround">
                                <span>효과 넣기</span>
                            </div>
                        </div>
                    </div>
                    <div class="oilInfoArea__buttonArea">
                        <button class="oilInfoArea__cancelBtn">취소</button>
                        <button class="oilInfoArea__registerBtn">등록</button>
                    </div>
                </div>
            </section>
        </section>
    `;

    $inputOilInputContainer.appendChild(navBarView());
    $inputOilInputContainer.appendChild($inputOilInputContent);

    return $inputOilInputContainer;
}

export { inputOilInfoView }