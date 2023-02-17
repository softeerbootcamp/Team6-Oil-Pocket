import { isReleaseMode } from "../../common/function"
import { BASE_COOKIE_URL, METHOD, RELEASE_COOKIE_URL } from "../../common/variable"
import { getMonthNumberFromDate, makeMonthArray } from "./helperFunction";
import { userOilArray, commonOilArray, monthArray } from "./view";

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
        if(data.length) {
            const dataArray = [...data];

            dataArray.forEach(({monthDate, totalRefuelingPrice, totalNationalAvgPrice}, index) => {
                const month = getMonthNumberFromDate(monthDate);
                userOilArray[userOilArray.length - 1 - index] = totalRefuelingPrice;
                commonOilArray[commonOilArray.length - 1 - index] = totalNationalAvgPrice;

                if(index === 0 ) {
                    makeMonthArray(month);
                }
            })
        } else {
            for(let i=1;i<=10;i++) {
                monthArray[i-1] = i;
            }
        }
    })
}

export { fetchChart }