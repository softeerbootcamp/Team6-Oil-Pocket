const gasStationDetailTemplate = () => `
<section class="gasDetailContainer">
        <section class="gasDetailContainer__background">
            <section class="gasDetailContainer__infoArea">
                <div class="gasDetailContainer__brandInfoCard">
                    <div class="gasDetailContainer__brandInfoBox">
                        <div class="gasDetailContainer__imageBox">
                            <img src="../../img/Mask group.png" alt="주유소 브랜드">
                        </div>
                        <div class="gasDetailContainer__textBox">
                            <span id="brandName">유진 주유소</span>
                            <span id="brandNumber">02-521-3908</span>
                            <span id="brandAddress">서울시 송파구 도곡로 438 (잠실동)</span>
                        </div>
                    </div>
                    <div class="gasDetailContainer__btnBox">
                        <button>찾아가기</button>
                        <button>관심 등록</button>
                        <button>주유 입력</button>
                    </div>
                </div>
                <div class="gasDetailContainer__gasPriceTable">
                    <div class="gasDetailContainer__gasPriceHeader">유가 정보</div>
                    <div class="gasDetailContainer__gasPriceBody">
                        <div class="gasDetailContainer__gasolineBox">
                            <span>휘발유</span>
                            <span>1,777원</span>
                            <span>2023.02.03</span>
                        </div>
                        <div class="gasDetailContainer__dieselBox">
                            <span>경유</span>
                            <span>1,777원</span>
                            <span>2023.02.03</span>
                        </div>
                    </div>
                </div>
            </section>
            <section class="gasDetailContainer__chartArea">
                <div class="gasDetailContainer__chartBox"></div>
            </section>
        </section>
    </section>
`;

export { gasStationDetailTemplate }