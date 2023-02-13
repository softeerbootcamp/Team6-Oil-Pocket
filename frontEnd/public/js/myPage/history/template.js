const getHistoryTemplate = () => `
    <section class="oilInfoArea">
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                <div class="oilInfoArea__tab"><a href="/userDetail" data-link>프로필 수정</a></div>
                <div class="oilInfoArea__tab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                <div class="oilInfoArea__tab"><a href="/comparison" data-link>이번 달 분석</a></div>
                <div class="oilInfoArea__tab"><a href="/chart" data-link>월별 비교</a></div>
                <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/history" data-link>주유 기록 열람</a></div>
            </div>
            <div class="oilInfoArea__contentArea">
                <div class="oilInfoArea__oilHistoryContainer">
                    <div class="oilInfoArea__oilHistorytitle">
                        <h2>이번 달 주유 기록</h2>
                    </div>
                    <div class="oilInfoArea__oilHistoryTable">
                        <div>
                            <div>
                                <span>2022.02.01</span>
                                <span><img src="./public/img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                                <span>유진 주유소</span>
                                <span>휘발유 30L</span>
                                <span>42,000원</span>
                                <span>-3,000원</span>
                            </div>
                            <div>
                                <span>2022.02.01</span>
                                <span><img src="/public/img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                                <span>유진 주유소</span>
                                <span>휘발유 30L</span>
                                <span>42,000원</span>
                                <span>+3,000원</span>
                            </div>
                            <div>
                                <span>2022.02.01</span>
                                <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                                <span>유진 주유소</span>
                                <span>휘발유 30L</span>
                                <span>42,000원</span>
                                <span>-3,000원</span>
                            </div>
                            <div>
                                <span>2022.02.01</span>
                                <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                                <span>유진 주유소</span>
                                <span>휘발유 30L</span>
                                <span>42,000원</span>
                                <span>-3,000원</span>
                            </div><div>
                            <span>2022.02.01</span>
                            <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                            <span>유진 주유소</span>
                            <span>휘발유 30L</span>
                            <span>42,000원</span>
                            <span>-3,000원</span>
                        </div><div>
                        <span>2022.02.01</span>
                        <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                        <span>유진 주유소</span>
                        <span>휘발유 30L</span>
                        <span>42,000원</span>
                        <span>-3,000원</span>
                    </div><div>
                    <span>2022.02.01</span>
                    <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                    <span>유진 주유소</span>
                    <span>휘발유 30L</span>
                    <span>42,000원</span>
                    <span>-3,000원</span>
                </div><div>
                <span>2022.02.01</span>
                <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
                <span>유진 주유소</span>
                <span>휘발유 30L</span>
                <span>42,000원</span>
                <span>-3,000원</span>
            </div><div>
            <span>2022.02.01</span>
            <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
            <span>유진 주유소</span>
            <span>휘발유 30L</span>
            <span>42,000원</span>
            <span>-3,000원</span>
        </div><div>
        <span>2022.02.01</span>
        <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
        <span>유진 주유소</span>
        <span>휘발유 30L</span>
        <span>42,000원</span>
        <span>-3,000원</span>
    </div><div>
    <span>2022.02.01</span>
    <span><img src="../img/Mask group.png" alt="주유소 브랜드 이미지"></span>
    <span>유진 주유소</span>
    <span>휘발유 30L</span>
    <span>42,000원</span>
    <span>-3,000원</span>
</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

export { getHistoryTemplate }