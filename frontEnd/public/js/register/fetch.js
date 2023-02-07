import { BASE_COMMON_URL, HEADER, METHOD } from "../common/variable";

const ID_VALIDATION_URL = BASE_COMMON_URL + "auth";
const REGISTER_URL = BASE_COMMON_URL + "user";

function fetchValidateID(IDString, $registerBtn) {
    fetch(ID_VALIDATION_URL + `?id=${IDString}`, {
        method: METHOD.GET
    }).then((res) => {
        if(res.status === 200) {
            $registerBtn.disabled = false;
        }
    })
}

function fetchRegisterID(requestBody) {
    fetch(REGISTER_URL, {
        method: METHOD.POST,
        headers: HEADER.POST,
        body: JSON.stringify(requestBody)
    })
}

export { fetchValidateID, fetchRegisterID }