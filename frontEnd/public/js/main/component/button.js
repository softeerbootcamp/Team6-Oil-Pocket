import { addEvent, changeCSS } from "../../helper/helperFunction.js";
import { getChatBotArea } from "../node.js";

const $explainArea = document.querySelector(".explainArea");
const $chatBotOpenBtn = document.querySelector(".chatBotArea__img");

const eventToChatBotCloseBtn = () => {
    const $chatBotCloseBtn = document.querySelector(".chatBotArea__closeBtn");
    addEvent($chatBotCloseBtn, [
        () => {
            changeCSS($chatBotOpenBtn, "display", "block");
            const $chatBotArea = document.querySelector(".chatBotArea");
            $chatBotArea.remove();
        }
    ])
}

const eventToChatBotBtns = () => {
    addEvent($chatBotOpenBtn, [
        () => {
            changeCSS($chatBotOpenBtn, "display", "none");
            const $chatBotArea = getChatBotArea();
            $explainArea.before($chatBotArea);
            setTimeout(() => changeCSS($chatBotArea, "opacity", 1));
            eventToChatBotCloseBtn();
        }
    ]);
}

const eventToBtns = () => {
    eventToChatBotBtns();
}

export { eventToBtns }