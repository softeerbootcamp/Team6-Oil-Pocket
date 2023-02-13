import { _$, changeCSS } from "../common/function";
import { BASE_COMMON_URL, HEADER, METHOD } from "../common/variable";

const fetchLoginID = ($IDInput, $PWInput) => {
    const $loginErrorModal = _S(".loginArea__errorModal");
    
    fetch(BASE_COMMON_URL + "/auth", {
        method: METHOD.POST,
        headers: HEADER.POST, 
        body: JSON.stringify({
            "id": $IDInput.value,
            "password": $PWInput.value
        }),
        credentials: "include"
    }).then((res) => {
        if(res.status === 201) {
            location.assign("/");
        }
        else {
            changeCSS($loginErrorModal, "top", "12%");
            setTimeout(() => {
                changeCSS($loginErrorModal, "top", "-12%")
                $IDInput.focus();
            }, 1200);
        }
    })
}

export { fetchLoginID }