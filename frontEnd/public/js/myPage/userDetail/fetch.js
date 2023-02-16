import { isReleaseMode } from "../../common/function"
import { BASE_COOKIE_URL, HEADER, METHOD, RELEASE_COOKIE_URL } from "../../common/variable"

const fetchModifyUserDetail = (modifiedGender, modifiedAge) => {
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
    })
}

export { fetchModifyUserDetail }


