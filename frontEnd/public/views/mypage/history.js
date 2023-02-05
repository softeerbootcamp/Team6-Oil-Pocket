import { navBarView } from "../../navbar/view";

const historyView = () => {
    const $historyContainer = document.createElement("section");
    const $historyContent = document.createElement("section");
    $historyContent.classList.add("main");

    $historyContent.innerHTML = `
        <section class="oilInfoArea">
            <section class="oilInfoArea__background">
                <div class="oilInfoArea__tabArea">
                    <div class="oilInfoArea__tab"><a href="/userDetail" data-link>프로필 수정</a></div>
                    <div class="oilInfoArea__tab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                    <div class="oilInfoArea__tab"><a href="/comparison" data-link>이번달 비교</a></div>
                    <div class="oilInfoArea__tab"><a href="/chart" data-link>월별 비교</a></div>
                    <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/history" data-link>주유 기록 열람</a></div>
                </div>
                <div class="oilInfoArea__contentArea">
                    <div class="oilInfoArea__oilHistoryContainer">
                        <div class="oilInfoArea__oilHistorytitle">
                            <h2>이번 달 주유 기록</h2>
                        </div>
                        <table class="oilInfoArea__oilHistoryTable">
                            <tbody>
                                <tr>
                                    <td>2022.02.01</td>
                                    <td><img src="./public/img/Mask group.png" alt="주유소 브랜드 이미지"></td>
                                    <td>유진 주유소</td>
                                    <td>휘발유 30L</td>
                                    <td>42,000원</td>
                                    <td>-3,000원</td>
                                </tr>
                                <tr>
                                    <td>2022.02.01</td>
                                    <td><img src="/public/img/Mask group.png" alt="주유소 브랜드 이미지"></td>
                                    <td>유진 주유소</td>
                                    <td>휘발유 30L</td>
                                    <td>42,000원</td>
                                    <td>+3,000원</td>
                                </tr>
                                <tr>
                                    <td>2022.02.01</td>
                                    <td><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></td>
                                    <td>유진 주유소</td>
                                    <td>휘발유 30L</td>
                                    <td>42,000원</td>
                                    <td>-3,000원</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </section>
    `;

    $historyContainer.appendChild(navBarView());
    $historyContainer.appendChild($historyContent);

    return $historyContainer;
}

export { historyView }