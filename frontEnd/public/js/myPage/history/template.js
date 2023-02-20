const tabObjArray = [
    {choosed: false, text: "프로필 수정", path: "/userDetail"},
    {choosed: false, text: "주유 기록 입력", path: "/inputOilInfo"},
    {choosed: false, text: "이번 달 분석", path: "/comparison"},
    {choosed: false, text: "월별 비교", path: "/chart"},
    {choosed: true, text: "주유 기록 열람", path: "/history"}
];

const getHistoryTemplate = () => `
    <section class="oilInfoArea">
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                ${tabObjArray.map(({choosed, text, path}) => `
                    <div class="oilInfoArea__tab ${choosed ? "oilInfoArea__choosedTab" : ""}">
                        <a href=${path} data-link>${text}</a>
                    </div>
                `).join("")}
            </div>
            <div class="oilInfoArea__contentArea">
                <div class="oilInfoArea__oilHistoryContainer">
                    <div class="oilInfoArea__oilHistorytitle">
                        <h2>이번 달 주유 기록</h2>
                    </div>
                    <div class="oilInfoArea__oilHistoryTable">
                        <div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

const getHistoryRowTemplate = (date, imgSrc, gasStationName, gasType, gasAmount, spendPrice, savePrice) => `
    <div>
        <span>${date}</span>
        <span><img src="${imgSrc}" alt="주유소 브랜드 이미지"></span>
        <span>${gasStationName}</span>
        <span>${gasType} ${gasAmount}</span>
        <span>${spendPrice}</span>
        <span>${savePrice}</span>
    </div>
`;

export { getHistoryTemplate, getHistoryRowTemplate }