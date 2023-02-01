import { getChatBotAreaTemplate, getLoginAreaTemplate, getRegisterAreaTemplate } from "../template.js";
import { eventToRegisterBtn } from "./component/button.js";

const getChatBotArea = () => {
    const $chatBotArea = document.createElement("section");
    $chatBotArea.classList.add("chatBotArea");
    $chatBotArea.innerHTML = getChatBotAreaTemplate();

    return $chatBotArea;
}

const getLoginArea = () => {
    const $loginArea = document.createElement("section");
    $loginArea.classList.add("loginArea");
    $loginArea.innerHTML = getLoginAreaTemplate();

    const $registerBtn = $loginArea.querySelector("#loginArea__registerBtn");
    eventToRegisterBtn($registerBtn);

    return $loginArea;
}

const getRegisterArea = () => {
    const $registerArea = document.createElement("section");
    $registerArea.classList.add("registerArea");
    $registerArea.innerHTML = getRegisterAreaTemplate();

    return $registerArea;
}

export { getChatBotArea, getLoginArea, getRegisterArea }