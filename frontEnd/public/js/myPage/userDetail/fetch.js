import { isReleaseMode, _$, changeCSS } from "../../common/utils"
import { BASE_COOKIE_URL, HEADER, METHOD, RELEASE_COOKIE_URL } from "../../common/variable"

const fetchModifyUserDetail = (modifiedGender, modifiedAge, $container) => {
    const FETCH_URL = isReleaseMode() ?
                        RELEASE_COOKIE_URL + "/user" :
                        BASE_COOKIE_URL + "/user";

    
    fetch(FETCH_URL, {
        method: METHOD.PATCH,
        headers: HEADER.PATCH,
        body: JSON.stringify({
            "gender": modifiedGender,
            "age": modifiedAge
        }),
        credentials: "include"
    }).then((res) => {
        if(res.status === 200) {
            const $successModal = _$(".myPage__SuccessModal", $container);
            changeCSS($successModal, "top", 0);
            setTimeout(() => {
                changeCSS($successModal, "top", "-30%");
            }, 900);
        }
    })
}

export { fetchModifyUserDetail }


