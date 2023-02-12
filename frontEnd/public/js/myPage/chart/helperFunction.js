import { changeCSS, parseNumberToMoneyString } from "../../common/function";

NodeList.prototype.forEach = Array.prototype.forEach;
const OIL_UPPER_PRICE = 200_000;

const makeChart = ($container, userOilArray, commonOilArray) => {
    const $charts = $container.querySelectorAll(".oilInfoArea__chart");
    $charts.forEach(($chart, index) => {
        const $userPrice = $chart.querySelector(".oilInfoArea__myChart");
        const $averagePrice = $chart.querySelector(".oilInfoArea__averageChart");
        const $userPriceText = $userPrice.querySelector("span");
        const $averageText = $averagePrice.querySelector("span");

        $userPriceText.innerHTML = parseNumberToMoneyString(userOilArray[index]);
        $averageText.innerHTML = parseNumberToMoneyString(commonOilArray[index]);

        let userPercent = userOilArray[index] / OIL_UPPER_PRICE;
        let averagePercent = commonOilArray[index] / OIL_UPPER_PRICE;

        userPercent = userPercent + 0.1 >= 1 ? 100 : userPercent * 100 + 10;
        averagePercent = averagePercent + 0.1 >= 1 ? 100 : averagePercent * 100 + 10;

        setTimeout(() => {
            changeCSS($userPrice, "height", `${userPercent}%`);
            changeCSS($averagePrice, "height", `${averagePercent}%`);
        }, 50 * index);
    })
}

const filterChart = ($container) => {

}

export { makeChart, filterChart }