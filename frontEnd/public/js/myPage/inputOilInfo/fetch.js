import { isReleaseMode, _$ } from "../../common/function.js";
import { BASE_COOKIE_URL, HEADER, METHOD, RELEASE_COOKIE_URL } from "../../common/variable.js";
import { gasNameMapper, validateOilInput } from "./helperFunction.js";
import { gasStationSearchView } from "./view.js";

const fetchGasStationSearch = ($oilSearchResultBox, gasStationName) => {
    const FETCH_URL = isReleaseMode() ? 
                        `${RELEASE_COOKIE_URL}/gas-station/?name=${gasStationName}` :
                        `${BASE_COOKIE_URL}/gas-station/?name=${gasStationName}`;

    fetch(FETCH_URL, {
        method: METHOD.GET,
        credentials: "include",
    }).then((res) => {
        if(res.status === 200) {
            return res.json();
        }
    }).then(({data}) => {
        const InfoArray = [...data];
        $oilSearchResultBox.innerHTML = ``;
        InfoArray.forEach((info) => $oilSearchResultBox.appendChild(
            gasStationSearchView(info["name"], info["address"], info["stationNo"])
        ));
    })
}

const fetchOilRegister = ($container) => {
    const $oilText = _$(".oilInfoArea__oilSelect > span", $container);
    const $oilPriceText = _$(".oilInfoArea__oilPriceInput", $container);
    const $gasStationText = _$(".oilInfoArea__searchInput", $container);
    const FETCH_URL = isReleaseMode() ? 
                        RELEASE_COOKIE_URL + "/user/gas-record" :
                        BASE_COOKIE_URL + "/user/gas-record";


    if(validateOilInput($oilText, $oilPriceText, $gasStationText)) {
        fetch(FETCH_URL, {
            method: METHOD.POST,
            headers: HEADER.POST,
            body: JSON.stringify({
                gasType: `${gasNameMapper($oilText.innerHTML)}`,
                "refuelingPrice": $oilPriceText.value,
                "gasStationNo": $gasStationText.dataset.stationNo
            }),
            credentials: "include"
        })
    }
    else {
        // error modal
    }
}

export { fetchGasStationSearch, fetchOilRegister }