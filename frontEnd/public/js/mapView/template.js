const getMapViewContentTemplate = () => `
    <div class="main__ForMap">
        <div class="main__SideSearchBar">
            <div class="main__SearchOption">
                <button class="main__SearchFromCurrentLocation">
                    <img id="Option1" src="svg_image/🦆 icon _pin outline_.svg">
                </button>
                <button class="main__SearchFromRoute">
                    <img id="Option2" src="svg_image/🦆 icon _search outline_.svg">
                </button>
            </div>
            <div class="main__InputandResultinSearchBar">
                <div class="main__ShowCurrentLocation">
                    <div class="main__CurrentLocation">
                        <span>현재위치</span>
                    </div>
                    <div class="main__CurrentLocationAddress">
                    </div>
                    <div class="main__SearchOptionButtonGroup">
                        <button id="sort_HH" onclick = "sortHhPrice()">휘발유</button>
                        <button id="sort_GG" onclick = "sortGgPrice()">경유</button>
                        <button id="sort_LL" onclick = "sortLlPrice()">엘피지</button>
                    </div>
                </div>
                <div class="main__SearchResult">
                    <div class="main__ResultList">
                    </div>
                </div>
            </div>
        </div>
        <button class="main__SearchBarHideButton">
            <img src="svg_image/🦆 icon _chevron left_.svg">
        </button>
        <div class="main__MapDiv">
            <div id="map_div" class="map_wrap" style="float:left"></div>
        </div>
        <button id="main__btn_select">적용하기</button>
    </div>
`;

export { getMapViewContentTemplate }