import { BASE_COMMON_URL, HEADER, METHOD } from "../common/variable";
import { setTargetDisabled } from "./helperFunction";

const ID_VALIDATION_URL = BASE_COMMON_URL + "/auth";
const REGISTER_URL = BASE_COMMON_URL + "/user";

function fetchValidateID($IDInput, $IDValidateBtn, $registerBtn) {
    fetch(ID_VALIDATION_URL + `?id="${$IDInput.value}"`, {
        method: METHOD.GET
    }).then((res) => {
        if(res.status === 200) {
            setTargetDisabled($registerBtn, false);
            setTargetDisabled($IDInput, true);
            setTargetDisabled($IDValidateBtn, true);
        }
        else {
            alert("올바르지 않은 ID 형식입니다.");
        }
    })
}

function fetchRegisterID(requestBody) {
    fetch(REGISTER_URL, {
        method: METHOD.POST,
        headers: HEADER.POST,
        body: JSON.stringify(requestBody)
    }).then((res) => {
        console.log(res)
        if(res.status === 201) {
            location.assign("/login");
        }
        else {
            alert("회원가입에 실패하였습니다.");
        }
    })
}

export { fetchValidateID, fetchRegisterID }