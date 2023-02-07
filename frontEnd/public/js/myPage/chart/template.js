const getChartTemplate = () => `
    <section class="oilInfoArea">
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                <div class="oilInfoArea__tab"><a href="/userDetail" data-link>프로필 수정</a></div>
                <div class="oilInfoArea__tab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                <div class="oilInfoArea__tab"><a href="/comparison" data-link>이번달 비교</a></div>
                <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/chart" data-link>월별 비교</a></div>
                <div class="oilInfoArea__tab"><a href="/history" data-link>주유 기록 열람</a></div>
            </div>
            <div class="oilInfoArea__contentArea">
                <div class="oilInfoArea__oilChartContainer">
                    <div class="oilInfoArea__oilCharttitle">
                        <h2>이번 달 주유 정보</h2>
                    </div>
                    <div class="oilInfoArea__oilChartArea">
                        <div class="oilInfoArea__chartZone">
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                            <div class="oilInfoArea__chart" id="Month--01">
                                <div class="oilInfoArea__averageChart"></div>
                                <div class="oilInfoArea__myChart"></div>
                            </div>
                        </div>
                        <div class="oilInfoArea__monthZone">
                            <span>1월</span>
                            <span>2월</span>
                            <span>3월</span>
                            <span>4월</span>
                            <span>5월</span>
                            <span>6월</span>
                            <span>7월</span>
                            <span>8월</span>
                            <span>9월</span>
                            <span>10월</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

export { getChartTemplate }