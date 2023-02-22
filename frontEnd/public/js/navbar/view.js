import { isLogin, userId } from "../login/fetch.js";
import { eventToLogoutBtn } from "./event.js";
import { getNonMemberNavBarTemplate, getMemberNavBarTemplate } from "./template.js";

const navBarView = () => {
    const $navBar = document.createElement("section");
    $navBar.classList.add("navbar");

    isLogin ? 
        $navBar.innerHTML = getMemberNavBarTemplate(userId) : 
        $navBar.innerHTML = getNonMemberNavBarTemplate();

    if(isLogin) {
        eventToLogoutBtn($navBar);
    }

    return $navBar;
}

export { navBarView }