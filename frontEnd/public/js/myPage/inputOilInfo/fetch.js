import { _$ } from "../../common/function.js";
import { BASE_COOKIE_URL, HEADER, METHOD } from "../../common/variable.js";
import { gasNameMapper, validateGasName, validateGasPrice, validateOilInput, validateStationName } from "./helperFunction.js";
import { gasStationSearchView } from "./view.js";

const fetchGasStationSearch = ($oilSearchResultBox, gasStationName) => {
    fetch(`${BASE_COOKIE_URL}/gas-station/?name=${gasStationName}`, {
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

    if(validateOilInput($oilText, $oilPriceText, $gasStationText)) {
        fetch(BASE_COOKIE_URL + "/user/gas-record", {
            method: METHOD.POST,
            headers: HEADER.POST,
            // mode: 'cors',
            body: JSON.stringify({
                gasType: `${gasNameMapper($oilText.innerHTML)}`,
                "refuelingPrice": $oilPriceText.value,
                "gasStationNo": $gasStationText.dataset.stationNo
            }),
            credentials: "include"
        }).then((res) => {
            console.log(res);
        })
    }
    else {
        // error modal
    }
}

export { fetchGasStationSearch, fetchOilRegister }