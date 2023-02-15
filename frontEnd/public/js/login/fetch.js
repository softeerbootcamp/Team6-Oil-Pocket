import { _$, changeCSS } from "../common/function";
import { BASE_COMMON_URL, HEADER, METHOD } from "../common/variable";

let isLogin = "";
let userId = ""
let userGender = "";
let userAge = "";

const fetchLoginID = ($IDInput, $PWInput) => {
    const $loginErrorModal = _$(".loginArea__errorModal");
    
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

const fecthCheckLogin = async () => {
    isLogin = false;
    const CHECK_LOGIN_URL = BASE_COMMON_URL + "/auth/status";

    await fetch(CHECK_LOGIN_URL, {
        method: METHOD.GET,
        credentials: "include"
    }).then((res) => {
        if(res.status === 200) {
            isLogin = true;
            return res.json();
        }
        else if(res.status === 401) {
            isLogin = false;
            return {};
        }
    }).then(({data}) => {
        if(data) {
            userId = data.userId;
            userGender = data.gender;
            userAge = data.age;
        }
    })
}

export { 
    fetchLoginID, fecthCheckLogin, 
    isLogin, userId, userGender, userAge
}