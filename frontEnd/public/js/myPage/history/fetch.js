import { isReleaseMode, _$ } from "../../common/utils.js";
import { BASE_COOKIE_URL, METHOD, RELEASE_COOKIE_URL } from "../../common/variable.js";
import { historyRowView } from "./view.js";

const fetchOilHistory = async ($container) => {
    const FETCH_URL = isReleaseMode() ? 
                        RELEASE_COOKIE_URL + "/user/gas-record" : 
                        BASE_COOKIE_URL + "/user/gas-record";

    await fetch(FETCH_URL, {
        method: METHOD.GET,
        credentials: "include"
    }).then((res) => {
        if(res.status === 200) {
            return res.json();
        }
        else {
            return {};
        }
    }).then(({data}) => {
        const $historyTable = _$(".oilInfoArea__oilHistoryTable ", $container);

        const oilHistoryArray = [...data];
        oilHistoryArray.forEach(({ chargeDate, gasStationName, gasType, recordGasAmount, refuelingPrice, savingPrice, brand }) => {
            $historyTable.appendChild(historyRowView(
                chargeDate, brand, gasStationName, gasType, recordGasAmount, refuelingPrice, savingPrice
            ));
        })
    })
}

export { fetchOilHistory }