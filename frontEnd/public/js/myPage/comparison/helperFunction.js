import { _$, parseNumberToMoneyString } from "../../common/function";

const imageLocationMapper = {
    "동전": "./public/img/myProfile_Image/coin.png",
    "아이스 아메리카노": "./public/img/myProfile_Image/coffee.png",
    "햄버거 세트": "./public/img/myProfile_Image/hamburger.png",
    "국밥": "./public/img/myProfile_Image/rice.png",
    "치킨": "./public/img/myProfile_Image/chicken.png",
};

const getCompareText = (myPrice, commonPrice) => {
    if(myPrice < commonPrice) {
        return `이번 달은 다른 사람들보다 <span>${getImageName(myPrice, commonPrice)}</span> 만큼 절약했어요! 😁`;
    }
    return `이번 달은 다른 사람들보다 <span>${getImageName(myPrice, commonPrice)}</span> 만큼 더 소비했어요! 🥲`;
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
    else if(differ >= 100) {
        return "동전";
    }
}

const setImageByName = ($image, imageName) => $image.setAttribute("src", imageLocationMapper[imageName]);

const makeComparisonCards = ($container, myPrice, commonPrice) => {
    const $myPirceText = _$(".oilInfoArea__compareMyBox > h1", $container);
    const $commonPriceText = _$(".oilInfoArea__compareCommonBox > h1", $container);
    const $imageText = _$(".oilInfoArea__compareTitle", $container);
    const $compareImage = _$(".oilInfoArea__compareImgBox > img", $container);

    $myPirceText.innerHTML = parseNumberToMoneyString(myPrice);
    $commonPriceText.innerHTML = parseNumberToMoneyString(commonPrice);
    $imageText.innerHTML = getCompareText(myPrice, commonPrice);

    setImageByName($compareImage, getImageName(myPrice, commonPrice));
}

export { makeComparisonCards }