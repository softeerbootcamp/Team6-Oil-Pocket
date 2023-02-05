import { addEvent, changeCSS, replaceChildWithFadeEffect } from "../../helper/helperFunction.js";
import { getChatBotArea, getLoginArea, getRegisterArea } from "../node.js";

const $explainArea = document.querySelector(".explainArea");
const $chatBotOpenBtn = document.querySelector(".chatBotArea__img");
const $goLoginBtn = document.querySelector("#goLoginBtn");

// chat bot btn
const eventToChatBotCloseBtn = () => {
    const $chatBotCloseBtn = document.querySelector(".chatBotArea__closeBtn");
    addEvent($chatBotCloseBtn, [
        () => {
            changeCSS($chatBotOpenBtn, "display", "block");
            const $chatBotArea = document.querySelector(".chatBotArea");
            $chatBotArea.style.opacity = 0;
            setTimeout(() => $chatBotArea.remove(), 1000);
        }
    ])
}

const eventToChatBotBtns = () => {
    addEvent($chatBotOpenBtn, [
        () => changeCSS($chatBotOpenBtn, "display", "none"),
        () => {
            const $chatBotArea = getChatBotArea();
            $explainArea.before($chatBotArea);
            setTimeout(() => changeCSS($chatBotArea, "opacity", 1));
        },
        () => eventToChatBotCloseBtn()
    ]);
}

// nav btn
const eventToLoginBtn = () => {
    addEvent($goLoginBtn, [
        () => {
            const $navBar = document.querySelector(".navbar");
            $navBar.remove();
        },
        () => {
            const $mainSection = document.querySelector("section.main");
            const $loginArea = getLoginArea();
            replaceChildWithFadeEffect($mainSection, $loginArea);
        }
    ])
}

const eventToRegisterBtn = ($registerBtn) => {
    addEvent($registerBtn, [
        () => {
            const $mainSection = document.querySelector("section.main");
            const $registerArea = getRegisterArea();
            replaceChildWithFadeEffect($mainSection, $registerArea);
        }
    ])
}

// init btns
const eventToBtns = () => {
    eventToChatBotBtns();
    eventToLoginBtn();
}

export { eventToBtns, eventToRegisterBtn }