import { _$, parseNumberToMoneyString, changeCSS } from "../../common/function";

const imageLocationMapper = {
    "동전": "./public/img/myProfile_Image/coin.png",
    "사탕": "./public/img/myProfile_Image/candy.png",
    "아이스 아메리카노": "./public/img/myProfile_Image/coffee.png",
    "햄버거 세트": "./public/img/myProfile_Image/hamburger.png",
    "국밥": "./public/img/myProfile_Image/rice.png",
    "치킨": "./public/img/myProfile_Image/chicken.png",
};

const getCompareText = (myPrice, commonPrice) => {
    if(myPrice < commonPrice) {
        return `이번 달은 <span>${getImageName(myPrice, commonPrice)}</span> 만큼 절약했어요! 😁`;
    }
    else if(myPrice > commonPrice) {
        return `이번 달은 <span>${getImageName(myPrice, commonPrice)}</span> 만큼 더 소비했어요! 🥲`;
    }

    return "이번 달은 다른 사람들만큼 사용했네요!";
}

const getImageName = (myPrice, commonPrice) => {
    let differ = Math.abs(myPrice - commonPrice);

    if(differ >= 20_000) {
        return "치킨";
    }
    else if(differ >= 10_000) {
        return "국밥";
    }
    else if(differ >= 5_000) {
        return "햄버거 세트";
    }
    else if(differ >= 1_000) {
        return "아이스 아메리카노";
    }
    else if(differ >= 0) {
        return "사탕";
    }
}

const setImageByName = ($image, imageName) => $image.setAttribute("src", imageLocationMapper[imageName]);

const adjustChartsOnCard = (myPrice, commonPrice, $container) => {
    const $myChart = _$(".oilInfoArea__myChart", $container);
    const $commonChart = _$(".oilInfoArea__otherChart", $container);

    const upperBound = Math.max(myPrice, commonPrice);

    let myPercent = myPrice / upperBound * 100;
    let commonPercent = commonPrice / upperBound * 100;

    changeCSS($myChart, "height", `${myPercent}%`);
    changeCSS($commonChart, "height", `${commonPercent}%`);
}

const makeComparisonCards = ($container, myPrice, commonPrice, percent, ageText, gender) => {
    const $myPirceText = _$(".oilInfoArea__compareMyBox > h1", $container);
    const $commonPriceText = _$(".oilInfoArea__compareMySaveBox > h1", $container);
    const $imageText = _$(".oilInfoArea__compareTitle", $container);
    const $compareImage = _$(".oilInfoArea__compareImgBox > img", $container);
    const $agePriceText = _$(".oilInfoArea__compareAgeCommonBox > h1", $container);
    const $chartMyPriceText = _$(".oilInfoArea__chartValueText--user", $container);
    const $chartCommonPriceText = _$(".oilInfoArea__chartValueText--common", $container);
    const $percentText = _$(".oilInfoArea__otherText", $container);
    const $chartCommonText = _$(".oilInfoArea__chartNameArea--info", $container);

    $myPirceText.innerHTML = parseNumberToMoneyString(myPrice);
    $commonPriceText.innerHTML = parseNumberToMoneyString(commonPrice - myPrice);
    
    let priceDiffColor = "red";
    if(commonPrice - myPrice < 0) {
        priceDiffColor = "#3181F6";
    }
    else if(commonPrice == myPrice) {
        priceDiffColor = "#000";
    }

    changeCSS($commonPriceText, "color", priceDiffColor);

    $imageText.innerHTML = getCompareText(myPrice, commonPrice);
    $agePriceText.innerHTML = parseNumberToMoneyString(commonPrice);
    $chartMyPriceText.innerHTML = parseNumberToMoneyString(myPrice);
    $chartCommonPriceText.innerHTML = parseNumberToMoneyString(commonPrice);
    $percentText.innerHTML = `현재까지 절약 금액은 ${ageText} ${gender} 중  <span>상위 ${percent}%</span>  입니다.`;
    $chartCommonText.innerHTML = `${ageText} ${gender}`;

    adjustChartsOnCard(myPrice, commonPrice, $container);
    setImageByName($compareImage, getImageName(myPrice, commonPrice));
}

export { makeComparisonCards }