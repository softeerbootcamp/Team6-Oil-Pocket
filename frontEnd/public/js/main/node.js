import { getChatBotAreaTemplate } from "../template.js";

const getChatBotArea = () => {
    const $chatBotArea = document.createElement("section");
    $chatBotArea.classList.add("chatBotArea");
    $chatBotArea.innerHTML = getChatBotAreaTemplate();

    return $chatBotArea;
}

export { getChatBotArea }