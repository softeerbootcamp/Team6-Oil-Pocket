import { BASE_COOKIE_URL, METHOD } from "../../common/variable"
import { makeComparisonCards } from "./helperFunction";

const COMPARISON_URL = BASE_COOKIE_URL + "/user/eco-record";

const fetchComparisonCardData = async ($container) => {
    await fetch(COMPARISON_URL, {
        method: METHOD.GET,
        credentials: "include"
    }).then((res) => {
        return res.json();
    }).then(({data : {userId, gender, age, refuelingPrice, myEcoPrice, averageEcoPrice, imageUrl, rankPercentage}}) => {
        makeComparisonCards($container, refuelingPrice, averageEcoPrice, myEcoPrice, rankPercentage, age, gender, userId);
    })
}

export { fetchComparisonCardData }