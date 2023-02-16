import { isReleaseMode } from "../../common/function";
import { BASE_COOKIE_URL, METHOD, RELEASE_COOKIE_URL } from "../../common/variable"
import { makeComparisonCards } from "./helperFunction";

const FETCH_URL = isReleaseMode() ? 
                    RELEASE_COOKIE_URL + "/user/eco-record" :
                    BASE_COOKIE_URL + "/user/eco-record";

const fetchComparisonCardData = async ($container) => {
    await fetch(FETCH_URL, {
        method: METHOD.GET,
        credentials: "include"
    }).then((res) => {
        if(res.status === 500) {
            return {data: {}};
        }
        return res.json();
    }).then(({data : {userId, gender, age, refuelingPrice, myEcoPrice, averageEcoPrice, rankPercentage}}) => {
        if(userId) {
            makeComparisonCards($container, refuelingPrice, averageEcoPrice, myEcoPrice, rankPercentage, age, gender, userId);
        }
    })
}

export { fetchComparisonCardData }