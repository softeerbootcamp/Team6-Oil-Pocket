const getInputOilInfoTemplate = () => `
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
                        <div class="oilInfoArea__selectOil">
                            <div class="oilInfoArea__oilValue">휘발유</div>
                            <div class="oilInfoArea__oilValue">경유</div>
                            <div class="oilInfoArea__oilValue">LPG</div>
                            <div class="oilInfoArea__oilSelect">
                                <span>기름 종류</span>
                                <img src="../../img/dropDownBtn.png">
                            </div>
                        </div>
                        <input class="oilInfoArea__oilPriceInput" type="number" maxlength=4 placeholder="주유금액">
                        <div class="oilInfoArea__oilPrice">
                            <span>주유 금액이 입력되지 않았습니다.</span>
                        </div>
                        <input type="text" placeholder="주유소">
                    </div>
                    <div class="oilInfoArea__effectBox">
                        <div class="oilInfoArea__effectBoxBackGround">
                            <svg xmlns="http://www.w3.org/2000/svg" width="50%" height="50%" viewBox="0 0 1069 1464" fill="none">
                            <mask id="mask0_480_824" style="mask-type:alpha" maskUnits="userSpaceOnUse" x="0" y="0" width="1069" height="1464">
                            <path d="M534.06 0L473.533 60.5267C453.951 80.1089 0 539.4 0 929.264C0 1223 240.327 1463.32 534.06 1463.32C827.792 1463.32 1068.12 1223 1068.12 929.264C1068.12 541.18 614.168 80.1089 594.586 60.5267L534.06 0ZM267.03 840.254C316.875 840.254 356.04 879.418 356.04 929.264C356.04 1027.17 436.149 1107.28 534.06 1107.28C583.905 1107.28 623.069 1146.45 623.069 1196.29C623.069 1246.14 583.905 1285.3 534.06 1285.3C338.238 1285.3 178.02 1125.09 178.02 929.264C178.02 879.418 217.184 840.254 267.03 840.254Z" fill="white"/>
                            </mask>
                            <g mask="url(#mask0_480_824)">
                                <rect x="0" y="0" width="1100" height="1500" fill="#fff" stroke="#000" stroke-width="1px"/>
                            </g>
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="50%" height="50%" viewBox="0 0 1069 1464" fill="none">
                            <mask id="mask0_480_824" style="mask-type:alpha" maskUnits="userSpaceOnUse" x="0" y="0" width="1069" height="1464">
                            <path d="M534.06 0L473.533 60.5267C453.951 80.1089 0 539.4 0 929.264C0 1223 240.327 1463.32 534.06 1463.32C827.792 1463.32 1068.12 1223 1068.12 929.264C1068.12 541.18 614.168 80.1089 594.586 60.5267L534.06 0ZM267.03 840.254C316.875 840.254 356.04 879.418 356.04 929.264C356.04 1027.17 436.149 1107.28 534.06 1107.28C583.905 1107.28 623.069 1146.45 623.069 1196.29C623.069 1246.14 583.905 1285.3 534.06 1285.3C338.238 1285.3 178.02 1125.09 178.02 929.264C178.02 879.418 217.184 840.254 267.03 840.254Z" fill="white"/>
                            </mask>
                            <g mask="url(#mask0_480_824)">
                                <rect class="oilInfoArea__effectImage" x="0" y="1500" width="1100" height="1500" fill="#14BD7E" stroke="#000" stroke-width="1px"/>
                            </g>
                            </svg>
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

export { getInputOilInfoTemplate }