import { changeCSS, isReleaseMode, _$ } from "../../common/utils.js";
import { BASE_COOKIE_URL, HEADER, METHOD, RELEASE_COOKIE_URL } from "../../common/variable.js";
import { gasNameMapper, validateOilInput } from "./helperFunction.js";
import { gasStationSearchView, recentGasStationView } from "./view.js";

const fetchGasStationSearch = ($oilSearchResultBox, gasStationName, gasType) => {
    const FETCH_URL = isReleaseMode() ? 
                        `${RELEASE_COOKIE_URL}/gas-station/?name=${gasStationName}&gasType=${gasNameMapper(gasType)}` :
                        `${BASE_COOKIE_URL}/gas-station/?name=${gasStationName}&gasType=${gasNameMapper(gasType)}`;

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
            gasStationSearchView(info["name"], info["address"], info["stationNo"], info["brand"])
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
        }).then((res) => {
            if(res.status === 201) {
                // 성공 모달
                const $successModal = _$(".oilInput__successModal", $container);
                changeCSS($successModal, "top", 0);
                setTimeout(() => {
                    changeCSS($successModal, "top", "-30%")
                }, 1000);
                setTimeout(() => {
                    location.assign("/inputOilInfo");
                }, 1800);
            }
        })
    }
    else {
        // error modal
    }
}

const fetchRecentGasStation = ($container) => {
    const $oilSelect = _$(".oilInfoArea__oilSelect > span", $container);
    const $oilRecentModalContent = _$(".oilInput__preferContentBox", $container);

    let gasType = gasNameMapper($oilSelect.innerHTML);

    const FETCH_URL = isReleaseMode() ? 
                        RELEASE_COOKIE_URL + `/gas-station/recent/?gasType=${gasType}` :
                        BASE_COOKIE_URL + `/gas-station/recent/?gasType=${gasType}`;

    fetch(FETCH_URL, {
        headers: HEADER.GET,
        credentials: "include"
    }).then((res) => {
        $oilRecentModalContent.innerHTML = ``;
        if(res.status === 200) {
            return res.json();
        }
        return {};
    }).then(({data}) => {
        if(data) {
            data.forEach(({address, name, brand}) => {
                $oilRecentModalContent.appendChild(recentGasStationView(brand, name, address));
            })
        }
    })
}

export { fetchGasStationSearch, fetchOilRegister, fetchRecentGasStation }