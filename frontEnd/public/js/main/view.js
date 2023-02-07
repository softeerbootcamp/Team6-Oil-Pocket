import { navBarView } from "../navbar/view.js";
import { eventToChatBot } from "./event.js";
import { getChatBotTemplate, getMainViewContentTemplate } from "./template.js";

const mainView = () => {
    const $mainViewContainer = document.createElement("section");
    const $mainViewContent = document.createElement("section");

    $mainViewContent.innerHTML = getMainViewContentTemplate();

    const $chatBotImg = $mainViewContent.querySelector(".chatBotArea__img");
    eventToChatBot($chatBotImg);

    $mainViewContainer.appendChild(navBarView());
    $mainViewContainer.appendChild($mainViewContent);

    return $mainViewContainer;
}

const chatBotView = () => {
    const $chatBotContainer = document.createElement("section");
    $chatBotContainer.classList.add("chatBotArea");
    $chatBotContainer.innerHTML = getChatBotTemplate();

    return $chatBotContainer;
}

export { mainView, chatBotView }