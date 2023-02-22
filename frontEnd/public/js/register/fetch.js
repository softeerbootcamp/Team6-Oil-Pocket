import { _$, changeCSS, isReleaseMode } from "../common/utils";
import { BASE_COMMON_URL, HEADER, METHOD, RELEASE_COMMON_URL } from "../common/variable";
import { setTargetDisabled } from "./helperFunction";

function fetchValidateID($IDInput, $IDValidateBtn, $registerBtn) {
    const $IdValidateErrorModal = _$(".registerArea__errorModal--IDvalidation");
    const FETCH_URL = isReleaseMode() ? 
                        RELEASE_COMMON_URL + "/auth" :
                        BASE_COMMON_URL + "/auth";


    fetch(FETCH_URL + `?id=${$IDInput.value}`, {
        method: METHOD.GET
    }).then((res) => {
        if(res.status === 200) {
            setTargetDisabled($registerBtn, false);
            setTargetDisabled($IDInput, true);
            setTargetDisabled($IDValidateBtn, true);
        }
        else {
            changeCSS($IdValidateErrorModal, "top", "12%");
            setTimeout(() => changeCSS($IdValidateErrorModal, "top", "-12%"), 1200);
        }
    })
}

function fetchRegisterID(requestBody) {
    const $registerErrorModal = _$(".registerArea__errorModal--register");
    const FETCH_URL = isReleaseMode() ?
                        RELEASE_COMMON_URL + "/user" :
                        BASE_COMMON_URL + "/user";

    fetch(FETCH_URL, {
        method: METHOD.POST,
        headers: HEADER.POST,
        body: JSON.stringify(requestBody)
    }).then((res) => {        
        if(res.status === 201) {
            location.assign("/login");
        }
        else {
            changeCSS($registerErrorModal, "top", "12%");
            setTimeout(() => changeCSS($registerErrorModal, "top", "-12%"), 1200);
        }
    })
}

export { fetchValidateID, fetchRegisterID }