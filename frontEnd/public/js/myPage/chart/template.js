const tabObjArray = [
    {choosed: false, text: "프로필 수정", path: "/userDetail"},
    {choosed: false, text: "주유 기록 입력", path: "/inputOilInfo"},
    {choosed: false, text: "이번 달 분석", path: "/comparison"},
    {choosed: true, text: "월별 비교", path: "/chart"},
    {choosed: false, text: "주유 기록 열람", path: "/history"}
];

const chartIDs = [
    "Month--01", "Month--02", "Month--03", "Month--04", "Month--05", 
    "Month--06", "Month--07", "Month--08", "Month--09", "Month--10"
];

const monthNumbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

const getChartTemplate = () => `
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
                <div class="oilInfoArea__oilChartContainer">
                    <div class="oilInfoArea__oilCharttitle">
                        <h2>월별 주유 정보</h2>
                        <div class="oilInfoArea__oilChartInfo">
                            <div class="oilInfoArea__oilChartInfo--common">
                                <div class="oilInfoArea__legend--common">&nbsp;</div>
                                <span>평균 주유 금액</span>
                            </div>
                            <div class="oilInfoArea__oilChartInfo--user">
                                <div class="oilInfoArea__legend--user">&nbsp;</div>
                                <span>사용자 주유 금액</span>
                            </div>
                        </div>
                    </div>
                    <div class="oilInfoArea__oilChartArea">
                        <div class="oilInfoArea__chartZone">
                            ${chartIDs.map((chartID) => `
                                <div class="oilInfoArea__chart" id=${chartID}>
                                    <div class="oilInfoArea__averageChart"><span></span></div>
                                    <div class="oilInfoArea__myChart"><span></span></div>
                                </div>
                            `).join("")}
                        </div>
                        <div class="oilInfoArea__monthZone">
                            ${monthNumbers.map((monthNumber) => `<span>${monthNumber}월</span>`).join("")}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

export { getChartTemplate }