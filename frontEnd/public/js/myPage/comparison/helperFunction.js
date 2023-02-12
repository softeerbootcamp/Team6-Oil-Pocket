import { parseNumberToMoneyString } from "../../common/function";

const makeComparisonCards = ($container, myPrice, commonPrice) => {
    const $myPirceText = $container.querySelector(".oilInfoArea__compareMyBox > h1");
    const $commonPriceText = $container.querySelector(".oilInfoArea__compareCommonBox > h1");

    $myPirceText.innerHTML = parseNumberToMoneyString(myPrice);
    $commonPriceText.innerHTML = parseNumberToMoneyString(commonPrice);
}

export { makeComparisonCards }