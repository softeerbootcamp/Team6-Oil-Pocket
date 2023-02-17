import { _$, _$_ALL, changeCSS, parseNumberToMoneyString } from "../../common/function.js";
import { monthArray } from "./view.js";

NodeList.prototype.forEach = Array.prototype.forEach;
const OIL_UPPER_PRICE = 500_000;

const makeChart = ($container, userOilArray, commonOilArray, monthArray) => {
    const $charts = _$_ALL(".oilInfoArea__chart", $container);    
    const $months = _$_ALL(".oilInfoArea__monthZone > span", $container);

    $charts.forEach(($chart, index) => {
        const $userPrice = _$(".oilInfoArea__myChart", $chart);
        const $averagePrice = _$(".oilInfoArea__averageChart", $chart);
        const $userPriceText = _$("span", $userPrice);
        const $averageText = _$("span", $averagePrice);

        $userPriceText.innerHTML = parseNumberToMoneyString(userOilArray[index]);
        $averageText.innerHTML = parseNumberToMoneyString(commonOilArray[index]);
        $months[index].innerHTML = monthArray[index] + "월";

        let userPercent = userOilArray[index] / OIL_UPPER_PRICE;
        let averagePercent = commonOilArray[index] / OIL_UPPER_PRICE;

        userPercent = userPercent >= 1 ? 100 : userPercent * 100;
        averagePercent = averagePercent >= 1 ? 100 : averagePercent * 100;

        setTimeout(() => {
            changeCSS($userPrice, "height", `${userPercent}%`);
            changeCSS($averagePrice, "height", `${averagePercent}%`);
        }, 50 * index);
    })
}

const getMonthNumberFromDate = (dateString) => parseInt(dateString.split(".")[1]);

const makeMonthArray = (lastMonth) => {
    let startMonth = lastMonth + 3;
    for(let i=0;i<10;i++, startMonth++) {
        if(startMonth > 12) {
            startMonth = 1;
        }
        monthArray[i] = startMonth;
    }
}

export { makeChart, getMonthNumberFromDate, makeMonthArray }