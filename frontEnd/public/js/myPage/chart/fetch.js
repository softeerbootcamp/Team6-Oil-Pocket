import { isReleaseMode } from "../../common/function"
import { BASE_COOKIE_URL, METHOD, RELEASE_COOKIE_URL } from "../../common/variable"
import { getMonthNumberFromDate } from "./helperFunction";
import { userOilArray, commonOilArray } from "./view";

const fetchChart = async () => {
    const FETCH_URL = isReleaseMode() ? 
                        RELEASE_COOKIE_URL + "/user/gas-record/month" :
                        BASE_COOKIE_URL + "/user/gas-record/month";

    await fetch(FETCH_URL, {
        method:METHOD.GET,
        credentials: "include"
    }).then((res) => {
        if(res.status === 200) {
            return res.json();
        }
        else {
            return {};
        }
    }).then(({data}) => {
        const dataArray = [...data];

        console.log(dataArray)

        dataArray.forEach(({monthDate, totalRefuelingPrice, totalNationalAvgPrice}) => {
            const month = getMonthNumberFromDate(monthDate);
            userOilArray[month - 1] = totalRefuelingPrice;
            commonOilArray[month - 1] = totalNationalAvgPrice;
        })
    })
}

export { fetchChart }