import { changeCSS } from "../common/function";
import { BASE_COMMON_URL, HEADER, METHOD } from "../common/variable";
import { setTargetDisabled } from "./helperFunction";

const ID_VALIDATION_URL = BASE_COMMON_URL + "/auth";
const REGISTER_URL = BASE_COMMON_URL + "/user";

function fetchValidateID($IDInput, $IDValidateBtn, $registerBtn) {
    const $IdValidateErrorModal = document.querySelector(".registerArea__errorModal--IDvalidation");

    fetch(ID_VALIDATION_URL + `?id="${$IDInput.value}"`, {
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
    const $registerErrorModal = document.querySelector(".registerArea__errorModal--register");

    fetch(REGISTER_URL, {
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