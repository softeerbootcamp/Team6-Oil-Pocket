import { _$, _$_ALL, changeCSS, parseNumberToMoneyString } from "../../common/function.js";

NodeList.prototype.forEach = Array.prototype.forEach;
const OIL_UPPER_PRICE = 200_000;

const makeChart = ($container, userOilArray, commonOilArray) => {
    const $charts = _$_ALL(".oilInfoArea__chart", $container);    

    $charts.forEach(($chart, index) => {
        const $userPrice = _$(".oilInfoArea__myChart", $chart);
        const $averagePrice = _$(".oilInfoArea__averageChart", $chart);
        const $userPriceText = _$("span", $userPrice);
        const $averageText = _$("span", $averagePrice);

        $userPriceText.innerHTML = parseNumberToMoneyString(userOilArray[index]);
        $averageText.innerHTML = parseNumberToMoneyString(commonOilArray[index]);

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

export { makeChart, getMonthNumberFromDate }